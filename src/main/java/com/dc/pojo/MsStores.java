package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
import com.wordnik.swagger.annotations.ApiOperation;

import java.io.Serializable;

public class MsStores implements Serializable {
    private int store_id;
    private String name;
    @ApiModelProperty("描述评分")
    private float desc_rank;
    @ApiModelProperty("服务评分")
    private float serv_rank;
    @ApiModelProperty("物流评分")
    private float deli_rank;
    @ApiModelProperty("店铺综合等级")
    private String total_rank;
    private int status;

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public int getStore_id() {
        return store_id;
    }

    public void setStore_id(int store_id) {
        this.store_id = store_id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public float getDesc_rank() {
        return desc_rank;
    }

    public void setDesc_rank(float desc_rank) {
        this.desc_rank = desc_rank;
    }

    public float getServ_rank() {
        return serv_rank;
    }

    public void setServ_rank(float serv_rank) {
        this.serv_rank = serv_rank;
    }

    public float getDeli_rank() {
        return deli_rank;
    }

    public void setDeli_rank(float deli_rank) {
        this.deli_rank = deli_rank;
    }

    public String getTotal_rank() {
        return total_rank;
    }

    public void setTotal_rank(String total_rank) {
        this.total_rank = total_rank;
    }
}
