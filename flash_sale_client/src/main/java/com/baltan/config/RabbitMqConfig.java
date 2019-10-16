package com.baltan.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.Queue;
import org.springframework.amqp.core.TopicExchange;
import org.springframework.beans.factory.annotation.Value;
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
    @Value("${rabbitmq.ticket-queue.name}")
    private String ticketRequestQueueName;
    @Value(("${rabbitmq.ticket-exchanger.name}"))
    private String ticketRequestExchangerName;
    @Value("${rabbitmq.ticket-route-key}")
    private String ticketRequestRouteKey;

    /**
     * 创建一个队列
     *
     * @return
     */
    @Bean
    public Queue ticketQueue() {
        return new Queue(ticketRequestQueueName);
    }

    /**
     * 创建一个交换器
     *
     * @return
     */
    @Bean
    public TopicExchange ticketExchanger() {
        return new TopicExchange(ticketRequestExchangerName);
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
        return BindingBuilder.bind(ticketQueue).to(ticketExchanger).with(ticketRequestRouteKey);
    }

    public String getTicketRequestQueueName() {
        return ticketRequestQueueName;
    }

    public void setTicketRequestQueueName(String ticketRequestQueueName) {
        this.ticketRequestQueueName = ticketRequestQueueName;
    }

    public String getTicketRequestExchangerName() {
        return ticketRequestExchangerName;
    }

    public void setTicketRequestExchangerName(String ticketRequestExchangerName) {
        this.ticketRequestExchangerName = ticketRequestExchangerName;
    }

    public String getTicketRequestRouteKey() {
        return ticketRequestRouteKey;
    }

    public void setTicketRequestRouteKey(String ticketRequestRouteKey) {
        this.ticketRequestRouteKey = ticketRequestRouteKey;
    }
}
