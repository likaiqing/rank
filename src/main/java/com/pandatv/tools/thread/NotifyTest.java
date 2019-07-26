package com.pandatv.tools.thread;

/**
 * @author: likaiqing
 * @create: 2019-01-18 16:59
 * wait方法依赖于同步，而sleep方法可以直接调用，wait不使用同步会报错(java.lang.IllegalMonitorStateException)
 **/
public class NotifyTest {

    public synchronized void testWait() {
        System.out.println(Thread.currentThread().getName() + " Start-----");
        try {
            wait(10000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(Thread.currentThread().getName() + " End-------");
    }

    public static void main(String[] args) throws InterruptedException {
        final NotifyTest test = new NotifyTest();
        for (int i = 0; i < 5; i++) {
            new Thread(new Runnable() {
                @Override
                public void run() {
                    test.testWait();
                }
            }).start();
        }
        Thread.sleep(1000);
        System.out.println("-----");
        synchronized (test) {
            test.notify();
        }
        Thread.sleep(2000);
        System.out.println("------分割线-------");
        synchronized (test) {
            test.notifyAll();
        }
    }
}
