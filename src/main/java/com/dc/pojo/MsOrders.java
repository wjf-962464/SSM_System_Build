package com.dc.pojo;

import org.joda.time.DateTime;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;

public class MsOrders implements Serializable {
    private int order_id;
    private int user_id;
    private String good_id_list;
    private String good_amount_list;
    private String cost_list;
    private BigDecimal total_money;
    private String send_address;
    private Timestamp create_time;
    private int order_status;

    public int getOrder_status() {
        return order_status;
    }

    public void setOrder_status(int order_status) {
        this.order_status = order_status;
    }

    public String getGood_amount_list() {
        return good_amount_list;
    }

    public void setGood_amount_list(String good_amount_list) {
        this.good_amount_list = good_amount_list;
    }


    public String getCost_list() {
        return cost_list;
    }

    public void setCost_list(String cost_list) {
        this.cost_list = cost_list;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getUser_id() {
        return user_id;
    }

    public void setUser_id(int user_id) {
        this.user_id = user_id;
    }

    public String getGood_id_list() {
        return good_id_list;
    }

    public void setGood_id_list(String good_id_list) {
        this.good_id_list = good_id_list;
    }

    public String getGood_amount() {
        return good_amount_list;
    }

    public void setGood_amount(String good_amount_list) {
        this.good_amount_list = good_amount_list;
    }

    public BigDecimal getTotal_money() {
        return total_money;
    }

    public void setTotal_money(BigDecimal total_money) {
        this.total_money = total_money;
    }

    public String getSend_address() {
        return send_address;
    }

    public void setSend_address(String send_address) {
        this.send_address = send_address;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }
}
