package com.cw.thread;

/**
 * @author david.cai
 * @date 2020/8/6
 */
public class NumberThread extends Thread {
    private Object obj;
    private volatile Boolean flag;

    public NumberThread(Object obj, Boolean flag){
        this.obj = obj;
        this.flag = flag;
    }

    @Override
    public void run() {
        int num = 0;
        while (num < 5) {
            synchronized (obj) {
                System.out.println("N:" + flag);
                if (flag) {
                    System.out.println(num ++);
                    flag = false;
                    obj.notifyAll();
                } else {
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
    }
}
