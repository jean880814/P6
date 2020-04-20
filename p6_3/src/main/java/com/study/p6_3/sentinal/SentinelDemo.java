package com.study.p6_3.sentinal;

import com.alibaba.csp.sentinel.Entry;
import com.alibaba.csp.sentinel.SphU;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;

import java.util.ArrayList;
import java.util.List;

public class SentinelDemo {

    private static String resource="doTest";

    //初始化规则
    private static void initFlowRules(){
        List<FlowRule> rules=new ArrayList<>(); //限流规则的集合
        FlowRule flowRule=new FlowRule();
        flowRule.setResource(resource);//资源(方法名称、接口）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的阈值的类型
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }

    public static void main(String[] args) {
        initFlowRules(); //初始化一个规则
        while(true){
            Entry entry=null;
            try{
                entry= SphU.entry(resource); //它做了什么
                System.out.println("Hello Word");
            }catch (BlockException e){//如果被限流了，那么会抛出这个异常
                e.printStackTrace();
            }finally {
                if(entry!=null){
                    entry.exit();// 释放
                }
            }
        }
    }

}
