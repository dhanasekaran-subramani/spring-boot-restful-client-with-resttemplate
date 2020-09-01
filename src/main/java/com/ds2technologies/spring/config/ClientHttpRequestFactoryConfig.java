package com.ds2technologies.spring.config;

import org.apache.http.client.HttpClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.http.client.ClientHttpRequestFactory;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;

import javax.naming.ConfigurationException;

@Configuration
public class ClientHttpRequestFactoryConfig {

    @Autowired
   private  HttpClient httpClient;

    @Bean
    public ClientHttpRequestFactory httpRequestFactory() throws ConfigurationException {
        return new HttpComponentsClientHttpRequestFactory(httpClient);
    }
}
