package com.kayela.exchangeservice.exceptions;

import lombok.Getter;

@Getter
public class GifException extends RuntimeException {
    public GifException(String message) {
        super(message);
    }
}
