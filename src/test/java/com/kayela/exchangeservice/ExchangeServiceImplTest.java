package com.kayela.exchangeservice;

import com.kayela.exchangeservice.api.ExchangeApi;
import com.kayela.exchangeservice.api.service.ExchangeService;
import com.kayela.exchangeservice.dto.RatesDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

@SpringBootTest
public class ExchangeServiceImplTest {
    @MockBean
    ExchangeApi exchangeApi;

    @Autowired
    private ExchangeService exchangeService;

    @Value("${oxr.service.id}")
    private String exchangeAppID;

    @Value("${oxr.service.currency}")
    private String base;

    @Test
    void getLatestRates() {
        Map<String, BigDecimal> expectedRates = new HashMap<>();
        expectedRates.put("AED", new BigDecimal(3.6731));
        expectedRates.put("EUR", new BigDecimal(0.935404));
        expectedRates.put("HKD", new BigDecimal(7.844185));
        expectedRates.put("RUB", new BigDecimal(61.625001));

        Mockito.when(exchangeApi.getLatestRates(exchangeAppID, base)).thenReturn(new RatesDTO(base, expectedRates));
        Map<String, BigDecimal> latestRates = exchangeService.getLatestRates();

        Assert.isTrue(latestRates.equals(expectedRates), "Latest rates should be equal to expected rates");
    }

    @Test
    void getChangeRatio() {
        String yesterdayDate = getYesterdayDate();
        Map<String, BigDecimal> expectedRates = new HashMap<>();
        expectedRates.put("AED", new BigDecimal(3.6731));
        expectedRates.put("EUR", new BigDecimal(0.935404));
        expectedRates.put("HKD", new BigDecimal(7.844185));
        expectedRates.put("RUB", new BigDecimal(61.625001));
        Map<String, BigDecimal> yesterdayRates = new HashMap<>();
        yesterdayRates.put("AED", new BigDecimal(5.6731));
        yesterdayRates.put("EUR", new BigDecimal(0.935404));
        yesterdayRates.put("HKD", new BigDecimal(7.844185));
        yesterdayRates.put("RUB", new BigDecimal(2.625001));

        Mockito.when(exchangeApi.getLatestRates(exchangeAppID, base))
            .thenReturn(new RatesDTO(base, expectedRates));
        Mockito.when(exchangeApi.getRatesByDate(yesterdayDate, exchangeAppID, base))
            .thenReturn(new RatesDTO(base, yesterdayRates));
        BigDecimal aedRate = exchangeService.getChangeRatio("AED");
        BigDecimal eurRate = exchangeService.getChangeRatio("EUR");
        BigDecimal hkdRate = exchangeService.getChangeRatio("HKD");
        BigDecimal rubRate = exchangeService.getChangeRatio("RUB");

        Assert.isTrue(aedRate.compareTo(BigDecimal.ZERO) < 0,
            "AED rate < AED yesterday rate");
        Assert.isTrue(eurRate.compareTo(BigDecimal.ZERO) == 0,
            "rub rate = rub yesterday rate");
        Assert.isTrue(hkdRate.compareTo(BigDecimal.ZERO) == 0,
            "hkd rate = hkd yesterday rate");
        Assert.isTrue(rubRate.compareTo(BigDecimal.ZERO) > 0,
            "rub rate < rub yesterday rate");
    }

    // ===================================================================================================================
    // = Utils
    // ===================================================================================================================

    private String getYesterdayDate() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DATE, -1);
        return dateFormat.format(cal.getTime());
    }
}
