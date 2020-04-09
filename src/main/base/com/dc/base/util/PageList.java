package com.dc.base.util;

import com.dc.base.pojo.BaseModel;

import java.util.List;

public class PageList {
    public static<E> void pageQuery(List<E> list,int page,int size,String msg,BaseModel model){
        int list_size=list.size();
        if(list.size()==0){
            model.setResultCode(0);
            model.setMessage("还没有任何"+msg+"哦");
            return;
        }
        if (page * size >= list_size) {
            model.setResultCode(0);
            model.setMessage("(*╹▽╹*)没有下一页了，已经是底线了喔-_-");
            return;
        }
        int end=(page*size+size)>=list_size?list_size:(page*size+size);
        List<E> data = list.subList(page * size, end);
        model.setResultCode(1);
        model.setMessage(msg+"查询成功，该页"+msg+"数为："+data.size());
        model.setData(data);
    }
}
