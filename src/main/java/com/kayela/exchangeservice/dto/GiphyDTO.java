package com.kayela.exchangeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

/**
 * todo Document type GiphyDTO
 */
public class GiphyDTO {

    @JsonProperty("data")
    private List<GifDTO> data;

    public List<GifDTO> getData() {
        return data;
    }
}
