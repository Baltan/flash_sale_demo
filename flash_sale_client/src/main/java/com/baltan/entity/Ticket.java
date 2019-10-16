package com.baltan.entity;

/**
 * Description: 车票实体类
 *
 * @author Baltan
 * @date 2019-10-15 13:46
 */
public class Ticket {
    private Integer id;
    /**
     * 起点
     */
    private String from;
    /**
     * 终点
     */
    private String to;
    /**
     * 乘客姓名
     */
    private String username;
    /**
     * 乘客身份证号
     */
    private String userId;
    /**
     * 价格
     */
    private Float price;
    /**
     * 车厢号
     */
    private String coachNo;
    /**
     * 座位号
     */
    private String seatNo;
    /**
     * 车票是否已出售
     */
    private boolean sold;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getFrom() {
        return from;
    }

    public void setFrom(String from) {
        this.from = from;
    }

    public String getTo() {
        return to;
    }

    public void setTo(String to) {
        this.to = to;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Float getPrice() {
        return price;
    }

    public void setPrice(Float price) {
        this.price = price;
    }

    public String getCoachNo() {
        return coachNo;
    }

    public void setCoachNo(String coachNo) {
        this.coachNo = coachNo;
    }

    public String getSeatNo() {
        return seatNo;
    }

    public void setSeatNo(String seatNo) {
        this.seatNo = seatNo;
    }

    public boolean isSold() {
        return sold;
    }

    public void setSold(boolean sold) {
        this.sold = sold;
    }

    @Override
    public String toString() {
        return "Ticket{" +
                "id=" + id +
                ", from='" + from + '\'' +
                ", to='" + to + '\'' +
                ", username='" + username + '\'' +
                ", userId='" + userId + '\'' +
                ", price=" + price +
                ", coachNo='" + coachNo + '\'' +
                ", seatNo='" + seatNo + '\'' +
                ", sold=" + sold +
                '}';
    }
}
