package com.palak.mtp.hackerdemo;

public class PoliceThread extends Thread {

    PoliceThread(){
        setName(this.getClass().getName());
    }

    @Override
    public void run() {
        try {
            for(int i=0; i< 10; i++){
                Thread.sleep(500);
                System.out.println("Catching hackers on count of "+ i);
            }
            System.out.println("Police caught hackers. The end.");
            System.exit(0);

        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }
}
