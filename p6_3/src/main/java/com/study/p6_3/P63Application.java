package com.study.p6_3;

import com.alibaba.csp.sentinel.cluster.ClusterStateManager;
import com.alibaba.csp.sentinel.slots.block.RuleConstant;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRule;
import com.alibaba.csp.sentinel.slots.block.flow.FlowRuleManager;
import com.study.p6_3.kafka.KafkaProducer;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.TimeUnit;

@SpringBootApplication
public class P63Application {

    public static void main(String[] args) throws InterruptedException {
        //表示当前的节点是集群客户端
        ClusterStateManager.applyState(ClusterStateManager.CLUSTER_CLIENT);
        ConfigurableApplicationContext context = SpringApplication.run(P63Application.class, args);
        KafkaProducer kp = context.getBean(KafkaProducer.class);
        for (int i = 0; i < 10; i++) {
            kp.send();
            TimeUnit.SECONDS.sleep(2);
        }
    }

    //初始化规则
    private static void initFlowRules() {
        List<FlowRule> rules = new ArrayList<>(); //限流规则的集合
        FlowRule flowRule = new FlowRule();
        flowRule.setResource("sayHello");//资源(方法名称、接口）
        flowRule.setGrade(RuleConstant.FLOW_GRADE_QPS); //限流的阈值的类型
        flowRule.setCount(10);
        rules.add(flowRule);
        FlowRuleManager.loadRules(rules);
    }


}
