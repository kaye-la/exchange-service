package com.kayela.exchangeservice;

import com.kayela.exchangeservice.api.GiphyApi;
import com.kayela.exchangeservice.api.service.GiphyService;
import com.kayela.exchangeservice.dto.GifDTO;
import com.kayela.exchangeservice.dto.GiphyDTO;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.util.Assert;

import java.util.ArrayList;
import java.util.List;

@SpringBootTest
public class GiphyServiceImplTest {
    @MockBean
    GiphyApi giphyApi;

    @Autowired
    GiphyService giphyService;

    @Value("${giphy.service.key}")
    private String apiKey;

    @Test
    void getGifs()
    {
        List<GifDTO> expectedgifs = new ArrayList<>();
        expectedgifs.add(new GifDTO("1", "khank1"));
        expectedgifs.add(new GifDTO("2", "khank2"));
        expectedgifs.add(new GifDTO("3", "khank3"));
        expectedgifs.add(new GifDTO("4", "khank4"));

        Mockito.when(giphyApi.getGifs(apiKey, "broke", 4)).thenReturn(new GiphyDTO(expectedgifs));
        List<GifDTO> gifs = giphyService.getGifs("broke", 4);

        Assert.isTrue(gifs.equals(expectedgifs), "Gifs should be equal to expected gifs");
    }

    @Test
    void getRandomGif()
    {
        List<GifDTO> gifs = new ArrayList<>();
        gifs.add(new GifDTO("1", "khank1"));
        gifs.add(new GifDTO("2", "khank2"));
        gifs.add(new GifDTO("3", "khank3"));
        gifs.add(new GifDTO("4", "khank4"));

        Mockito.when(giphyApi.getGifs(apiKey, "broke", 50)).thenReturn(new GiphyDTO(gifs));
        GifDTO gif = giphyService.getRandomGif("broke");

        Assert.isTrue(gif.getId().equals("1") || gif.getId().equals("2")
            || gif.getId().equals("3") || gif.getId().equals("4"),
            "Gifs should be with id from 1 to 4");
    }
}
