package com.itmuch.cloud.study.config;

import com.itmuch.cloud.configuration.RibbonConfiguration;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.cloud.netflix.ribbon.RibbonClient;

@Configurable
@RibbonClient(name="microservice-provider-user",configuration= RibbonConfiguration.class)
public class TestConfiguration {
}
