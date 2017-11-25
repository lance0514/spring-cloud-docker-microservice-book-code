package com.itmuch.cloud.study.config;

import feign.Logger;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.context.annotation.Bean;

@Configurable
public class FeignLogConfiguration {
    @Bean
    Logger.Level feignlogConfiguration() {
        return Logger.Level.FULL;
    }
}
