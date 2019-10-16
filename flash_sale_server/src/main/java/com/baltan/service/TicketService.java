package com.baltan.service;

import com.baltan.config.MyBatisSqlSessionFactory;
import com.baltan.entity.Ticket;
import com.baltan.mapper.TicketMapper;
import org.apache.ibatis.session.SqlSession;
import org.springframework.stereotype.Component;

import java.util.Collections;
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
    public String buyTicket(Map<String, Object> map) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSqlSession();
        int ticketCount;
        String response = "购票失败！";

        try {
            TicketMapper mapper = sqlSession.getMapper(TicketMapper.class);
            ticketCount = mapper.buyTicket(map);

            if (ticketCount == 1) {
                sqlSession.commit();
                response = "购票成功！";
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return response;
    }

    public List<Ticket> queryAllAvailableTickets(String from, String to) {
        SqlSession sqlSession = MyBatisSqlSessionFactory.openSqlSession();
        List<Ticket> availableTicketList = Collections.emptyList();

        try {
            TicketMapper mapper = sqlSession.getMapper(TicketMapper.class);
            availableTicketList = mapper.queryAllAvailableTickets(from, to);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            sqlSession.close();
        }
        return availableTicketList;
    }
}
