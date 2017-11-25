package com.itmuch.cloud.study.Controller;

import com.itmuch.cloud.study.POJO.User;
import com.itmuch.cloud.study.feignClient.UserFeignClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;


@RestController
public class MoiveController {
    @Autowired
    private UserFeignClient userFeignClient;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;

    @GetMapping("/user/{id}")
    public User findById(@PathVariable("id") long id) {
        return userFeignClient.findById(id);
    }

}
