package com.kevin.common.designMode.chain;

/**
 * @author kevin
 * @date 2019-06-13 09:57
 */
public class TestChain {
    public static void main(String[] args) {
        String input = "";
        CaseChain caseChain = new CaseChain();
        caseChain.addBaseCase(new OneCase())
                .addBaseCase(new TwoCase())
                .addBaseCase(new DefaultCase());
        caseChain.doSomething(input);
    }
}
