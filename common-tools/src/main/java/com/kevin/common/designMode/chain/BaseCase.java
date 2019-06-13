package com.kevin.common.designMode.chain;

/**
 * @author kevin
 * @date 2019-06-13 09:51
 */
public interface BaseCase {

    // 所有 case 处理逻辑的方法
    void doSomething(String input, BaseChain baseChain);
}
