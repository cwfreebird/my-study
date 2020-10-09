package com.cw.dubbo.adaptive;

import org.apache.dubbo.common.URL;

/**
 * @author david.cai
 * @date 2020/8/3
 */
//@Adaptive
public class ThriftAdaptiveImpl implements IAdaptive {
    @Override
    public String hello(String msg, URL url) {
        return "thrift";
    }
}
