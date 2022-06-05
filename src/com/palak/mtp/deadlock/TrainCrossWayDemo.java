package com.palak.mtp.deadlock;

public class TrainCrossWayDemo {
    public static void main(String[] args) {

        Intersection intersection = new Intersection();
        Thread trainThread1 = new Thread(new TrainThread1(intersection));
        trainThread1.setName("train1");
        Thread trainThread2 = new Thread(new TrainThread2(intersection));
        trainThread2.setName("train2");

        trainThread1.start();
        trainThread2.start();

    }
}
