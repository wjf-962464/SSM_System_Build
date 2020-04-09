package com.dc.pojo;

import javax.persistence.criteria.Order;
import java.io.Serializable;
import java.sql.Timestamp;

public class WpSpotOrderDetail implements Serializable {
    private int order_id;
    private WpSpot spot;
    private WpPark park;
    private int  user_id;
    private int order_time;
    private Timestamp create_time;
    private Timestamp park_time;
    private int order_status;
    private String user_status;

    public int getOrder_time() {
        return order_time;
    }

    public void setOrder_time(int order_time) {
        this.order_time = order_time;
    }

    public void setUser_status(String user_status) {
        this.user_status = user_status;
    }

    public WpSpotOrderDetail(WpSpotOrder order, WpSpot spot, WpPark park,int order_time){
        this.order_id=order.getOrder_id();
        this.spot=spot;
        this.park=park;
        this.user_id=order.getUser_id();
        this.create_time=order.getCreate_time();
        this.order_status=order.getOrder_status();
        this.park_time=order.getPark_time();
        this.order_time=order_time;
        setUser_status(order.getUser_status());
    }
    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public WpSpot getSpot() {
        return spot;
    }

    public void setSpot(WpSpot spot) {
        this.spot = spot;
    }

    public WpPark getPark() {
        return park;
    }

    public void setPark(WpPark park) {
        this.park = park;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        switch (user_status){
            case 0:
                this.user_status = "已取消";
                break;
            case 1:
                this.user_status = "成功抵达";
                break;
            case 2:
                this.user_status = "正在前往";
                break;
            case 3:
                this.user_status = "停车中";
                break;
            case -1:
                this.user_status = "已超时";
                break;
        }
    }
}
