package br.com.itau.cat_api_collector.service;

import br.com.itau.cat_api_collector.config.RabbitConfig;
import br.com.itau.cat_api_collector.config.TheCatApiProperties;
import br.com.itau.cat_api_collector.dto.BreedDto;
import br.com.itau.cat_api_collector.dto.BreedMessage;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.List;
import java.util.concurrent.CompletableFuture;

@Service
@RequiredArgsConstructor
@Slf4j
public class CollectorService {

    private final WebClient.Builder webClientBuilder;
    private final RabbitTemplate rabbitTemplate;
    private final TheCatApiProperties properties;
    private final ObjectMapper objectMapper = new ObjectMapper();

    @Scheduled(fixedRate = 60000, scheduler = "taskScheduler")
    public void collectBreeds() {
        WebClient client = webClientBuilder
                .baseUrl(properties.getBaseUrl())
                .build();

        List<BreedDto> breeds = client.get()
                .uri(uriBuilder -> uriBuilder
                        .path(properties.getUrl())
                        .queryParam("limit", 10)
                        .queryParam("page", 0)
                        .build())
                .header("Content-Type", properties.getContentType())
                .retrieve()
                .bodyToFlux(BreedDto.class)
                .collectList()
                .block();
        log.info("Dados coletados: " + breeds);

        if (breeds != null) {
            breeds.forEach(breed -> {
                BreedMessage message = new BreedMessage(
                        breed.id(),
                        breed.name(),
                        breed.origin(),
                        breed.temperament(),
                        breed.description()
                );
                try {
                    String json = objectMapper.writeValueAsString(message);
                    log.info("{} -> JSON enviado para a fila: {}", Thread.currentThread().getName(), json);
                } catch (Exception e) {
                    log.error("Erro ao serializar BreedMessage para JSON", e);
                }
                rabbitTemplate.convertAndSend(RabbitConfig.CAT_API_BREEDS_QUEUE, message);
            });
        }
    }
}
