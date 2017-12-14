package com.itmuch.cloud.microservicegatewayzuul.Controller;

import com.google.common.collect.Maps;
import com.itmuch.cloud.microservicegatewayzuul.Service.AggregationService;
import com.itmuch.cloud.microservicegatewayzuul.dto.User;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.context.request.async.DeferredResult;
import rx.Observable;

import java.util.HashMap;
import java.util.Observer;

@Controller
public class AddregationController {

    public static final Logger LOGGER = LoggerFactory.getLogger(AddregationController.class);


    @Autowired
    private AggregationService aggregationService;


    @GetMapping("/aggregate/{id}")
    public DeferredResult<HashMap<String, User>> aggregate(@PathVariable Long id) {
        Observable<HashMap<String, User>> hashMapObservable = this.aggregateObservable(id);
        return this.toDeferredResult(hashMapObservable);
    }

    public Observable<HashMap<String, User>> aggregateObservable(Long id) {
        return Observable.zip(
                this.aggregationService.getUserById(id),
                this.aggregationService.getMovieUserByUserId(id),
                (user, movieUser) -> {
                    HashMap<String, User> map = Maps.newHashMap();
                    map.put("user", user);
                    map.put("movieUser", movieUser);
                    return map;
                }
        );
    }

    public DeferredResult<HashMap<String, User>> toDeferredResult(Observable<HashMap<String, User>> details) {
        DeferredResult<HashMap<String, User>> resul = new DeferredResult<>();
        details.subscribe(new rx.Observer<HashMap<String, User>>() {
            @Override
            public void onCompleted() {
                LOGGER.info("完成");
            }

            @Override
            public void onError(Throwable e) {
                LOGGER.error("发生错误..", e);
            }

            @Override
            public void onNext(HashMap<String, User> stringUserHashMap) {
                resul.setResult(stringUserHashMap);
            }
        });
        return resul;
    }


}
