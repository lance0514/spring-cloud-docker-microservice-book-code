package com.itmuch.cloud.microservicegatewayzuul.Service;

import com.itmuch.cloud.microservicegatewayzuul.dto.User;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import rx.Observable;
import rx.Observer;


@Service
public class AggregationService {
    @Autowired
    private RestTemplate restTemplate;

    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getUserById(Long id){
    return Observable.create(observer -> {
        User user = restTemplate.getForObject("http://microservice-provide-user/{id}", User.class, id);
        observer.onNext(user);
        observer.onCompleted();

    });
    }
    @HystrixCommand(fallbackMethod = "fallback")
    public Observable<User> getMovieUserByUserId(Long id){
        return Observable.create(observer -> {
            User user = restTemplate.getForObject("http://microservice-consumer-movice/user/{id}", User.class, id);
            observer.onNext(user);
            observer.onCompleted();

        });
    }

    public User fallback(Long id){
        User user = new User();
        user.setId(-1L);
        return user;
    }

}
