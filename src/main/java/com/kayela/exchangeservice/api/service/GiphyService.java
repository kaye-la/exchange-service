package com.kayela.exchangeservice.api.service;

import com.kayela.exchangeservice.dto.GifDTO;
import com.kayela.exchangeservice.exceptions.GifException;

import java.util.List;

/**
 * todo Document type GiphyService
 */
public interface GiphyService {
    List<GifDTO> getGifs(String search, int limit);

    GifDTO getRandomGif(String search) throws GifException;
}
