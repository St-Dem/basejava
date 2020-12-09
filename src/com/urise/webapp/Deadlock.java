package com.urise.webapp;

public class Deadlock {
    static Thread thread1 = new Thread(new Deadlock1());
    static Thread thread2 = new Thread(new Deadlock2());

    public static void main(String[] args) {
        thread1.start();
        thread2.start();
    }

    public static class Deadlock1 implements Runnable {

        @Override
        public void run() {
            try {
                thread2.join();
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
                thread1.join();
                System.out.println("Deadlock2 is finished");
            } catch (InterruptedException e) {
                System.out.println("Deadlock2 was interapted");
            }
        }
    }
}
