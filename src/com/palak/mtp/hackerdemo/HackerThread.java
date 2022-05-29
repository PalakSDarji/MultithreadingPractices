package com.palak.mtp.hackerdemo;

public class HackerThread extends Thread{

    Safe vault;

    public HackerThread(Safe vault) {
        this.vault = vault;
    }

    public void openVault(int password){
        try {
            Thread.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        if(vault.open(password)){
            System.exit(0);
        }
    }
}
