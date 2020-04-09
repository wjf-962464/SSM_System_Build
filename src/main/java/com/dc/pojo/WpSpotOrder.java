package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;

import java.io.Serializable;
import java.sql.Timestamp;

public class WpSpotOrder implements Serializable {
    private int order_id;
    private int spot_id;
    private int user_id;
    private Timestamp create_time;
    private Timestamp park_time;
    private int order_status;
    @ApiModelProperty("1：已完成；2：进行中；0：已取消；-1：超时;3:停车中")
    private int user_status;

    public Timestamp getPark_time() {
        return park_time;
    }

    public void setPark_time(Timestamp park_time) {
        this.park_time = park_time;
    }

    public int getUser_status() {
        return user_status;
    }

    public void setUser_status(int user_status) {
        this.user_status = user_status;
    }

    public int getOrder_id() {
        return order_id;
    }

    public void setOrder_id(int order_id) {
        this.order_id = order_id;
    }

    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
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
}
