package com.kayela.exchangeservice.api.service;

import com.kayela.exchangeservice.dto.GifDTO;
import com.kayela.exchangeservice.exceptions.GifException;

import java.util.List;


public interface GiphyService {
    /**
     * Return a list of gifs consist of limit
     * @param search Search query term or phrase
     * @param limit The maximum number of objects to return
     * @return list of gifs
     */
    List<GifDTO> getGifs(String search, int limit);

    /**
     * Return a random gif from list of gifs(getGifs)
     * @param search Search query term or phrase
     * @return gif
     * @throws GifException exception
     */
    GifDTO getRandomGif(String search) throws GifException;
}
