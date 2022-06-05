package com.palak.mtp.deadlock;

import java.util.Random;

public class TrainThread1 implements Runnable{

    Intersection intersection;
    Random random = new Random();

    public TrainThread1(Intersection intersection) {
        this.intersection = intersection;
    }

    @Override
    public void run() {
        while(true){
            intersection.takeRoadA();
            try {
                Thread.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
