package com.kayela.exchangeservice.api;

import com.kayela.exchangeservice.dto.RatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${oxr.service.name}", url = "${oxr.service.url}")
public interface ExchangeApi {
    @GetMapping("/api/latest.json")
    RatesDTO getLatestRates(@RequestParam(value = "app_id") String appId,
        @RequestParam(value = "base") String base);

    @GetMapping("/api/historical/{date}.json")
    RatesDTO getRatesByDate(@PathVariable(name = "date") String date,
        @RequestParam(value = "app_id") String appId,
        @RequestParam(value = "base") String base);
}
