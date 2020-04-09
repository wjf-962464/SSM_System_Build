package com.dc.base.pojo;

import com.wordnik.swagger.annotations.ApiModelProperty;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

public class BaseModel implements Serializable {
    @ApiModelProperty("定义结果状态码,'1'为正常状态，'0'为特殊状态")
    private Integer resultCode = 1;//定义返回状态码
    @ApiModelProperty("定义返回的错误消息")
    private String message;//定义一个消息
    @ApiModelProperty("定义返回的数据集合")
    private Object data;//数据集

    public void setFailure(String msg){
        this.resultCode=0;
        this.message=msg;
    }
    public void setFailure(String msg,Object data){
        this.resultCode=0;
        this.message=msg;
        this.data=data;
    }
    public void setSucceed(String msg,Object data){
        this.resultCode=1;
        this.message=msg;
        this.data=data;
    }
    public void setSucceed(String msg){
        this.resultCode=1;
        this.message=msg;
    }
    public Integer getResultCode() {
        return resultCode;
    }

    public void setResultCode(Integer resultCode) {
        this.resultCode = resultCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

}
