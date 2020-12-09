package com.urise.webapp;

public class Deadlock {
    public static void main(String[] args) {
        Thread thread1 = new Thread(new Deadlock1());
        Thread thread2 = new Thread(new Deadlock2());

        thread1.start();
        thread2.start();
    }

    public static class Deadlock1 implements Runnable {

        @Override
        public void run() {
            try {
                Thread.currentThread().join();
                System.out.println("Deadlock1 is finished");
            } catch (InterruptedException e) {
                System.out.println("Deadlock1 was interapted");
            }

        }
    }

    public static class Deadlock2 implements Runnable {

        @Override
        public void run() {
            try {
                Thread.currentThread().join();
                System.out.println("Deadlock2 is finished");
            } catch (InterruptedException e) {
                System.out.println("Deadlock2 was interapted");
            }
        }
    }
}
