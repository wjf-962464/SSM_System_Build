package com.dc.pojo;

import com.dc.dao.MsGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.Serializable;
import java.math.BigDecimal;

import java.sql.Timestamp;

public class MsGoods implements Serializable {
    private int good_id;
    private int store_id;
    private String name;
    private String describe;
    private String good_params;
    private BigDecimal price;
    private int promotion_flag;
    private BigDecimal promotion_price;
    private String tag;
    private int rank;
    private int inventory;
    private int photo_amount;
    private String good_photo1;
    private String good_photo2;
    private String good_photo3;
    private String good_photo4;
    private String good_photo5;
    private int evaluate_amount;
    private int monthly_sales;
    private Timestamp create_time;
    private Timestamp last_modify;
    private int good_status;

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

    public int getEvaluate_amount() {
        return evaluate_amount;
    }

    public void setEvaluate_amount(int evaluate_amount) {
        this.evaluate_amount = evaluate_amount;
    }

    public int getMonthly_sales() {
        return monthly_sales;
    }

    public void setMonthly_sales(int monthly_sales) {
        this.monthly_sales = monthly_sales;
    }

    public int getGood_id() {
        return good_id;
    }

    public void setGood_id(int good_id) {
        this.good_id = good_id;
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

    public String getDescribe() {
        return describe;
    }

    public void setDescribe(String describe) {
        this.describe = describe;
    }

    public String getGood_params() {
        return good_params;
    }

    public void setGood_params(String good_params) {
        this.good_params = good_params;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public int getPromotion_flag() {
        return promotion_flag;
    }

    public void setPromotion_flag(int promotion_flag) {
        this.promotion_flag = promotion_flag;
    }

    public BigDecimal getPromotion_price() {
        return promotion_price;
    }

    public void setPromotion_price(BigDecimal promotion_price) {
        this.promotion_price = promotion_price;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

    public int getInventory() {
        return inventory;
    }

    public void setInventory(int inventory) {
        this.inventory = inventory;
    }

    public int getPhoto_amount() {
        return photo_amount;
    }

    public void setPhoto_amount(int photo_amount) {
        this.photo_amount = photo_amount;
    }

    public String getGood_photo1() {
        return good_photo1;
    }

    public void setGood_photo1(String good_photo1) {
        this.good_photo1 = good_photo1;
    }

    public String getGood_photo2() {
        return good_photo2;
    }

    public void setGood_photo2(String good_photo2) {
        this.good_photo2 = good_photo2;
    }

    public String getGood_photo3() {
        return good_photo3;
    }

    public void setGood_photo3(String good_photo3) {
        this.good_photo3 = good_photo3;
    }

    public String getGood_photo4() {
        return good_photo4;
    }

    public void setGood_photo4(String good_photo4) {
        this.good_photo4 = good_photo4;
    }

    public String getGood_photo5() {
        return good_photo5;
    }

    public void setGood_photo5(String good_photo5) {
        this.good_photo5 = good_photo5;
    }


    public int getGood_status() {
        return good_status;
    }

    public void setGood_status(int good_status) {
        this.good_status = good_status;
    }
}
