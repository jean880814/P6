package com.study.p6_1.adapter_pattern.passport.adapterv2.adapters;

import com.study.p6_1.adapter_pattern.passport.ResultMsg;

/**
 * Created by Tom.
 */
public interface ILoginAdapter {
    boolean support(Object object);
    ResultMsg login(String id, Object adapter);
}
