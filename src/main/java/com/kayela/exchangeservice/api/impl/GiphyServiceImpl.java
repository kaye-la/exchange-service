package com.kayela.exchangeservice.api.impl;

import com.kayela.exchangeservice.api.GiphyApi;
import com.kayela.exchangeservice.api.service.GiphyService;
import com.kayela.exchangeservice.dto.GifDTO;
import com.kayela.exchangeservice.dto.GiphyDTO;
import com.kayela.exchangeservice.exceptions.GifException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Random;


@Service
public class GiphyServiceImpl implements GiphyService {
    private final int LIMIT = 50;
    private GiphyApi giphyApi;
    @Value("${giphy.service.key}")
    private String apiKey;

    @Autowired
    public GiphyServiceImpl(GiphyApi giphyApi) {
        this.giphyApi = giphyApi;
    }

    @Override
    public GiphyDTO getGifs(String search, int limit) {
        GiphyDTO gifs = giphyApi.getGifs(apiKey, search, limit);
        return gifs;
    }

    @Override
    public GifDTO getRandomGif(String search) throws GifException {
        List<GifDTO> gifs = getGifs(search, LIMIT).getData();

        if (gifs.isEmpty()) {
            throw new GifException("Gif API problem. Gifs are empty");
        }
        Random rnd = new Random();
        return gifs.get(rnd.nextInt(gifs.size()));
    }
}
