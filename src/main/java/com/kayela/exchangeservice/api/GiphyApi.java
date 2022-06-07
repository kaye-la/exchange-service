package com.kayela.exchangeservice.api;

import com.kayela.exchangeservice.dto.GiphyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "${giphy.service.name}", url = "${giphy.service.url}")
public interface GiphyApi {
    /**
     * Calls giphy API and returns gifs
     * https://developers.giphy.com/docs/api/endpoint#search
     * @param apiKey GIPHY API Key.
     * @param q Search query term or phrase.
     * @param limit The maximum number of objects to return.
     * @return giphyDTO
     */
    @GetMapping("/v1/gifs/search")
    GiphyDTO getGifs(@RequestParam(value = "api_key") String apiKey,
        @RequestParam(value = "q") String q,
        @RequestParam(value = "limit") int limit);
}
