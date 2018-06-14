package com.cw.kafka.thread;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.SynchronousQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

/**
 * @author david.cai
 * @date 2018/6/14
 **/
@Slf4j
public class ThreadPoolTest {


    private ThreadPoolExecutor executor = null;
    /**
     * SynchronousQueue
     * @throws InterruptedException
     */
    @Test
    public void pool1() throws InterruptedException {
        executor = new ThreadPoolExecutor(6, 10, 5, TimeUnit.SECONDS, new SynchronousQueue<>());
        runTask();
    }

    /**
     * LinkedBlockingDeque
     * @throws InterruptedException
     */
    @Test
    public void pool2() throws InterruptedException {
        executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        runTask();
    }

    /**
     * SynchronousQueue
     * @throws InterruptedException
     */
    @Test
    public void pool3() throws InterruptedException {
        executor = new ThreadPoolExecutor(3, 6, 5, TimeUnit.SECONDS, new SynchronousQueue<>());
        runTask();
    }

    /**
     * LinkedBlockingDeque
     * @throws InterruptedException
     */
    @Test
    public void pool4() throws InterruptedException {
        executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>());
        runTask();
    }

    /**
     * LinkedBlockingDeque with limit 2
     * @throws InterruptedException
     */
    @Test
    public void pool5() throws InterruptedException {
        executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(2));
        runTask();
    }

    /**
     * LinkedBlockingDeque with limit 1
     * @throws InterruptedException
     */
    @Test
    public void pool6() throws InterruptedException {
        executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new LinkedBlockingDeque<>(1));
        runTask();
    }

    /**
     * LinkedBlockingDeque with limit 1
     * @throws InterruptedException
     */
    @Test
    public void pool7() throws InterruptedException {
        executor = new ThreadPoolExecutor(3, 4, 5, TimeUnit.SECONDS, new SynchronousQueue<>());
        runTask();
    }

    void runTask() throws InterruptedException {
        executor.execute(new TestRunable());
        executor.execute(new TestRunable());
        executor.execute(new TestRunable());

        log.info("********启动3个线程********");
        log.info("getCorePoolSize : {}", executor.getCorePoolSize());
        log.info("getPoolSize : {}", executor.getPoolSize());
        log.info("getActiveCount : {}", executor.getActiveCount());
        log.info("getQueue : {}", executor.getQueue().size());

        executor.execute(new TestRunable());
        executor.execute(new TestRunable());
        executor.execute(new TestRunable());

        log.info("********再启动3个线程********");
        log.info("getCorePoolSize : {}", executor.getCorePoolSize());
        log.info("getPoolSize : {}", executor.getPoolSize());
        log.info("getActiveCount : {}", executor.getActiveCount());
        log.info("getQueue : {}", executor.getQueue().size());

        Thread.sleep(8000);
        log.info("--------8秒后--------");
        log.info("getCorePoolSize : {}", executor.getCorePoolSize());
        log.info("getPoolSize : {}", executor.getPoolSize());
        log.info("getActiveCount : {}", executor.getActiveCount());
        log.info("getQueue : {}", executor.getQueue().size());
    }

    static class TestRunable implements Runnable{

        @Override
        public void run() {
            try {
                Thread.sleep(2000);
                log.info("thread name {} , id {} runs", Thread.currentThread().getName(), Thread.currentThread().getId());
            } catch (InterruptedException e) {
                log.info("error : {}", e);
            }
        }
    }

}
