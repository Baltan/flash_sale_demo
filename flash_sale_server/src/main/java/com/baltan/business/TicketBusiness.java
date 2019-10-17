package com.baltan.business;

import com.baltan.config.RabbitMqConfig;
import com.baltan.service.TicketService;
import com.baltan.util.RabbitMqUtil;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
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
    private ObjectMapper objectMapper = new ObjectMapper();

    @RabbitListener(queues = "ticket.request.queue")
    public void buyTicket(String message) throws IOException {
        Map<String, Object> saleMap = objectMapper.readValue(message, Map.class);
        String username = (String) saleMap.get("username");
        String response = ticketService.buyTicket(saleMap);

        rabbitMqUtil.send(rabbitMqConfig.getTicketResponseExchangerName(),
                rabbitMqConfig.getTicketResponseRouteKey(), username + " ： " + response);
    }
}
