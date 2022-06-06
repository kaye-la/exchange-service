package com.kayela.exchangeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.NoArgsConstructor;

import java.util.List;

@NoArgsConstructor
public class GiphyDTO {
    @JsonProperty("data")
    private List<GifDTO> data;

    public GiphyDTO(List<GifDTO> data) {
        this.data = data;
    }

    public List<GifDTO> getData() {
        return data;
    }
}
