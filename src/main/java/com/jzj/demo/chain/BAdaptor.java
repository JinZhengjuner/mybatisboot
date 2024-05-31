package com.jzj.demo.chain;

public class BAdaptor extends LetterTransferAdaptor{
    @Override
    protected String doTransfer(String iput) {
        return iput + "->B  ";
    }
}
