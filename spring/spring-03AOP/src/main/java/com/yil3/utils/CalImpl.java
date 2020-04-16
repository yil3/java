package com.yil3.utils;

import org.springframework.stereotype.Component;

@Component
public class CalImpl implements Cal {
    public int add(int num1, int num2) {
        int res = num1+num2;
        return res;
    }

    public int sub(int num1, int num2) {
        int res = num1-num2;
        return res;
    }

    public int mul(int num1, int num2) {
        int res = num1*num2;
        return res;
    }

    public int div(int num1, int num2) {
        int res = num1/num2;
        return res;
    }
}
