package com.kayela.exchangeservice.controller;

import com.kayela.exchangeservice.api.service.ExchangeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/currencies")
public class ExchangeController {
    private ExchangeService exchangeService;

    @Autowired
    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("/{currency}")
    public ResponseEntity<Map<String, BigDecimal>> getAllCurrencies(@PathVariable String currency)
    {
        return new ResponseEntity<>(exchangeService.getLatestRates(), HttpStatus.OK);
    }
}
