package com.study.p6_1.strategy_pattern;

/**
 * 支付完成以后的状态
 * Created by Tom.
 */
public class MsgResult {
    private int code;
    private Object data;
    private String msg;

    public MsgResult(int code, String msg, Object data) {
        this.code = code;
        this.data = data;
        this.msg = msg;
    }

    public String toString(){
        return ("发送状态：[" + code + "]," + msg + ",发送详情：" + data);
    }
}
