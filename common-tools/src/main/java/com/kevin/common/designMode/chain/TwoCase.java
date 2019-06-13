package com.kevin.common.designMode.chain;

/**
 * @author kevin
 * @date 2019-06-13 09:59
 */
public class TwoCase implements BaseCase {
    @Override
    public void doSomething(String input,BaseChain baseChain) {
        System.out.println("this is "+ getClass().getName());
        if ("2".equals(input)) {
            // TODO do something
            System.out.println(getClass().getName());
            return;
        }
        //当前没法处理，回调回去，让下一个去处理
        baseChain.doSomething(input);

    }
}
