package br.com.itau.cat_api_collector.dto;

import lombok.Data;

@Data
public class ImageInfoDto {
    private String id;
    private Integer width;
    private Integer height;
    private String url;
}

