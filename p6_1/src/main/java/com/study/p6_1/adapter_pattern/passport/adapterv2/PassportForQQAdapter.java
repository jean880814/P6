package com.study.p6_1.adapter_pattern.passport.adapterv2;

import com.study.p6_1.adapter_pattern.passport.ResultMsg;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.ILoginAdapter;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.LoginForQQAdapter;

public class PassportForQQAdapter extends AbstractPassportForThird {
    private String openId;

    public PassportForQQAdapter(String openId) {
        this.openId = openId;
    }

    @Override
    public ResultMsg loginForAdapter() {
        return processLogin(openId);
    }

    @Override
    protected ILoginAdapter adapter() {
        return new LoginForQQAdapter();
    }
}
