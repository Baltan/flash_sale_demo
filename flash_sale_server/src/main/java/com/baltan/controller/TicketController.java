package com.baltan.controller;

import com.baltan.entity.Ticket;
import com.baltan.service.TicketService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

/**
 * Description:
 *
 * @author Baltan
 * @date 2019-10-15 17:11
 */
@RestController
public class TicketController {
    @Autowired
    private TicketService ticketService;

    @PostMapping("/buyTicket")
    public String buyTicket(@RequestBody Map<String, Object> map) {
        return ticketService.buyTicket(map);
    }

    @GetMapping("/queryAvailableAllTickets")
    public List<Ticket> queryAvailableAllTickets(@RequestParam String from, @RequestParam String to) {
        List<Ticket> availableTicketList = ticketService.queryAvailableAllTickets(from, to);
        return availableTicketList;
    }
}
