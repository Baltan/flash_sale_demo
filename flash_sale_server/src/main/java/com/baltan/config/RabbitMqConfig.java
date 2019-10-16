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
    private String ticketResponseQueueName;
    @Value(("${rabbitmq.ticket-exchanger.name}"))
    private String ticketResponseExchangerName;
    @Value("${rabbitmq.ticket-route-key}")
    private String ticketResponseRouteKey;

    /**
     * 创建一个队列
     *
     * @return
     */
    @Bean
    public Queue ticketQueue() {
        return new Queue(ticketResponseQueueName);
    }

    /**
     * 创建一个交换器
     *
     * @return
     */
    @Bean
    public TopicExchange ticketExchanger() {
        return new TopicExchange(ticketResponseExchangerName);
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
        return BindingBuilder.bind(ticketQueue).to(ticketExchanger).with(ticketResponseRouteKey);
    }

    public String getTicketResponseQueueName() {
        return ticketResponseQueueName;
    }

    public void setTicketResponseQueueName(String ticketResponseQueueName) {
        this.ticketResponseQueueName = ticketResponseQueueName;
    }

    public String getTicketResponseExchangerName() {
        return ticketResponseExchangerName;
    }

    public void setTicketResponseExchangerName(String ticketResponseExchangerName) {
        this.ticketResponseExchangerName = ticketResponseExchangerName;
    }

    public String getTicketResponseRouteKey() {
        return ticketResponseRouteKey;
    }

    public void setTicketResponseRouteKey(String ticketResponseRouteKey) {
        this.ticketResponseRouteKey = ticketResponseRouteKey;
    }
}
