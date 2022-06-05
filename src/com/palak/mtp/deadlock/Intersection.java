package com.palak.mtp.deadlock;

public class Intersection {

    Object lockA = new Object();
    Object lockB = new Object();

    public void takeRoadA(){
        synchronized (lockA){
            System.out.println("Road A is locked with lockA by " + Thread.currentThread().getName());

            synchronized (lockB){
                System.out.println("Road B is locked with lockB by " + Thread.currentThread().getName());
            }
        }
    }

    public void takeRoadB(){
        synchronized (lockA){
            System.out.println("Road A is locked with lockA by " + Thread.currentThread().getName());

            synchronized (lockB){
                System.out.println("Road B is locked with lockB by " + Thread.currentThread().getName());
            }
        }
    }
}
