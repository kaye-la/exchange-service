package com.kayela.exchangeservice.api.impl;

import com.kayela.exchangeservice.api.ExchangeApi;
import com.kayela.exchangeservice.api.service.ExchangeService;
import com.kayela.exchangeservice.dto.RatesDTO;
import com.kayela.exchangeservice.exceptions.ExchangeException;
import feign.FeignException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@Service
public class ExchangeServiceImpl implements ExchangeService {
    private final ExchangeApi exchangeApi;

    @Value("${oxr.service.id}")
    private String exchangeAppID;

    @Value("${oxr.service.currency}")
    private String baseCurrency;

    @Autowired
    public ExchangeServiceImpl(ExchangeApi exchangeApi) {
        this.exchangeApi = exchangeApi;
    }

    @Override
    public Map<String, BigDecimal> getLatestRates() {
        RatesDTO ratesDTO;

        try {
            ratesDTO = exchangeApi.getLatestRates(exchangeAppID, baseCurrency);
        } catch (FeignException ex) {
            return new HashMap<>();
        }
        return ratesDTO.getRates();
    }

    @Override
    public Map<String, BigDecimal> getYesterdayRates() {
        RatesDTO ratesDto;

        try {
            String date = getYesterdayDate();
            ratesDto = exchangeApi.getRatesByDate(date, exchangeAppID, baseCurrency);
        } catch (FeignException ex) {
            return new HashMap<>();
        }
        return ratesDto.getRates();
    }

//    @Override
//    public String getAll() {
//        return getYesterdayDate();
//    }

//    @Override
//    public Map<String, BigDecimal> getTemp() {
//        return getYesterdayRates();
//    }
    private String getYesterdayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }

    @Override
    public BigDecimal getChangeRatio(String currency) throws ExchangeException {
        BigDecimal newRate = getLatestRates().get(currency);
        if (newRate == null) {
            throw new ExchangeException("Exchange exception. Latest rates are empty");
        }

        BigDecimal oldRate = getYesterdayRates().get(currency);
        if (oldRate == null) {
            throw new ExchangeException("Exchange exception. Old rates are empty");
        }
        return newRate.subtract(oldRate);
    }
}
