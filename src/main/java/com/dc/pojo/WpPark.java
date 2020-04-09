package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;

public class WpPark implements Serializable {
    @ApiModelProperty("停车场编号，主键")
    private int park_id;
    @ApiModelProperty("poi编号，来自高德")
    private String poi_id;
    @ApiModelProperty("停车场名")
    private String park_name;
    @ApiModelProperty("停车场坐标，经纬度")
    private String park_gps;
    @ApiModelProperty("停车场位置信息")
    private String park_info;
    @ApiModelProperty("剩余空闲车位数")
    private int spot_remain;
    @ApiModelProperty("总计车位数")
    private int spot_total;
    @ApiModelProperty("停车场评分，以100为满分，推荐时以分数降序")
    private int park_grade;
    @ApiModelProperty("停车场创建时间")
    private Timestamp create_time;
    @ApiModelProperty("上一次数据修改时间")
    private Timestamp last_modify;
    @ApiModelProperty("停车场工作状态，'1'为正常，'0'为注销")
    private int park_status;

    public String getPoi_id() {
        return poi_id;
    }

    public void setPoi_id(String poi_id) {
        this.poi_id = poi_id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public String getPark_name() {
        return park_name;
    }

    public void setPark_name(String park_name) {
        this.park_name = park_name;
    }


    public String getPark_gps() {
        return park_gps;
    }

    public void setPark_gps(String park_gps) {
        this.park_gps = park_gps;
    }

    public String getPark_info() {
        return park_info;
    }

    public void setPark_info(String park_info) {
        this.park_info = park_info;
    }

    public int getSpot_remain() {
        return spot_remain;
    }

    public void setSpot_remain(int spot_remain) {
        this.spot_remain = spot_remain;
    }

    public int getSpot_total() {
        return spot_total;
    }

    public void setSpot_total(int spot_total) {
        this.spot_total = spot_total;
    }

    public int getPark_grade() {
        return park_grade;
    }

    public void setPark_grade(int park_grade) {
        this.park_grade = park_grade;
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

    public int getPark_status() {
        return park_status;
    }

    public void setPark_status(int park_status) {
        this.park_status = park_status;
    }
}
