package com.kayela.exchangeservice.api.service;

import java.math.BigDecimal;
import java.util.Map;

/**
 * todo Document type ExchangeService
 */
public interface ExchangeService {
    Map<String, BigDecimal> getLatestRates();
    String getAll();
    Map<String, BigDecimal> getYesterdayRates();
    BigDecimal getChangeRatio(String currency);
    Map<String, BigDecimal> getTemp();
}
