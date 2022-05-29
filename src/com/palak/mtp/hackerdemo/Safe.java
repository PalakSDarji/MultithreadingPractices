package com.palak.mtp.hackerdemo;

public interface Safe {

    public int MAX_PASS_LIMIT = 99;

    boolean open(int password);
}
