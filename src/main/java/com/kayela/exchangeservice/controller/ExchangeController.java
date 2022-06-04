package com.kayela.exchangeservice.controller;

import com.kayela.exchangeservice.api.service.ExchangeService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Map;

@RestController
@RequestMapping("/api")
public class ExchangeController {
    private ExchangeService exchangeService;

    public ExchangeController(ExchangeService exchangeService) {
        this.exchangeService = exchangeService;
    }

    @GetMapping("currencies/{currency}")
    public ResponseEntity<Map<String, BigDecimal>> getAllCurrencies(@PathVariable String currency)
    {
        return new ResponseEntity<>(exchangeService.getAllCurrencies(), HttpStatus.OK);
    }
    @GetMapping("currencie/")
    public ResponseEntity<String> tempCheck()
    {
        return ResponseEntity.ok(exchangeService.getAll());
    }
}
