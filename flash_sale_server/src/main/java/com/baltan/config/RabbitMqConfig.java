package com.baltan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * Description: 消息队列配置
 *
 * @author Baltan
 * @date 2019-10-15 16:14
 */
@Configuration
public class RabbitMqConfig {
    /**
     * 创建一个队列
     *
     * @return
     */
    @Bean
    public Queue ticketQueue() {
        return new Queue("ticketQueue");
    }

    /**
     * 创建一个交换器
     *
     * @return
     */
    @Bean
    public TopicExchange ticketExchanger() {
        return new TopicExchange("ticketExchanger");
    }

    /**
     * 绑定队列和交换器
     *
     * @param ticketQueue
     * @param ticketExchanger
     * @return
     */
    @Bean
    Binding bindTicketQueue2TicketExchanger(Queue ticketQueue, TopicExchange ticketExchanger) {
        return BindingBuilder.bind(ticketQueue).to(ticketExchanger).with("ticket.routeKey");
    }
}
