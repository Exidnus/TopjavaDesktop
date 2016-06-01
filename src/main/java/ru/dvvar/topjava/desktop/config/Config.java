package ru.dvvar.topjava.desktop.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

/**
 * Created by Dmitriy_Varygin on 01.06.2016.
 */
@Configuration
@ComponentScan(basePackages = "ru.dvvar.topjava.desktop")
public class Config {

    @Bean
    public RestTemplate restTemplate() {
        return new RestTemplate();
    }
}
