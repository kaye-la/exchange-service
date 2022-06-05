package com.kayela.exchangeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

/**
 * todo Document type GifDTO
 */
public class GifDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("embed_url")
    private String embed_url;
}
