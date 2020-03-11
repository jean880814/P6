package com.study.p6_1.adapter_pattern.passport.adapterv2;

import com.study.p6_1.adapter_pattern.passport.ResultMsg;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.ILoginAdapter;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.LoginForTelAdapter;

public class PassportForPhoneAdapter extends AbstractPassportForThird {
    private String phone;
    private String code;

    public PassportForPhoneAdapter(String phone, String code) {
        this.phone = phone;
        this.code = code;
    }

    @Override
    public ResultMsg loginForAdapter() {
        return processLogin(phone);
    }

    @Override
    protected ILoginAdapter adapter() {
        return new LoginForTelAdapter();
    }
}
