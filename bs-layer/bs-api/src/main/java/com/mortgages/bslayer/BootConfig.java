package com.mortgages.bslayer;

import org.apache.http.client.HttpClient;
import org.apache.http.conn.ssl.NoopHostnameVerifier;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.conn.ssl.TrustSelfSignedStrategy;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.ssl.SSLContextBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.client.HttpComponentsClientHttpRequestFactory;
import org.springframework.web.client.RestTemplate;

import java.io.InputStream;
import java.security.KeyStore;

@Configuration
public class BootConfig {

    @Bean
    public RestTemplate getRestTemplate(){
        System.out.println("BootConfig.getRestTemplate");

        RestTemplate restTemplate = new RestTemplate();
        KeyStore keyStore;
        HttpComponentsClientHttpRequestFactory requestFactory = null;

        try{
            keyStore = KeyStore.getInstance("jks");
            ClassPathResource resource = new ClassPathResource("mortgage-gateway.jks");
            InputStream stream = resource.getInputStream();

            keyStore.load(stream, "nt-gateway".toCharArray());

            SSLConnectionSocketFactory socketFactory = new SSLConnectionSocketFactory(new SSLContextBuilder()
                    .loadTrustMaterial(null, new TrustSelfSignedStrategy())
                    .loadKeyMaterial(keyStore, "nt-gateway".toCharArray()).build(),
                    NoopHostnameVerifier.INSTANCE);

            HttpClient httpClient = HttpClients.custom().setSSLSocketFactory(socketFactory)
                    .setMaxConnTotal(Integer.valueOf(5))
                    .setMaxConnPerRoute(Integer.valueOf(5))
                    .build();

            requestFactory = new HttpComponentsClientHttpRequestFactory(httpClient);
            requestFactory.setReadTimeout(10000);
            requestFactory.setConnectTimeout(10000);

            restTemplate.setRequestFactory(requestFactory);
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
        }catch(Exception e){
            System.out.println("Service.getRestTemplate" + e);
            e.printStackTrace();
        }
        return restTemplate;
    }

}