package com.kayela.exchangeservice.api.service;

import java.math.BigDecimal;
import java.util.Map;


public interface ExchangeService {
    /**
     * Get the latest exchange rates available from the Open Exchange Rates API
     * @return map of currencies rates
     */
    Map<String, BigDecimal> getLatestRates();

    /**
     * Get yesterday exchange rates from the Open Exchange Rates API
     * @return map of yesteday's currencies rates
     */
    Map<String, BigDecimal> getYesterdayRates();

    /**
     * Get difference between today currency rate and yesterday
     * @param currency currency to compare
     * @return difference
     */
    BigDecimal getChangeRatio(String currency);
}
