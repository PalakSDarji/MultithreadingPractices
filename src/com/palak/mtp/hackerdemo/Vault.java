package com.palak.mtp.hackerdemo;

import java.util.Random;

public class Vault implements Safe{

    int password;
    public int amount;

    Vault(int amount){
        password = generateRandomPassword();
        this.amount = amount;
    }

    private int generateRandomPassword(){
        Random random = new Random();
        return random.nextInt(MAX_PASS_LIMIT);
    }

    @Override
    public boolean open(int password) {
        if(this.password == password){
            System.out.println(Thread.currentThread().getName() + " opened Vault with password : " + password );
            amount = 0;
            return true;
        }
        return false;
    }
}
