package com.baltan.mapper;

import com.baltan.entity.Ticket;
import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

/**
 * Description: 车票操作接口
 *
 * @author Baltan
 * @date 2019-10-15 13:52
 */
@Mapper
@Component
public interface TicketMapper {
    /**
     * 创建某一趟车次的车票
     *
     * @param ticketList
     */
    int insertTickets(List<Ticket> ticketList);

    /**
     * 删除某一趟车次的所有车票
     */
    @Delete("truncate table ticket")
    int deleteAllTickets();

    /**
     * 买一张车票，在数据库中插入某张车票的乘客信息
     *
     * @param map
     * @return
     */
    int buyTicket(Map<String, Object> map);

    /**
     * 查询所有还未出售的车票
     *
     * @param from
     * @param to
     * @return
     */
    List<Ticket> queryAvailableAllTickets(String from, String to);
}
