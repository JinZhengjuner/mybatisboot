package com.jzj.demo.chain;

public class CAdaptor extends LetterTransferAdaptor{
    @Override
    protected String doTransfer(String iput) {
        return iput + "->C  ";
    }

    public static void main(String[] args) {
        AAdaptor aAdaptor = new AAdaptor();
        BAdaptor bAdaptor = new BAdaptor();
        CAdaptor cAdaptor = new CAdaptor();
        aAdaptor.setNextAdaptor(bAdaptor);
        bAdaptor.setNextAdaptor(cAdaptor);

        String result = aAdaptor.transfer("开始了....");
        System.out.println(result);
    }
}
