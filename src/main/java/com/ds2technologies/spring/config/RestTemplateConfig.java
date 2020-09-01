package com.ds2technologies.spring.config;

import com.ds2technologies.spring.exceptions.RestResponseErrorHandler;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
import org.springframework.web.client.RestTemplate;

import java.util.List;

@Slf4j
@Configuration
public class RestTemplateConfig {

    @Autowired
    private ObjectMapper objectMapper;

    @Autowired
    private ClientHttpRequestFactory httpRequestFactory;

    @Bean
    public RestTemplate restTemplate() {
        RestTemplate restTemplate = new RestTemplate(httpRequestFactory);
        List<HttpMessageConverter<?>> converters = restTemplate
                .getMessageConverters();

        for (HttpMessageConverter<?> converter : converters) {
            if (converter instanceof MappingJackson2HttpMessageConverter) {
                MappingJackson2HttpMessageConverter jsonConverter = (MappingJackson2HttpMessageConverter) converter;
                jsonConverter.setObjectMapper(objectMapper);
            }
        }
        restTemplate.setErrorHandler(new RestResponseErrorHandler());

        return restTemplate;
    }

}
