package com.cw.thread;

/**
 * @author david.cai
 * @date 2020/8/6
 */
public class CharacterThread extends Thread {
    private Object obj;
    private String[] str = new String[]{"A", "B", "C", "D", "E", "F", "G"};
    private volatile Boolean flag;

    public CharacterThread(Object obj, Boolean flag){
        this.obj = obj;
        this.flag = flag;
    }

    @Override
    public void run() {
        int num = 0;
        while (num < 5) {
            synchronized (obj) {
                System.out.println("c:" + flag);
                if (flag){
                    try {
                        obj.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                } else {
                    System.out.println(str[num ++]);
                    flag = true;
                    obj.notifyAll();
                }
            }
        }
    }
}
