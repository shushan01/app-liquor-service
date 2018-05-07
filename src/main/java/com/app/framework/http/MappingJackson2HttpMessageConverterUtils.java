package com.app.framework.http;

import org.springframework.http.MediaType;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;

import java.util.ArrayList;
import java.util.List;

public class MappingJackson2HttpMessageConverterUtils extends MappingJackson2HttpMessageConverter {
    public MappingJackson2HttpMessageConverterUtils() {
        List<MediaType> mediaTypes = new ArrayList<MediaType>();
        mediaTypes.add(MediaType.TEXT_PLAIN);
        setSupportedMediaTypes(mediaTypes);
    }
}