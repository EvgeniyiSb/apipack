package org.sb.task.apipack;

import com.sun.net.httpserver.HttpServer;
import org.sb.task.apipack.discovery.ServiceDiscovery;
import org.sb.task.apipack.externalconfig.ExternalConfiguration;
import org.sb.task.apipack.gateway.GatewayAPI;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.net.InetSocketAddress;


@SpringBootApplication
public class ApipackApplication {

    public static void main(String[] args) throws IOException {
        SpringApplication.run(ApipackApplication.class, args);

        GatewayAPIStart();
        ServiceDiscoveryStart();
        ExternalPropertiesStart();
    }

    /**
     * Запуск пользовательской реализации паттерна Gateway API
     * @throws IOException Выброс исключение ввод/вывода
     */
    private static void GatewayAPIStart() throws IOException {
        HttpServer httpServer = HttpServer.create(new InetSocketAddress(8083), 0);
        httpServer.createContext("/", new GatewayAPI());
        httpServer.setExecutor(null);
        httpServer.start();
    }

    /**
     * Запуск пользовательской реализации паттерна Service Discovery
     */
    private static void ServiceDiscoveryStart(){
        ServiceDiscovery.addService("service1", "http://localhost:8080/users/get/all");
        ServiceDiscovery.addService("service2", "http://localhost:8080/users/get/50");
        ServiceDiscovery.addService("service3", "http://localhost:8080/users/get/51");
    }

    /**
     * Запуск пользовательской реализации паттерна External Configuration
     */
    private static void ExternalPropertiesStart(){
        ExternalConfiguration externalConfiguration = new ExternalConfiguration();

        System.out.println(externalConfiguration.getPropertyValue("something.1"));
        System.out.println(externalConfiguration.getPropertyValue("something.2"));
    }
}
