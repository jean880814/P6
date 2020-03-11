package com.study.p6_1.adapter_pattern.passport.adapterv2;

import com.study.p6_1.adapter_pattern.passport.ResultMsg;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.ILoginAdapter;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.LoginForWeiboAdapter;

public class PassportForWeiboAdapter extends AbstractPassportForThird {
    private String weibo;

    public PassportForWeiboAdapter(String weibo) {
        this.weibo = weibo;
    }

    @Override
    public ResultMsg loginForAdapter() {
        return processLogin(weibo);
    }

    @Override
    protected ILoginAdapter adapter() {
        return new LoginForWeiboAdapter();
    }
}
