package com.pandatv.tools.thread;

/**
 * @author: likaiqing
 * @create: 2019-03-16 23:27
 **/
public class Test {
    static int index = 0;

    public static void main(String[] args) {

        ConTest conTest = new ConTest();
        Object obj = new Object();
//        boolean flag = false;
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
                    synchronized (obj) {///必须使用同步，否则wait会报错
                        while (true) {
                            if (index++ % 2 == 1) {
                                System.out.println(Thread.currentThread().getName() + i);
                                obj.notifyAll();
                                if (i < 9) {
                                    try {
                                        obj.wait();
                                    } catch (InterruptedException e) {

                                    }
                                }
                                break;
                            } else {
                                try {
                                    obj.wait();
                                } catch (InterruptedException e) {

                                }
                            }
                        }
                    }
                }
            }
        }).start();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i < 10; i++) {
//                    if (index % 2 != 1) {
//                        try {
//                            obj.wait();
//                        } catch (InterruptedException e) {
//
//                        }
//                    }
                    synchronized (obj) {
                        while (true) {
                            if (index++ % 2 == 0) {
                                System.out.println(Thread.currentThread().getName() + (char) (i + 65));
                                obj.notifyAll();
                                if (i < 9) {
                                    try {
                                        obj.wait();
                                    } catch (InterruptedException e) {

                                    }
                                }
                                break;
                            }
                        }
                    }
                }
            }
        }).start();

    }
}

class ConTest {

    public void print(String i) {
        System.out.println(i);
    }
}
