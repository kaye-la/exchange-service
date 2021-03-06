package com.kayela.exchangeservice.dto;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.math.BigDecimal;
import java.util.Map;

public class RatesDTO {
    @JsonProperty("base")
    private String base;

    @JsonProperty("rates")
    private Map<String, BigDecimal> rates;

    public RatesDTO(String base, Map<String, BigDecimal> rates) {
        this.base = base;
        this.rates = rates;
    }

    public Map<String, BigDecimal> getRates() {
        return rates;
    }
}
