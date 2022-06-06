package com.kayela.exchangeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;

@Data
@NoArgsConstructor
public class GifDTO {

    @JsonProperty("id")
    private String id;

    @JsonProperty("url")
    private String url;

    @JsonProperty("embed_url")
    private String embedUrl;

    @Autowired
    public GifDTO(String id, String url) {
        this.id = id;
        this.url = url;
    }

    public GifDTO(String id, String url, String embedUrl) {
        this.id = id;
        this.url = url;
        this.embedUrl = embedUrl;
    }

    public String getId() {
        return id;
    }

    public String getUrl() {
        return url;
    }
}
