package com.dc.pojo;

import com.dc.dao.MsGoodsDao;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.Serializable;
import java.math.BigDecimal;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class MsShoppingCart implements Serializable {

    private int shopcar_id;
    private List<CartGood> good_list;

    private class CartGood {
        private int good_id;
        private int cart_amount;
        private int store_id;
        private String name;
        private String describe;
        private String good_params;
        private BigDecimal price;
        private int promotion_flag;
        private BigDecimal promotion_price;
        private String tag;
        private int inventory;
        private String good_photo;
        private int good_status;

        public int getGood_id() {
            return good_id;
        }

        public void setGood_id(int good_id) {
            this.good_id = good_id;
        }

        public int getCart_amount() {
            return cart_amount;
        }

        public void setCart_amount(int cart_amount) {
            this.cart_amount = cart_amount;
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

        public int getInventory() {
            return inventory;
        }

        public void setInventory(int inventory) {
            this.inventory = inventory;
        }

        public String getGood_photo() {
            return good_photo;
        }

        public void setGood_photo(String good_photo) {
            this.good_photo = good_photo;
        }

        public int getGood_status() {
            return good_status;
        }

        public void setGood_status(int good_status) {
            this.good_status = good_status;
        }
    }

    public CartGood setCartGood(MsGoods good, int good_amount) {
        CartGood cartGood = new CartGood();
        cartGood.setGood_id(good.getGood_id());
        cartGood.setCart_amount(good_amount);
        cartGood.setDescribe(good.getDescribe());
        cartGood.setGood_params(good.getGood_params());
        cartGood.setDescribe(good.getDescribe());
        cartGood.setName(good.getName());
        cartGood.setPrice(good.getPrice());
        cartGood.setPromotion_flag(good.getPromotion_flag());
        cartGood.setPromotion_price(good.getPromotion_price());
        cartGood.setTag(good.getTag());
        cartGood.setStore_id(good.getStore_id());
        cartGood.setGood_photo(good.getGood_photo1());
        cartGood.setTag(good.getTag());
        cartGood.setInventory(good.getInventory());

        return cartGood;
    }

    public int getShopcar_id() {
        return shopcar_id;
    }

    public void setShopcar_id(int shopcar_id) {
        this.shopcar_id = shopcar_id;
    }

    public List<CartGood> getGood_list() {
        return good_list;
    }

    public void setGood_list(List<MsGoods> goodsList, String good_amount_list) {
        List<CartGood> good_list = new ArrayList<CartGood>();
        int size=goodsList.size();
        if (size==1) {
            good_list.add(setCartGood(goodsList.get(0), Integer.parseInt(good_amount_list)));
        } else {
            String[] amount_arr = good_amount_list.split(",");
            for (int i = 0; i < size; i++) {
                good_list.add(setCartGood(goodsList.get(i), Integer.parseInt(amount_arr[i])));
            }
        }
        this.good_list = good_list;
    }
}
