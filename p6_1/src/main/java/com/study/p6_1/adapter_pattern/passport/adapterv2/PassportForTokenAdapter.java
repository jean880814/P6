package com.study.p6_1.adapter_pattern.passport.adapterv2;

import com.study.p6_1.adapter_pattern.passport.ResultMsg;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.ILoginAdapter;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.LoginForTokenAdapter;

public class PassportForTokenAdapter extends PassportForThirdBase {
    private String token;

    public PassportForTokenAdapter(String token) {
        this.token = token;
    }

    @Override
    public ResultMsg loginForAdapter() {
        return processLogin(token);
    }

    @Override
    protected ILoginAdapter adapter() {
        return new LoginForTokenAdapter();
    }
}
