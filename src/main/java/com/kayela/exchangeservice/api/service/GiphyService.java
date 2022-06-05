package com.kayela.exchangeservice.api.service;

import com.kayela.exchangeservice.dto.GifDTO;
import com.kayela.exchangeservice.dto.GiphyDTO;
import com.kayela.exchangeservice.exceptions.GifException;

/**
 * todo Document type GiphyService
 */
public interface GiphyService {
    GiphyDTO getGifs(String search, int limit);

    GifDTO getRandomGif(String search) throws GifException;
}
