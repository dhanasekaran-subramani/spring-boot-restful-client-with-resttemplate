package com.ds2technologies.spring.config;

import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.Resource;

import javax.naming.ConfigurationException;
import javax.net.ssl.SSLContext;
import java.io.IOException;
import java.security.GeneralSecurityException;
import java.security.KeyStore;

@Configuration
public class SSLContextConfig {

    @Value("${ssl.keystore.file}")
    private Resource keyStorePath;

    @Value("${ssl.keystore.password}")
    private String keyStorePassword;

    @Value("${ssl.truststore.password}")
    private String trustStorePassword;

    @Value("${ssl.truststore.file}")
    private Resource trustStorePath;

    @Bean
    public SSLContext sslContext() throws ConfigurationException {

        try {
            return new SSLContextBuilder()
                    .loadKeyMaterial(creatKeyStore(keyStorePath, "JKS",
                            keyStorePassword), keyStorePassword.toCharArray())
                    .loadTrustMaterial(creatKeyStore(trustStorePath, "JKS",
                            trustStorePassword), TrustSelfSignedStrategy.INSTANCE)
                    .build();
        } catch (GeneralSecurityException | IOException e) {
            throw new ConfigurationException(e.getMessage());
        }
    }

    private KeyStore creatKeyStore(Resource resource, String storeType,
                                   String password) throws IOException, GeneralSecurityException {
        KeyStore keyStore = KeyStore.getInstance(storeType);
        keyStore.load(resource.getInputStream(), password.toCharArray());

        return keyStore;
    }
}
