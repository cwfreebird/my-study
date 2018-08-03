package com.cw;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

/**
 * @author david.cai
 * @date 2018/8/3
 **/
@Slf4j
public class TestAll {
    @Test
    public void byteTest(){
        log.info(Byte.toString((byte)6));
    }
}
