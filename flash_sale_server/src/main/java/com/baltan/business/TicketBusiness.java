package com.baltan.business;

import com.baltan.config.RabbitMqConfig;
import com.baltan.service.TicketService;
import com.baltan.util.RabbitMqUtil;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-10-16 17:21
 */
@Component
public class TicketBusiness {
    @Autowired
    private RabbitMqUtil rabbitMqUtil;
    @Autowired
    private TicketService ticketService;
    @Autowired
    private RabbitMqConfig rabbitMqConfig;

    @RabbitListener(queues = "ticket.request.queue")
    public void buyTicket(Object message) {
        if (message instanceof Map) {
            Map<String, Object> saleMap = (Map<String, Object>) message;

            String username = (String) saleMap.get("username");
            String response = ticketService.buyTicket(saleMap);

            rabbitMqUtil.send(rabbitMqConfig.getTicketResponseExchangerName(),
                    rabbitMqConfig.getTicketResponseRouteKey(), username + " ： " + response);
        } else {
            rabbitMqUtil.send(rabbitMqConfig.getTicketResponseExchangerName(),
                    rabbitMqConfig.getTicketResponseRouteKey(), "购票失败！");
        }
    }
}
