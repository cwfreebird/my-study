package com.cw.jmockit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author david.cai
 * @date 2018/7/31
 **/
@Service
@Slf4j
public class HelloService {
    @Autowired
    HelloApi helloApi;

    public String sayHello(String name){
        log.info("====HelloService====");
        return helloApi.sayHello(name);
    }
}
