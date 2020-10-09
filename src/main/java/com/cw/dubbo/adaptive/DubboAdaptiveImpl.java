package com.cw.dubbo.adaptive;

import org.apache.dubbo.common.URL;

/**
 * @author david.cai
 * @date 2020/8/3
 */
public class DubboAdaptiveImpl implements IAdaptive {

    @Override
    public String hello(String msg, URL url) {
        return "dubbo";
    }
}
