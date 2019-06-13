package com.kevin.common.designMode.chain;

import java.util.ArrayList;
import java.util.List;

/**
 * @author kevin
 * @date 2019-06-13 09:52
 */
public class CaseChain implements BaseChain {
    // 所有 case 列表
    private List<BaseCase> mCaseList = new ArrayList<>();
    // 索引，用于遍历所有 case 列表
    private int index = 0;

    // 添加 case
    public CaseChain addBaseCase(BaseCase baseCase) {
        mCaseList.add(baseCase);
        return this;
    }

    @Override
    public void doSomething(String input) {
        if (this.index < mCaseList.size()) {
            BaseCase currentCase = mCaseList.get(this.index++);
            currentCase.doSomething(input,this);
        }
    }
}
