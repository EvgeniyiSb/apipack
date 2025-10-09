package org.sb.task.apipack.repository;

import org.sb.task.apipack.service.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UserRepositoryConfig {
    @Bean
    public UserServiceImpl userService(){
        return new UserServiceImpl();
    }
}
