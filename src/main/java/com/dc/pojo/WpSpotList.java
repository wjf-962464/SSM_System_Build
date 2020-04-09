package com.dc.pojo;

import org.joda.time.DateTime;

import java.io.Serializable;

public class WpSpotList implements Serializable {
    int spot_list_id;
    int park_id;
    String spot_name;
    int spot_type;
    int free_spot;
    String last_modify;
    String create_time;
    int status;
    public WpSpotList(){
        super();
    }
    public WpSpotList(int park_id,String spot_name,int spot_type){
        super();
        this.park_id=park_id;
        this.spot_name=spot_name;
        this.spot_type=spot_type;
        this.free_spot=spot_type;
    }
    public int getSpot_list_id() {
        return spot_list_id;
    }

    public void setSpot_list_id(int spot_list_id) {
        this.spot_list_id = spot_list_id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public String getSpot_name() {
        return spot_name;
    }

    public void setSpot_name(String spot_name) {
        this.spot_name = spot_name;
    }

    public int getSpot_type() {
        return spot_type;
    }

    public void setSpot_type(int spot_type) {
        this.spot_type = spot_type;
    }

    public int getFree_spot() {
        return free_spot;
    }

    public void setFree_spot(int free_spot) {
        this.free_spot = free_spot;
    }


    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }
}
