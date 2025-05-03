package com.wiseai.reservemeetingroom.core.config;

import io.swagger.v3.oas.models.OpenAPI;
import io.swagger.v3.oas.models.info.Info;
import io.swagger.v3.oas.models.servers.Server;
import java.util.List;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;

/**
 * swagger 설정을 담당합니다.
 */
@Configuration
@RequiredArgsConstructor
public class OpenApiConfig {
    private final Environment environment;

    @Bean
    public OpenAPI customOpenAPI() {
        String activeProfile = environment.getProperty("spring.profiles.active", "local");

        String serverUrl = System.getenv("PROD_SERVER_URL");
        if (serverUrl == null || serverUrl.isBlank()) {
            serverUrl = "http://localhost:8080";
        }

        return new OpenAPI()
                .servers(List.of(new Server().url(serverUrl).description(activeProfile + " server")))
                .info(new Info().title("Reservation Meeting Room API Documentation")
                        .description("회의실 예약 서비스에 대한 API 문서입니다.")
                        .version("1.0"));
    }
}
