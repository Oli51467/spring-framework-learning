package com.sdu.springframework.concurrent;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.Semaphore;
import java.util.concurrent.atomic.AtomicInteger;
import java.util.concurrent.locks.LockSupport;

public class AlternatePrinting {

    private final static AtomicInteger atomicInteger = new AtomicInteger();
    private final static int PRINT_COUNT = 100;
    private static final Semaphore semaphoreA = new Semaphore(1);
    private static final Semaphore semaphoreB = new Semaphore(0);

    /**
     * 使用LockSupport进行交替打印
     */
    private static void useLockSupport() {
        Thread[] threads = new Thread[2];
        AtomicInteger count = new AtomicInteger();
        threads[0] = new Thread(() -> {
            for (int i = 0; i < 50; i ++ ) {
                count.incrementAndGet();
                System.out.println("A \t" + count.get());
                LockSupport.unpark(threads[1]);
                LockSupport.park();
            }
        });
        threads[1] = new Thread(() -> {
            for (int i = 0; i < 50; i ++ ) {
                LockSupport.park();
                count.getAndIncrement();
                System.out.println("B \t" + count.get());
                LockSupport.unpark(threads[0]);
            }
        });
        threads[0].start();
        threads[1].start();
        try {
            threads[0].join();
            threads[1].join();
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        } finally {
            System.out.println(count.get());
        }
    }

    /**
     * 使用信号量进行交替打印
     */
    private static void useSemaphore() {
        CompletableFuture.runAsync(() -> {
            while(atomicInteger.get() < PRINT_COUNT) {
                try {
                    semaphoreA.acquire();
                    if (atomicInteger.incrementAndGet() > PRINT_COUNT) {
                        return;
                    }
                    System.out.println("A \t" + atomicInteger.get());
                    semaphoreB.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });

        CompletableFuture.runAsync(() -> {
            while(atomicInteger.get() < PRINT_COUNT) {
                try {
                    semaphoreB.acquire();
                    if (atomicInteger.incrementAndGet() > PRINT_COUNT) {
                        return;
                    }
                    System.out.println("B \t" + atomicInteger.get());
                    semaphoreA.release();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).join();
    }

    public static void main(String[] args) {
        useSemaphore();
        useLockSupport();
    }
}
