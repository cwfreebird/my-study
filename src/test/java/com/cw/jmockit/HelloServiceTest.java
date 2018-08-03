package com.cw.jmockit;

import lombok.extern.slf4j.Slf4j;
import mockit.Injectable;
import mockit.Mock;
import mockit.MockUp;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.AbstractJUnit4SpringContextTests;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;



/**
 * @author david.cai
 * @date 2018/7/31
 **/
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath*:applicationContext.xml" )
@Slf4j
public class HelloServiceTest{

    @Autowired
    HelloService helloService;

    @Test
    public void mockSayHello(){
        new MockUp<HelloApi>(){
            @Mock
            public String sayHello(String name){
                log.info("=========mock sayHello=====");
                return "Mock Hi " + name;
            }
        };
        log.info("{}", helloService.sayHello("david"));
    }

    @Test
    public void sayHi(){
        new MockUp<HelloConstants>(){
            @Mock
            public String sayHi(String name){
                log.info("=========mock sayHi=====");
                return "Mock Hi " + name;
            }
        };
        log.info("{}", HelloConstants.sayHi("david"));
    }
}
