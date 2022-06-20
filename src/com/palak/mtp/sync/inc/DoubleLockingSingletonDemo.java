package com.palak.mtp.sync.inc;

public class DoubleLockingSingletonDemo {

    private static volatile DoubleLockingSingletonDemo instance;

    private DoubleLockingSingletonDemo(){ }

    public static DoubleLockingSingletonDemo getInstance() {
        if(instance == null) {
            synchronized (DoubleLockingSingletonDemo.class) {
                if(instance == null) {
                    instance = new DoubleLockingSingletonDemo();
                    System.out.println(Thread.currentThread().getName() + " created singleton");
                }
            }
        }
        System.out.println(Thread.currentThread().getName() + " fetched singleton");
        return instance;
    }

    public static void main(String[] args) {

        for(int i = 0 ; i< 100; i++){
            new Thread(() -> DoubleLockingSingletonDemo.getInstance()).start();
        }
    }
}
