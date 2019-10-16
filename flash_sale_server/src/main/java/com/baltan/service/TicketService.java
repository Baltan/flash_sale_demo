package com.baltan.service;

import com.baltan.entity.Ticket;
import com.baltan.mapper.TicketMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-10-15 16:46
 */
@Component
public class TicketService {
    @Autowired
    private TicketMapper ticketMapper;

    public String buyTicket(Map<String, Object> map) {
        int ticketCount = ticketMapper.buyTicket(map);

        if (ticketCount == 1) {
            return "购票成功！";
        } else {
            return "购票失败！";
        }
    }

    public List<Ticket> queryAllAvailableTickets(String from, String to) {
        return ticketMapper.queryAllAvailableTickets(from, to);
    }
}
