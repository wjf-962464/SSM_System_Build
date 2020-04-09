package com.dc.pojo;

import java.io.Serializable;
import java.sql.Timestamp;

public class MsShopcar implements Serializable {
    private int shopcar_id;
    private int user_id;
    private String good_id_list;
    private String good_amount_list;
    private Timestamp create_time;
    private Timestamp last_modify;
    private int shopcar_status;

    public int getShopcar_id() {
        return shopcar_id;
    }

    public void setShopcar_id(int shopcar_id) {
        this.shopcar_id = shopcar_id;
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

    public String getGood_amount_list() {
        return good_amount_list;
    }

    public void setGood_amount_list(String good_amount_list) {
        this.good_amount_list = good_amount_list;
    }

    public Timestamp getCreate_time() {
        return create_time;
    }

    public void setCreate_time(Timestamp create_time) {
        this.create_time = create_time;
    }

    public Timestamp getLast_modify() {
        return last_modify;
    }

    public void setLast_modify(Timestamp last_modify) {
        this.last_modify = last_modify;
    }

    public int getShopcar_status() {
        return shopcar_status;
    }

    public void setShopcar_status(int shopcar_status) {
        this.shopcar_status = shopcar_status;
    }
}
