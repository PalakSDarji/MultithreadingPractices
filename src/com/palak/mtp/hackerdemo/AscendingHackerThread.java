package com.palak.mtp.hackerdemo;

public class AscendingHackerThread extends HackerThread{

    public AscendingHackerThread(Safe vault) {
        super(vault);
        setName(this.getClass().getSimpleName());
    }

    @Override
    public void run() {
        for(int i=0; i < Safe.MAX_PASS_LIMIT; i++){
            openVault(i);
        }
    }
}
