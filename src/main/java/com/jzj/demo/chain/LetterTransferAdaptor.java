package com.jzj.demo.chain;

import lombok.Getter;
import lombok.Setter;

public abstract class LetterTransferAdaptor {

    @Getter
    @Setter
    protected LetterTransferAdaptor nextAdaptor;

    protected String transfer(String input){
        String output = doTransfer(input);
        if (nextAdaptor != null){
            return nextAdaptor.transfer(output);
        }
        return output;
    }

    public static void main(String[] args) {
        String str = "fdslkfjdstulklx";
        String sub = "tu";
        bf(str, sub);
    }

    private static void bf(String str, String sub) {
        int i = 0;
        int j = 0;

        int strLen = str.length();
        int subLen = sub.length();

        while (i < strLen && j < subLen){
            if (str.charAt(i) == sub.charAt(j)){
                i ++;
                j++;
            }else {
                i = i - j  + 1;
                j =0;
            }
        }
        if (subLen <= j){
            System.out.println("找到子串再主串的位置了:" + (i-j) + " 到 " + (i-1));

        }
    }

    protected abstract String doTransfer(String iput);
}
