package com.pandatv.tools.thread;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Random;

/**
 * @author: likaiqing
 * @create: 2019-01-18 16:20
 * 你可以使用wait和notify函数来实现线程间通信
 * 永远在synchronized的函数或对象里使用wait、notify和notifyAll(这是JDK强制的，wait()方法和notify()/notifyAll()方法在调用前都必须先获得对象的锁)，不然Java虚拟机会生成 IllegalMonitorStateException
 * 永远在while循环里而不是if语句下使用wait。这样，循环会在线程睡眠前后都检查wait的条件，并在条件实际上并未改变的情况下处理唤醒通知
 *  永远在多线程间共享的对象（在生产者消费者模型里即缓冲区队列）上使用wait
 *  更倾向用 notifyAll()，而不是 notify()
 *
 *  wait()方法和notify()/notifyAll()方法在放弃对象监视器的时候的区别在于：wait()方法立即释放对象监视器，notify()/notifyAll()方法则会等待线程剩余代码执行完毕才会放弃对象监视器。
 **/
public class ProducerConsumerInJava {
    public static void main(String[] args) {
        System.out.println("How use wait and notify method in Java");
        System.out.println("Solving Producer Consumer Problem");
        Queue<Integer> buffer = new LinkedList<>();
        int maxSize = 10;
        Thread producer = new Producer(buffer, maxSize, "PRODUCER");
        Thread consumer = new Consumer(buffer, maxSize, "CONSUMER");
        producer.start();
        consumer.start();
    }

    static class Producer extends Thread {
        private Queue<Integer> queue;
        private int maxSize;

        public Producer(Queue<Integer> queue, int maxSize, String name) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.size() == maxSize) {
                        try {
                            System.out.println("Queue is full, " + "Producer thread waiting for " + "consumer to take something from queue");
                            queue.wait();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                    Random random = new Random();
                    int i = random.nextInt();
                    System.out.println("Producing value :" + i);
                    queue.add(i);
                    queue.notifyAll();
                }
            }
        }
    }

    static class Consumer extends Thread {
        private Queue<Integer> queue;
        private int maxSize;

        public Consumer(Queue<Integer> queue, int maxSize, String name) {
            super(name);
            this.queue = queue;
            this.maxSize = maxSize;
        }

        @Override
        public void run() {
            while (true) {
                synchronized (queue) {
                    while (queue.isEmpty()) {
                        System.out.println("Queue is empty," + "Consumer thread is waiting" + " for producer thread to put something in queue");
                        try {
                            queue.wait();
                        } catch (InterruptedException e) {
                            e.printStackTrace();
                        }
                    }
                    System.out.println("Consuming value:" + queue.remove());
                    queue.notifyAll();
                }
            }
        }
    }
}
