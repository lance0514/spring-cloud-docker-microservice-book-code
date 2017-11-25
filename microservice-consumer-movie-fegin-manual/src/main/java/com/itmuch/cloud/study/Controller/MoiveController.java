package com.itmuch.cloud.study.Controller;

import com.itmuch.cloud.study.POJO.User;
import com.itmuch.cloud.study.feignClient.UserFeignClient;
import feign.Client;
import feign.Contract;
import feign.Feign;
import feign.auth.BasicAuthRequestInterceptor;
import feign.codec.Decoder;
import feign.codec.Encoder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.netflix.feign.FeignClientsConfiguration;
import org.springframework.context.annotation.Import;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;


@RestController
@Import(FeignClientsConfiguration.class)
public class MoiveController {
    private UserFeignClient userUserFeignClient;

    private UserFeignClient adminUserFeignClient;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    public MoiveController(Decoder decoder, Encoder encoder, Client client, Contract contract) {
         userUserFeignClient = Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("user", "password1"))
                .target(UserFeignClient.class, "http://microservice-provider-user");
        adminUserFeignClient = Feign.builder()
                .client(client)
                .encoder(encoder)
                .decoder(decoder)
                .contract(contract)
                .requestInterceptor(new BasicAuthRequestInterceptor("admin", "password2"))
                .target(UserFeignClient.class, "http://microservice-provider-user");
    }

    @GetMapping("/user-user/{id}")
    public User findByIdUser(@PathVariable("id") long id) {
        return userUserFeignClient.findById(id);
    }

    @GetMapping("/admin-user/{id}")
    public User findByIdAdmin(@PathVariable("id") long id) {
        return adminUserFeignClient.findById(id);
    }

}
