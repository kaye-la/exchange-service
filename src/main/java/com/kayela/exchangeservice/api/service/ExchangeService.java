package com.kayela.exchangeservice.api.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * todo Document type ExchangeService
 */
public interface ExchangeService {
    Map<String, BigDecimal> getAllCurrencies();
    String getAll();
    Map<String, BigDecimal> getAllCurrenciesYesterday();
}
