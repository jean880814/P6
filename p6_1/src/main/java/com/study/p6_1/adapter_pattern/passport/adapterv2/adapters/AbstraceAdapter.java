package com.study.p6_1.adapter_pattern.passport.adapterv2.adapters;

import com.study.p6_1.adapter_pattern.passport.PassportService;
import com.study.p6_1.adapter_pattern.passport.ResultMsg;

/**
 * Created by Tom.
 */
public abstract class AbstraceAdapter extends PassportService implements ILoginAdapter {
    protected ResultMsg loginForRegist(String username, String password){
        if(null == password){
            password = "THIRD_EMPTY";
        }
        super.regist(username,password);
        return super.login(username,password);
    }
}
