package com.study.p6_1.adapter_pattern.passport.adapterv2.adapters;

import com.study.p6_1.adapter_pattern.passport.ResultMsg;

/**
 * Created by Tom.
 */
public class LoginForWechatAdapter extends AbstraceAdapter{
    public boolean support(Object adapter) {
        return adapter instanceof LoginForWechatAdapter;
    }

    public ResultMsg login(String id, Object adapter) {
        return super.loginForRegist(id,null);
    }


}
