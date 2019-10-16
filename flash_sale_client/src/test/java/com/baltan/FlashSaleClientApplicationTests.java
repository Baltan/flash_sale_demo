package com.baltan;

import com.baltan.entity.Ticket;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Random;
import java.util.concurrent.CyclicBarrier;

@RunWith(SpringRunner.class)
@SpringBootTest
public class FlashSaleClientApplicationTests {
    @Autowired
    private RestTemplate restTemplate;
    private int concurrentCount = 1000;
    private CyclicBarrier cyclicBarrier = new CyclicBarrier(concurrentCount);
    private String url = "http://127.0.0.1:8080/";
    private String from = "杭州东站";
    private String to = "上海虹桥站";
    private Random random = new Random();

    @Test
    public void testBuyTicketWithoutMQ() {
        for (int i = 1; i <= concurrentCount; i++) {
            ParameterizedTypeReference<List<Ticket>> typeReference =
                    new ParameterizedTypeReference<List<Ticket>>() {
                    };
            /**
             * 查询所有余票
             */
            ResponseEntity<List<Ticket>> responseEntity =
                    restTemplate.exchange(url + "queryAllAvailableTickets" + "?from=" + from + "&to=" + to,
                            HttpMethod.GET, new HttpEntity<>(Ticket.class), typeReference);
            List<Ticket> availableTicketList = responseEntity.getBody();
            int ticketCount = availableTicketList.size();
            System.out.println("用户" + i + "查询结果，还剩余票： " + ticketCount + "张！");
            int ticketNo = random.nextInt(ticketCount);
            Ticket ticket = availableTicketList.get(ticketNo);

            final int j = i;
            Map<String, Object> saleMap = new HashMap<>();

            saleMap.put("from", from);
            saleMap.put("to", to);
            saleMap.put("coachNo", ticket.getCoachNo());
            saleMap.put("seatNo", ticket.getSeatNo());
            saleMap.put("username", "用户" + j);
            saleMap.put("userId", String.format("%018d", j));

            new Thread(() -> {
                try {
                    cyclicBarrier.await();
                } catch (Exception e) {
                    e.printStackTrace();
                }
                /**
                 * 随机购买一张车票
                 */
                System.out.println("用户" + j + "开始抢票");
                String response = restTemplate.postForObject(url + "buyTicket", saleMap, String.class);
                System.out.println("用户" + j + " ： " + response);
            }).start();
        }
    }
}
