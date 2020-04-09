package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;

public class MsGoodDetail implements Serializable {
    private int good_id;
    private int store_id;
    private GoodDetail goodDetail;
    private StoreDetail storeDetail;
    public MsGoodDetail(){
        super();
    }
    public MsGoodDetail(MsGoods good,MsStores store){
        super();
        this.good_id=good.getGood_id();
        this.store_id=good.getStore_id();
        setGoodDetail(good);
        setStoreDetail(store);
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

    public GoodDetail getGoodDetail() {
        return goodDetail;
    }

    public void setGoodDetail(MsGoods good) {
        GoodDetail goodDetail = new GoodDetail();
        goodDetail.setName(good.getName());
        goodDetail.setDescribe(good.getDescribe());
        goodDetail.setEvaluate_amount(good.getEvaluate_amount());
        goodDetail.setGood_params(good.getGood_params());
        List<String> photoList = new ArrayList<String>();
        for (int i = 0; i < good.getPhoto_amount(); i++) {
            switch (i) {
                case 0:
                    photoList.add(good.getGood_photo1());
                    break;
                case 1:
                    photoList.add(good.getGood_photo2());
                    break;
                case 2:
                    photoList.add(good.getGood_photo3());
                    break;
                case 3:
                    photoList.add(good.getGood_photo4());
                    break;
                case 4:
                    photoList.add(good.getGood_photo5());
                    break;
            }
        }
        goodDetail.setGood_photo(photoList);
        goodDetail.setInventory(good.getInventory());
        goodDetail.setMonthly_sales(good.getMonthly_sales());
        goodDetail.setPhoto_amount(good.getPhoto_amount());
        goodDetail.setPrice(good.getPrice());
        goodDetail.setPromotion_flag(good.getPromotion_flag());
        goodDetail.setRank(good.getRank());
        goodDetail.setPromotion_price(good.getPromotion_price());
        goodDetail.setTag(good.getTag());
        this.goodDetail = goodDetail;
    }

    public StoreDetail getStoreDetail() {
        return storeDetail;
    }

    public void setStoreDetail(MsStores store) {
        StoreDetail storeDetail=new StoreDetail();
        storeDetail.setName(store.getName());
        storeDetail.setDeli_rank(store.getDeli_rank());
        storeDetail.setDesc_rank(store.getDesc_rank());
        storeDetail.setServ_rank(store.getServ_rank());
        storeDetail.setTotal_rank(store.getTotal_rank());
        this.storeDetail = storeDetail;
    }

    private class GoodDetail {
        private String name;
        private String describe;
        private String good_params;
        private BigDecimal price;
        private int promotion_flag;
        private BigDecimal promotion_price;
        private int photo_amount;
        private List<String> good_photo;
        private String tag;
        private int rank;
        private int inventory;
        private int evaluate_amount;
        private int monthly_sales;

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

        public int getPhoto_amount() {
            return photo_amount;
        }

        public void setPhoto_amount(int photo_amount) {
            this.photo_amount = photo_amount;
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

        public List<String> getGood_photo() {
            return good_photo;
        }

        public void setGood_photo(List<String> good_photo) {
            this.good_photo = good_photo;
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
    }

    private class StoreDetail {
        private String name;
        @ApiModelProperty("描述评分")
        private float desc_rank;
        @ApiModelProperty("服务评分")
        private float serv_rank;
        @ApiModelProperty("物流评分")
        private float deli_rank;
        @ApiModelProperty("店铺综合等级")
        private String total_rank;

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
}
