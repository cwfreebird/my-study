package com.cw.dubbo.adaptive;

import org.apache.dubbo.common.URL;
import org.apache.dubbo.common.extension.Adaptive;
import org.apache.dubbo.common.extension.SPI;

/**
 * @author david.cai
 * @date 2020/8/3
 */
@SPI("dubbo")
public interface IAdaptive {
    //@Adaptive({"t"})
    @Adaptive
    String hello(String msg, URL url);
}
