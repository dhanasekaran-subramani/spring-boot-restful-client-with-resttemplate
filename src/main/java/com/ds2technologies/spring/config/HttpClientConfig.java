package com.ds2technologies.spring.config;

import org.apache.http.client.HttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import javax.naming.ConfigurationException;

@Configuration
public class HttpClientConfig {


    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 1000;

    @Autowired
    private PoolingHttpClientConnectionManager poolingHttpClientConnectionManager;

    @Bean
    public HttpClient httpClient() throws ConfigurationException {
        HttpClient defaultHttpClient = HttpClientBuilder.create()
                .setConnectionManager(poolingHttpClientConnectionManager)
                .setMaxConnPerRoute(DEFAULT_MAX_TOTAL_CONNECTIONS)
                //.setConnectionTimeToLive()
                .disableConnectionState()
                .build();

        return defaultHttpClient;
    }


}
