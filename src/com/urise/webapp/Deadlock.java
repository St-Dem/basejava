package com.urise.webapp;

public class Deadlock {
    public static void main(String[] args) {
        int one = 1;
        String two = "Russia";
        deadlock(one, two);
        deadlock(two, one);
    }

    public static void deadlock(Object one, Object two) {
        new Thread(() -> {
            synchronized (one) {
                System.out.println("Before deadlcok");
                try {
                    Thread.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (two) {
                    System.out.println("After deadlock");
                    try {
                        Thread.sleep(10);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }
        ).start();
    }
}
