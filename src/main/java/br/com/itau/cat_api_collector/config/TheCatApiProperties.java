package br.com.itau.cat_api_collector.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

@Data
@Configuration
@ConfigurationProperties(prefix = "thecatapi")
public class TheCatApiProperties {
    private String url;
    private String baseUrl;
    private String contentType;
}
