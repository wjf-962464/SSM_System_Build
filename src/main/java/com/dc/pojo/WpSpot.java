package com.dc.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
import org.joda.time.DateTime;

import java.io.Serializable;
import java.sql.Timestamp;


public class WpSpot implements Serializable {
    @ApiModelProperty("停车位编号，主键")
    int spot_id;
    @ApiModelProperty("停车场编号，外键")
    int park_id;
    @ApiModelProperty("停车位名")
    String spot_serial;
    @ApiModelProperty("停车位坐标")
    String spot_site;
    @ApiModelProperty("停车位占用状态，'1'表示正在占用，'2'表示预约占用，'0'表示空闲状态")
    int occupy_status;
    @ApiModelProperty("停车位创建时间")
    private Timestamp create_time;
    @ApiModelProperty("停车位上一次数据修改时间")
    private Timestamp last_modify;
    @ApiModelProperty("停车位工作状态，'1'表示正常工作，'0'表示车位注销，" +
            "'2'表示高层车位在上升，'3'表示高层车位在下降")
    int work_status;

    public WpSpot() {
        super();
    }

    public WpSpot(int park_id, String area, String number) {
        super();
        this.park_id = park_id;
        this.spot_serial = area + number;
        this.spot_site = area + "区" + number + "车位";
    }
    public WpSpot(int park_id, String area, String number,int level) {
        super();
        this.park_id = park_id;
        this.spot_serial = area + number+"-"+level;
        switch (level){
            case 1:
                this.spot_site = area + "区" + number + "车位-下层";
                break;
            case 2:
                this.spot_site = area + "区" + number + "车位-中层";
                break;
            case 3:
                this.spot_site = area + "区" + number + "车位-上层";
                break;
        }
    }
    public int getSpot_id() {
        return spot_id;
    }

    public void setSpot_id(int spot_id) {
        this.spot_id = spot_id;
    }

    public int getPark_id() {
        return park_id;
    }

    public void setPark_id(int park_id) {
        this.park_id = park_id;
    }

    public String getSpot_serial() {
        return spot_serial;
    }

    public void setSpot_serial(String spot_serial) {
        this.spot_serial = spot_serial;
    }

    public String getSpot_site() {
        return spot_site;
    }

    public void setSpot_site(String spot_site) {
        this.spot_site = spot_site;
    }

    public int getOccupy_status() {
        return occupy_status;
    }

    public void setOccupy_status(int occupy_status) {
        this.occupy_status = occupy_status;
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

    public int getWork_status() {
        return work_status;
    }

    public void setWork_status(int work_status) {
        this.work_status = work_status;
    }
}
