package com.itmuch.cloud.config;


import feign.Contract;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class FeginConfiguration {

    @Bean
    public Contract feginContract(){
        return new Contract.Default();
    }
}
