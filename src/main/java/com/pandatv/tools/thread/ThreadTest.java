package com.pandatv.tools.thread;

/**
 * @author: likaiqing
 * @create: 2019-01-18 14:58
 **/
public class ThreadTest {
    @Test
    public void test1() {
        Thread thread1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
                Thread.yield();
                System.out.println(Thread.currentThread().getName() + " yirld之后重新获取cpu");
                try {
                    wait(10000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
//                try {
//                    Thread.sleep(10000);
//                } catch (InterruptedException e) {
//                    System.out.println("sleep被打断");
//                    e.printStackTrace();
//                }
                System.out.println(Thread.currentThread().getName() + " sleep之后重新获取cpu");
            }
        });
        Thread thread2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println(Thread.currentThread().getName());
            }
        });
        thread1.setName("test1");
        thread2.setName("test2");
        thread1.start();
//        try {
//            Thread.sleep(500);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
        thread2.start();
        System.out.println("over");
    }
}
