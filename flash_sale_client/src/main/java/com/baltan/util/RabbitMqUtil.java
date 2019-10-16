package com.baltan.util;

import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Description: 消息队列工具类
 *
 * @author Baltan
 * @date 2019-10-16 16:02
 */
@Component
public class RabbitMqUtil {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void send(String exchanger, String routeKey, Object message) {
        rabbitTemplate.convertAndSend(exchanger, routeKey, message);
    }
}
