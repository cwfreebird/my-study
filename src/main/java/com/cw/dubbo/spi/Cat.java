package com.cw.dubbo.spi;

/**
 * @author david.cai
 * @date 2020/8/5
 */
public class Cat implements Animal {
    @Override
    public String yield(String msg) {
        return "喵喵喵";
    }
}
