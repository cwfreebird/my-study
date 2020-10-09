package com.cw.thread;

/**
 * @author david.cai
 * @date 2020/8/6
 */
public class ThreadPrintTest {
    private static Integer num = 0;
    private static Boolean flag = true;
    private static Byte[] block = new Byte[0];
    private static String[] str = new String[]{"A", "B", "C", "D", "E", "F", "G"};

    public static void main(String[] args) throws InterruptedException {
        new Thread(() -> {
            while (num < 5){
                synchronized (block){
                    if (flag){
                        System.out.println(num);
                        flag = false;
                        block.notifyAll();
                    } else {
                        try {
                            block.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                }
            }
        }).start();

        new Thread(() -> {
            while (num < 5){
                synchronized (block){
                    if (flag){
                        try {
                            block.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    } else {
                        System.out.println(str[num]);
                        num ++;
                        flag = true;
                        block.notifyAll();
                    }
                }
            }
        }).start();

        Thread.sleep(1000 * 10);
    }

}
