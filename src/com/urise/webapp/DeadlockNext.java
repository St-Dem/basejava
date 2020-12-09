package com.urise.webapp;

public class DeadlockNext {
    static Deadlock1 deadlock1 = new Deadlock1();
    static Deadlock2 deadlock2 = new Deadlock2();

    public static void main(String[] args) {
        deadlock1.start();
        deadlock2.start();
    }

    public static class Deadlock1 extends Thread {

        @Override
        public void run() {
            try {
                deadlock2.join();
                System.out.println("Deadlock1 is finished");
            } catch (InterruptedException e) {
                System.out.println("Deadlock1 was interapted");
            }

        }
    }

    public static class Deadlock2 extends Thread {

        @Override
        public void run() {
            try {
                deadlock1.join();
                System.out.println("Deadlock2 is finished");
            } catch (InterruptedException e) {
                System.out.println("Deadlock2 was interapted");
            }
        }
    }
}