package com.pandatv.tools.thread;

/**
 * @author: likaiqing
 * @create: 2019-01-18 18:02
 * join方法的作用是父线程等待子线程执行完成后再执行，换句话说就是将异步执行的线程合并为同步的线程
 *join方法就是通过wait方法来将线程的阻塞，如果join的线程还在执行，则将当前线程阻塞起来，直到join的线程执行完成，当前线程才能执行。不过有一点需要注意，这里的join只调用了wait方法，却没有对应的notify方法，原因是Thread的start方法中做了相应的处理，所以当join的线程执行完成以后，会自动唤醒主线程继续往下执行
 **/
public class JoinTest implements Runnable {
    @Override
    public void run() {

        try {
            System.out.println(Thread.currentThread().getName() + " start-----");
            Thread.sleep(1000);
            System.out.println(Thread.currentThread().getName() + " end------");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
//        for (int i = 0; i < 5; i++) {
//            Thread test = new Thread(new JoinTest());
//            test.start();
//        }
        for (int i = 0; i < 5; i++) {
            Thread test = new Thread(new JoinTest());
            test.start();
            try {
                test.join();
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("Finished~~~");
    }
}

