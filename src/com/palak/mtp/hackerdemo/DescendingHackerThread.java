package com.palak.mtp.hackerdemo;

public class DescendingHackerThread extends HackerThread{

    public DescendingHackerThread(Safe vault) {
        super(vault);
        setName(this.getClass().getSimpleName());
    }

    @Override
    public void run() {
        for(int i=Safe.MAX_PASS_LIMIT; i >= 0 ; i--){
            openVault(i);
        }
    }
}
