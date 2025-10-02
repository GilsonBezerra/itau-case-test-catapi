package br.com.itau.cat_api_collector.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

public record BreedDto(
        @JsonProperty("id") String id,
        @JsonProperty("name") String name,
        @JsonProperty("origin") String origin,
        @JsonProperty("temperament") String temperament,
        @JsonProperty("description") String description,
        @JsonProperty("image") ImageInfoDto image
) {}

