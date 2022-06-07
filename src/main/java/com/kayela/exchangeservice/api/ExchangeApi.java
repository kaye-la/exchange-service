package com.kayela.exchangeservice.api;

import com.kayela.exchangeservice.dto.RatesDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(name = "${oxr.service.name}", url = "${oxr.service.url}")
public interface ExchangeApi {
    /**
     * Get the latest exchange rates available from the Open Exchange Rates API
     * https://docs.openexchangerates.org/docs/latest-json
     * @param appId Your unique App ID
     * @param base Change base currency (3-letter code, default: USD)
     * @return ratesDTO
     */
    @GetMapping("/api/latest.json")
    RatesDTO getLatestRates(@RequestParam(value = "app_id") String appId,
        @RequestParam(value = "base") String base);

    /**
     * Get historical exchange rates for any date available from the Open Exchange Rates API
     * https://docs.openexchangerates.org/docs/historical-json
     * @param date The requested date in YYYY-MM-DD format (required).
     * @param appId Your unique App ID (required)
     * @param base Change base currency (3-letter code, default: USD)
     * @return ratesDTO
     */
    @GetMapping("/api/historical/{date}.json")
    RatesDTO getRatesByDate(@PathVariable(name = "date") String date,
        @RequestParam(value = "app_id") String appId,
        @RequestParam(value = "base") String base);
}
