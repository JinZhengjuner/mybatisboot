package com.jzj.demo.chain;

public class AAdaptor extends LetterTransferAdaptor{
    @Override
    protected String doTransfer(String iput) {
        return iput + "->A  ";
    }
}
