package com.cw.thread;

import org.junit.Test;

/**
 * @author david.cai
 * @date 2020/8/7
 */
public class ThreadTest {
    Object obj = new Object();
    volatile boolean flag = true;
    @Test
    public void print() throws InterruptedException {

        NumberThread n = new NumberThread(obj, flag);
        CharacterThread c = new CharacterThread(obj, flag);

        n.start();
        c.start();

        Thread.sleep(1000 * 10);
    }
}
