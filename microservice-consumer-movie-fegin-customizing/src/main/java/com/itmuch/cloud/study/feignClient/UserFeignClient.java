package com.itmuch.cloud.study.feignClient;

import com.itmuch.cloud.config.FeginConfiguration;
import com.itmuch.cloud.study.POJO.User;
import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@FeignClient(name = "microservice-provider-user", configuration = FeginConfiguration.class)
public interface UserFeignClient {

    @GetMapping(value = "{id}")
    public User findById(@PathVariable("id") Long id);

}
