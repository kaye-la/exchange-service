package com.kayela.exchangeservice.api.impl;

import com.kayela.exchangeservice.api.ExchangeApi;
import com.kayela.exchangeservice.api.service.ExchangeService;
import com.kayela.exchangeservice.dto.RatesDTO;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeApi exchangeApi;

    @Value("${oxr.service.id}")
    private String exchangeAppID;

    @Value("${oxr.service.currency}")
    private String baseCurrency;

    public ExchangeServiceImpl(ExchangeApi exchangeApi) {
        this.exchangeApi = exchangeApi;
    }

    @Override
    public Map<String, BigDecimal> getAllCurrencies() {
        RatesDTO ratesDTO;
        try {
            ratesDTO = exchangeApi.getAllCurrencies(exchangeAppID, baseCurrency);
        } catch (FeignException ex) {
            return new HashMap<>();
        }
        return ratesDTO.getRates();
    }

    @Override
    public Map<String, BigDecimal> getAllCurrenciesYesterday() {
        RatesDTO ratesDto;
        try {
            String date = getYesterdayDate();
            ratesDto = exchangeApi.getAllCurrenciesByDate(date, exchangeAppID, baseCurrency);
        } catch (FeignException ex) {
            return new HashMap<>();
        }

        return ratesDto.getRates();
    }

    @Override
    public String getAll() {
        return getYesterdayDate();
    }

    private String getYesterdayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }
}
