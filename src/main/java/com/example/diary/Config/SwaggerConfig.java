package com.example.diary.Config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
@Configuration
public class SwaggerConfig {

    @Bean
    public OpenAPI openAPI() {
        Info info = new Info()
                .title("Contact API")
                .version("0.0.1")
                .description("<h3>Contact Service Swagger</h3>");

        return new OpenAPI()
                .info(info);
    }

}
