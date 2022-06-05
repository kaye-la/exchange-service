package com.kayela.exchangeservice.controller;

import com.kayela.exchangeservice.api.service.ExchangeService;
import com.kayela.exchangeservice.api.service.GiphyService;
import com.kayela.exchangeservice.dto.GifDTO;
import com.kayela.exchangeservice.exceptions.ExchangeException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
@RequestMapping("/gifs")
public class GifController {
    private final ExchangeService exchangeService;
    private final GiphyService giphyService;

    @Autowired
    public GifController(ExchangeService exchangeService, GiphyService giphyService) {
        this.exchangeService = exchangeService;
        this.giphyService = giphyService;
    }

    @GetMapping("/get-gif/{currency}")
    public ResponseEntity<GifDTO> getCurrencyGif(@PathVariable String currency) throws ExchangeException {
        BigDecimal diff = exchangeService.getChangeRatio(currency.toUpperCase());

        if (diff == null) {
            throw new ExchangeException("Can't get currency ratio");
        }

        GifDTO gifDto;

        if (diff.compareTo(BigDecimal.ZERO) > 0) {
            gifDto = giphyService.getRandomGif("rich");
        } else {
            gifDto = giphyService.getRandomGif("poor");
        }
        return ResponseEntity.ok(gifDto);
    }
    @GetMapping("/get-all/{currency}")
    public ResponseEntity<String> getGifs(@PathVariable String currency) throws ExchangeException {
        BigDecimal diff = exchangeService.getChangeRatio(currency.toUpperCase());

        return ResponseEntity.ok(diff.toString() +" " + currency);
    }

    @GetMapping("/sss")
    public ResponseEntity<String> getGifssss(@PathVariable String currency) throws ExchangeException {
        BigDecimal diff = exchangeService.getChangeRatio(currency.toUpperCase());

        return ResponseEntity.ok(diff.toString());
    }

    @GetMapping("/qq/{currency}")
    public ResponseEntity<String> getssGifs(@PathVariable String currency) throws ExchangeException {

        return ResponseEntity.ok(currency);
    }
}
