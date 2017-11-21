package com.itmuch.cloud.study.Controller;

import com.itmuch.cloud.study.POJO.User;
import org.apache.log4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.client.loadbalancer.LoadBalancerClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
public class MoiveController {

    private static final Logger LOGGER = LoggerFactory.getLogger(MoiveController.class)

    @Autowired
    private RestTemplate restTemplate;

    @Value("${user.userServiceUrl}")
    private String userServiceUrl;


    @Autowired
private LoadBalancerClient loadBalancerClientl
    @GetMapping("/user/{id}")
    public User findById(@PathVariable("id") long id) {
        return restTemplate.getForObject(userServiceUrl + id, User.class);
    }


    @GetMapping("/log-instance")
    public  void longUserInstance(){
        ServiceInstance serviceInstance = loadBalancerClientl.choose("micoservice-provider-user");

        MoiveController.LOGGER.info("{}:{}:{}",serviceInstance.getHost(),serviceInstance.getServiceId(),serviceInstance.getPort());


    }

}
