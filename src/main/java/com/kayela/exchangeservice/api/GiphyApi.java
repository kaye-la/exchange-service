package com.kayela.exchangeservice.api;

import com.kayela.exchangeservice.dto.GiphyDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;


@FeignClient(name = "${giphy.service.name}", url = "${giphy.service.url}")
public interface GiphyApi {
    @GetMapping("/v1/gifs/search")
    GiphyDTO getGifs(@RequestParam(value = "api_key") String apiKey,
        @RequestParam(value = "q") String q,
        @RequestParam(value = "limit") int limit);
}
