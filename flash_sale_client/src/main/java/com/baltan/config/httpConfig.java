package com.baltan.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-10-15 20:14
 */
@Configuration
public class httpConfig {
    @Bean
    public RestTemplate restTemplate() {
        List<HttpMessageConverter<?>> converterList = new ArrayList<>();
        HttpMessageConverter<?> converter = new MappingJackson2HttpMessageConverter();
        converterList.add(converter);
        RestTemplate restTemplate = new RestTemplate(converterList);
        return restTemplate;
    }
}
