package com.kayela.exchangeservice.exceptions;

import lombok.Getter;

@Getter
public class ExchangeException extends RuntimeException {
    public ExchangeException(String message) {
        super(message);
    }
}
