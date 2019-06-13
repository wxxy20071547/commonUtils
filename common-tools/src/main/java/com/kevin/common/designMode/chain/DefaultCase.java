package com.kevin.common.designMode.chain;

/**
 * @author kevin
 * @date 2019-06-13 10:00
 */
public class DefaultCase implements BaseCase {
    @Override
    public void doSomething(String input, BaseChain baseChain) {


        System.out.println(getClass().getName());

    }
}
