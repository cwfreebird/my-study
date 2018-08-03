package com.cw.jmockit;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author david.cai
 * @date 2018/7/31
 **/
@Component
@Slf4j
public class HelloApi {
    public String sayHello(String name){
        log.info("====HelloApi====");
        return "Hello " + name;
    }
}
