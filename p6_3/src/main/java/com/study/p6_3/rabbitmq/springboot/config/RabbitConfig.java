package com.study.p6_3.rabbitmq.springboot.config;

import org.springframework.amqp.core.*;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

@Configuration
public class RabbitConfig {

    // 两个交换机
    @Bean("topicExchange")
    public TopicExchange getTopicExchange(){
        return new TopicExchange("TOPIC_EXCHANGE");
    }

    @Bean("fanoutExchange")
    public FanoutExchange getFanoutExchange(){
        return  new FanoutExchange("FANOUT_EXCHANGE");
    }

    @Bean("delayExchange")
    public TopicExchange getDelayExchange(){
        Map<String,Object> args=new HashMap<>();
        args.put("x-delayed-type","direct");
        TopicExchange topic=new TopicExchange("DELAY_TEST_EXCHANGE",true,false,args);
        topic.setDelayed(true);
        return topic;
    }

    // 三个队列
    @Bean("firstQueue")
    public Queue getFirstQueue(){
        Map<String, Object> args = new HashMap<String, Object>();
        args.put("x-message-ttl",6000);
        Queue queue = new Queue("FIRST_QUEUE", false, false, true, args);
        return queue;
    }

    @Bean("secondQueue")
    public Queue getSecondQueue(){
        return new Queue("SECOND_QUEUE");
    }

    @Bean("thirdQueue")
    public Queue getThirdQueue(){
        return new Queue("THIRD_QUEUE");
    }

    // 两个绑定
    @Bean
    public Binding bindSecond(@Qualifier("secondQueue") Queue queue,@Qualifier("topicExchange") TopicExchange exchange){
        return BindingBuilder.bind(queue).to(exchange).with("#.gupao.#");
    }

    @Bean
    public Binding bindThird(@Qualifier("thirdQueue") Queue queue,@Qualifier("fanoutExchange") FanoutExchange exchange){
        return BindingBuilder.bind(queue).to(exchange);
    }

    @Bean
    public Binding bindDelay(@Qualifier("secondQueue") Queue queue, @Qualifier("delayExchange") TopicExchange exchange) {
        return BindingBuilder.bind(queue).to(exchange).with("#.delay.#");
    }

}
