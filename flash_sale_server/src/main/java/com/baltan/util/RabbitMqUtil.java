package com.baltan.util;

import org.springframework.amqp.core.AmqpTemplate;
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
    private AmqpTemplate amqpTemplate;

    public void send(String exchanger, String routeKey, Object message) {
        amqpTemplate.convertAndSend(exchanger, routeKey, message);
    }
}
