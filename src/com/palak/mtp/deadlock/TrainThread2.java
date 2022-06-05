package com.palak.mtp.deadlock;

import java.util.Random;

public class TrainThread2 implements Runnable{

    Intersection intersection;
    Random random = new Random();

    public TrainThread2(Intersection intersection) {
        this.intersection = intersection;
    }

    @Override
    public void run() {
        while (true){
            intersection.takeRoadB();
            try {
                Thread.sleep(random.nextInt(3));
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
