package com.ds2technologies.spring.config;

import org.apache.http.config.Registry;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class PoolingHttpClientConnectionManagerConfig {


    private static final int DEFAULT_MAX_TOTAL_CONNECTIONS = 1000;

    private static final int DEFAULT_MAX_CONNECTIONS_PER_ROUTE = 50;

    @Autowired
    private Registry<ConnectionSocketFactory> connectionSocketFactoryRegistry;

    @Bean
    public PoolingHttpClientConnectionManager poolingHttpClientConnectionManager() {
        PoolingHttpClientConnectionManager poolingHttpClientConnectionManager = new PoolingHttpClientConnectionManager(connectionSocketFactoryRegistry);
        poolingHttpClientConnectionManager.setMaxTotal(DEFAULT_MAX_TOTAL_CONNECTIONS);
        poolingHttpClientConnectionManager.setDefaultMaxPerRoute(DEFAULT_MAX_CONNECTIONS_PER_ROUTE);
        return poolingHttpClientConnectionManager;
    }
}
