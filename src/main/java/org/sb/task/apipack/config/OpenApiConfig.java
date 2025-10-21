package org.sb.task.apipack.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Contact;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OpenApiConfig {

    @Bean
    public OpenAPI customOpenApi(){
        Contact contact = new Contact();
        Server server = new Server();
        Info info = new Info();

        contact.email("smtptest.sbj@gmail.com")
                        .name("Developer")
                        .setUrl("url.su");

        server.setUrl("http://localhost:8080");

        info.title("User Service API")
                .contact(contact)
                .version("1.0")
                .description("Микросервис работы с пользователями");

        return new OpenAPI().info(info).servers(List.of(server));
    }
}
