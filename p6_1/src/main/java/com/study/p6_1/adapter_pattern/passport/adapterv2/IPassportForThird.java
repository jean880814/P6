package com.study.p6_1.adapter_pattern.passport.adapterv2;

import com.study.p6_1.adapter_pattern.passport.ResultMsg;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.ILoginAdapter;

/**
 * Created by Tom.
 */
public interface IPassportForThird {

//    ResultMsg loginForQQ(String openId);
//
//    ResultMsg loginForWechat(String openId);
//
//    ResultMsg loginForToken(String token);
//
//    ResultMsg loginForTelphone(String phone, String code);

    ResultMsg loginForAdapter();

}
