package com.palak.mtp.hackerdemo;

public class HackerDemo {
    public static void main(String[] args) {

        Safe vault = new Vault(300);
        Thread asHackerThread = new AscendingHackerThread(vault);
        Thread dsHackerThread = new DescendingHackerThread(vault);
        Thread policeThread = new PoliceThread();

        asHackerThread.start();
        dsHackerThread.start();
        policeThread.start();

    }
}
