package com.study.p6_1.adapter_pattern.passport.adapterv2;

import com.study.p6_1.adapter_pattern.passport.ResultMsg;
import com.study.p6_1.adapter_pattern.passport.adapterv2.adapters.ILoginAdapter;

public abstract class PassportForThirdBase  implements IPassportForThird {
    protected ResultMsg processLogin(String id){
        try {
            ILoginAdapter adapter = adapter();
            if (adapter.support(adapter)){
                return adapter.login(id,adapter);
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return null;
    }

    protected abstract ILoginAdapter adapter();
}
