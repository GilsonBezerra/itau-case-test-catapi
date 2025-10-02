package br.com.itau.cat_api_collector;

import br.com.itau.cat_api_collector.config.TheCatApiProperties;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.EnableConfigurationProperties;

@SpringBootApplication
public class CatApiCollectorApplication {

	public static void main(String[] args) {
		SpringApplication.run(CatApiCollectorApplication.class, args);
	}

}
