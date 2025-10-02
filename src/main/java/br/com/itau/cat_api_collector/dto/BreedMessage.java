package br.com.itau.cat_api_collector.dto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.Arrays;
import java.util.List;

@Data
@NoArgsConstructor
public class BreedMessage {
    private String catApiId;
    private String name;
    private String origin;
    private String description;
    private List<String> temperament;
    private List<String> images;

    public BreedMessage(String id, String name, String origin, String temperament, String description) {
        this.catApiId = id;
        this.name = name;
        this.origin = origin;
        this.description = description;
        if (temperament != null) {
            this.temperament = Arrays.asList(temperament.split(",\\s*"));
        }
    }
}
