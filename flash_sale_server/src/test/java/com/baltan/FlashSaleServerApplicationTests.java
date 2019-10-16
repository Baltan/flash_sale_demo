package com.baltan;

import com.baltan.config.MyBatisSqlSessionFactory;
import com.baltan.entity.Ticket;
import com.baltan.mapper.TicketMapper;
import org.apache.ibatis.session.SqlSession;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.LinkedList;
import java.util.List;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlashSaleServerApplicationTests {
    @Autowired
    private TicketMapper ticketMapper;

    @Test
    public void testCreateTickets() {
        List<Ticket> ticketList = new LinkedList<>();
        String from = "杭州东站";
        String to = "上海虹桥站";
        Float price = 77.5f;
        int coachCount = 8;
        int seatRowCount = 20;
        char[] seatName = {'A', 'B', 'C', 'D', 'F'};

        for (int i = 1; i <= coachCount; i++) {
            for (int j = 1; j <= seatRowCount; j++) {
                for (char c : seatName) {
                    Ticket ticket = new Ticket();
                    ticket.setFrom(from);
                    ticket.setTo(to);
                    ticket.setPrice(price);
                    ticket.setCoachNo(String.format("%02d", i));
                    ticket.setSeatNo(String.format("%02d", j) + c);
                    ticketList.add(ticket);
                }
            }
        }

        SqlSession sqlSession = MyBatisSqlSessionFactory.openSqlSession();

        try {
            TicketMapper mapper = sqlSession.getMapper(TicketMapper.class);
            int effectiveLineCount = mapper.insertTickets(ticketList);

            if (effectiveLineCount == ticketList.size()) {
                sqlSession.commit();
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
    }

    @Test
    public void testDeleteAllTickets() {
        ticketMapper.deleteAllTickets();
    }

    @Test
    public void testQueryAvailableAllTickets() {
        String from = "杭州东站";
        String to = "上海虹桥站";
        List<Ticket> ticketList = ticketMapper.queryAllAvailableTickets(from, to);
        System.out.println(ticketList);
    }
}
