package com.baltan.business;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-10-16 21:41
 */
@Component
public class TicketBusiness {
    @RabbitListener(queues = "ticket.response.queue")
    public void buyTicket(Object message) {
        if (message instanceof String) {
            String response = (String) message;
            System.out.println(response);
        }
    }
}
