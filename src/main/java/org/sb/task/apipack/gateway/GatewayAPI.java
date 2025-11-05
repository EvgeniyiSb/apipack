package org.sb.task.apipack.gateway;

import com.sun.net.httpserver.HttpExchange;
import com.sun.net.httpserver.HttpHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;

import java.io.IOException;
import java.net.URI;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.util.HashMap;
import java.util.Map;

/**
 * Реализация Getaway API
 */
@Slf4j
public class GatewayAPI implements HttpHandler {
    private static final Map<String, String> ROUTE_LIST = new HashMap<>();

    static {
        ROUTE_LIST.put("/get", "http://localhost:8080/users");
        ROUTE_LIST.put("/all", "http://localhost:8080/users/get");
    }

    @Override
    public void handle(HttpExchange exchange) throws IOException {
        String requestPath = exchange.getRequestURI().getPath();
        String targetUrl = route(requestPath);

        if(targetUrl != null){
            HttpClient httpClient = HttpClient.newHttpClient();
            HttpRequest httpRequest = HttpRequest.newBuilder()
                    .uri(URI.create(targetUrl + requestPath))
                    .method(exchange.getRequestMethod(), HttpRequest.BodyPublishers.noBody())
                    .build();

            try {
                HttpResponse<String> httpResponse = httpClient.send(httpRequest, HttpResponse.BodyHandlers.ofString());

                output(exchange, httpResponse.statusCode(), httpResponse.body().getBytes());
            } catch (InterruptedException e) {
                log.error("e: ", e);

                output(exchange, HttpStatus.INTERNAL_SERVER_ERROR.value(), "Ошибка ответа прокси");
            }
        } else {
            output(exchange, HttpStatus.NOT_FOUND.value(), "Служба не найдена");
        }
    }

    /**
     * Вывести результат выходного потока
     * @param httpExchange Сгенерированный HTTP-запрос обмена
     * @param httpCode HTTP-код ответа
     * @param response Возвращаемый ответ
     * @throws IOException Ошибка ввода/вывода
     */
    private static void output(HttpExchange httpExchange, int httpCode, String response) throws IOException {
        output(httpExchange, httpCode, response.getBytes());
    }

    /**
     * Вывести результат выходного потока
     * @param httpExchange Сгенерированны HTTP-запрос обмена
     * @param httpCode HTTP-код ответ
     * @param response Возвращаемый ответ
     * @throws IOException Ошибка ввода/вывода
     */
    private static void output(HttpExchange httpExchange, int httpCode, byte[] response) throws IOException {
        httpExchange.sendResponseHeaders(httpCode, response.length);
        httpExchange.getResponseBody().write(response);
        httpExchange.close();
    }

    /**
     * Получить путь из списка заданных URL-маршрутов
     * @param path Входной путь
     * @return Полученный путь
     */
    private static String route(String path){
        for (Map.Entry<String, String> entry : ROUTE_LIST.entrySet()){
            if(path.startsWith(entry.getKey())){
                return entry.getValue();
            }
        }

        return null;
    }

}
