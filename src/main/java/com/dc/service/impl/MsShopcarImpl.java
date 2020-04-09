package com.dc.service.impl;

import com.dc.base.pojo.BaseModel;
import com.dc.dao.MsGoodsDao;
import com.dc.dao.MsShopcarDao;
import com.dc.pojo.MsGoods;
import com.dc.pojo.MsShopcar;
import com.dc.pojo.MsShoppingCart;
import com.dc.service.MsShopcarService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MsShopcarImpl implements MsShopcarService {
    @Autowired
    private MsShopcarDao shopcarDao;
    @Autowired
    private MsGoodsDao goodsDao;

    @Override
    public void getShopCar(int user_id, BaseModel model) throws Exception {
        MsShoppingCart cart = new MsShoppingCart();
        MsShopcar shopcar = shopcarDao.getShopCar(user_id);
        if (shopcar == null) {
            shopcarDao.createShopCar(user_id);
            shopcar = shopcarDao.getShopCar(user_id);
        }
        List<MsGoods> goodsList=new ArrayList<MsGoods>();
        String good_id_str = shopcar.getGood_id_list();
        if (good_id_str.isEmpty()) {
            model.setResultCode(0);
            model.setMessage("购物车为空，快买点东西犒劳一下自己吧");
            return;
        }
        if(!good_id_str.contains(",")){
            goodsList.add(goodsDao.findById(Integer.parseInt(good_id_str)));
        }else {
            String[] good_arr=good_id_str.split(",");
            int len=good_arr.length;
            for(int i=0;i<len;i++){
                goodsList.add(goodsDao.findById(Integer.parseInt(good_arr[i])));
            }
        }
        cart.setShopcar_id(shopcar.getShopcar_id());
        cart.setGood_list(goodsList, shopcar.getGood_amount_list());
        model.setData(cart);
        model.setResultCode(1);
        model.setMessage("购物车查询成功，共计商品" + cart.getGood_list().size() + "件");
    }

    @Override
    public void addToShopCar(int user_id, String goods_id, BaseModel model) throws Exception {
        MsShopcar shopcar = shopcarDao.getShopCar(user_id);
        if (shopcar == null) {
            shopcarDao.createShopCar(user_id);
            shopcar = shopcarDao.getShopCar(user_id);
        }
        String goods_id_str = shopcar.getGood_id_list();
        String goods_amount_str = shopcar.getGood_amount_list();
        if (goods_id_str == null || goods_id_str.isEmpty()) {
            goods_id_str += goods_id;
            goods_amount_str += "1";
            shopcar.setGood_id_list(goods_id_str);
            shopcar.setGood_amount_list(goods_amount_str);
            shopcarDao.upShopCar(shopcar);
            model.setResultCode(1);
            model.setMessage("加入购物车成功");
            model.setData(shopcar);
            return;
        }
        if (goods_id_str.indexOf(",") == -1) {
            if (goods_id_str.equals(goods_id)) {
                model.setResultCode(0);
                model.setMessage("该商品已加入购物车，请勿重复添加");
                return;
            }
        }
        String[] goods_id_arr = goods_id_str.split(",");
        int len = goods_id_arr.length;
        for (int i = 0; i < len; i++) {
            if (goods_id_arr[i].equals(goods_id)) {
                model.setResultCode(0);
                model.setMessage("该商品已加入购物车，请勿重复添加");
                return;
            }
        }
        goods_id_str += "," + goods_id;
        goods_amount_str += ",1";
        shopcar.setGood_id_list(goods_id_str);
        shopcar.setGood_amount_list(goods_amount_str);
        shopcarDao.upShopCar(shopcar);
        model.setResultCode(1);
        model.setMessage("加入购物车成功");
        model.setData(shopcar);
    }

    @Override
    public void upShopCar(int user_id, String goods_id, String goods_num, BaseModel model) throws Exception {
        MsShopcar shopcar = shopcarDao.getShopCar(user_id);
        if (shopcar == null) {
            shopcarDao.createShopCar(user_id);
            shopcar = shopcarDao.getShopCar(user_id);
        }
        String goods_id_str = shopcar.getGood_id_list();
        String goods_amount_str = shopcar.getGood_amount_list();
        if (goods_id_str == null || goods_id_str.isEmpty()) {
            model.setResultCode(0);
            model.setMessage("购物车为空，拒绝修改");
            return;
        }
        if (!goods_id_str.contains(",")) {
            if (!goods_id_str.equals(goods_id)) {
                model.setResultCode(0);
                model.setMessage("该商品不在购物车内，请检查参数，本次拒绝修改");
                return;
            }
            goods_amount_str = goods_num;
            shopcar.setGood_amount_list(goods_amount_str);
            shopcarDao.upShopCar(shopcar);
            model.setResultCode(1);
            model.setMessage("购物车修改成功");
            model.setData(shopcar);
            return;
        }
        String[] up_goods_id = goods_id.split(",");
        String[] up_goods_amount = goods_num.split(",");
        String[] goods_id_arr = goods_id_str.split(",");
        String[] goods_amount_arr = goods_amount_str.split(",");
        int up_len = up_goods_id.length;
        int len = goods_id_arr.length;
        for (int i = 0; i < up_len; i++) {
            int count = 0;
            for (int j = 0; j < len; j++) {
                if (goods_id_arr[j].equals(up_goods_id[i])) {
                    count++;
                    goods_amount_arr[j] = up_goods_amount[i];
                    break;
                }
            }
            if (count != 1) {
                model.setResultCode(0);
                model.setMessage("存在商品不在购物车内，请检查参数，本次拒绝修改");
                return;
            }
        }
        goods_id_str = "";
        goods_amount_str = "";
        for (int i = 0; i < len; i++) {
            goods_id_str += goods_id_arr[i] + ",";
            goods_amount_str += goods_amount_arr[i] + ",";
        }
        goods_id_str = goods_id_str.substring(0, goods_id_str.length() - 1);
        goods_amount_str = goods_amount_str.substring(0, goods_amount_str.length() - 1);
        shopcar.setGood_id_list(goods_id_str);
        shopcar.setGood_amount_list(goods_amount_str);
        shopcarDao.upShopCar(shopcar);
        model.setResultCode(1);
        model.setMessage("购物车修改成功");
        model.setData(shopcar);
    }

    @Override
    public void deleteFromShopCar(int user_id, String goods_id, BaseModel model) throws Exception {
        MsShopcar shopcar = shopcarDao.getShopCar(user_id);
        if (shopcar == null) {
            shopcarDao.createShopCar(user_id);
            shopcar = shopcarDao.getShopCar(user_id);
        }
        String goods_id_str = shopcar.getGood_id_list();
        String goods_amount_str = shopcar.getGood_amount_list();
        if (goods_id_str == null || goods_id_str.isEmpty()) {
            model.setResultCode(0);
            model.setMessage("购物车为空，拒绝修改");
            return;
        }
        if (!goods_id_str.contains(",")) {
            if (!goods_id_str.equals(goods_id)) {
                model.setResultCode(0);
                model.setMessage("该商品不在购物车内，请检查参数，本次拒绝删除商品");
                return;
            }
            shopcar.setGood_id_list("");
            shopcar.setGood_amount_list("");
            shopcarDao.upShopCar(shopcar);
            model.setResultCode(1);
            model.setMessage("购物车删除商品成功");
            model.setData(shopcar);
            return;
        }
        String[] up_goods_id = goods_id.split(",");
        String[] goods_id_arr = goods_id_str.split(",");
        String[] goods_amount_arr = goods_amount_str.split(",");
        int up_len = up_goods_id.length;
        int len = goods_id_arr.length;
        for (int i = 0; i < up_len; i++) {
            int count = 0;
            for (int j = 0; j < len; j++) {
                if (goods_id_arr[j].equals(up_goods_id[i])) {
                    count++;
                    goods_id_arr[j] = "";
                    goods_amount_arr[j] = "";
                    break;
                }
            }
            if (count != 1) {
                model.setResultCode(0);
                model.setMessage("存在商品不在购物车内，请检查参数，本次拒绝删除商品");
                return;
            }
        }
        goods_id_str = "";
        goods_amount_str = "";
        for (int i = 0; i < len; i++) {
            if (goods_id_arr[i].isEmpty()) {
                continue;
            }
            goods_id_str += goods_id_arr[i] + ",";
            goods_amount_str += goods_amount_arr[i] + ",";
        }
        model.setMessage("购物车删除商品成功");
        if(!goods_id_str.isEmpty()){
            goods_id_str = goods_id_str.substring(0, goods_id_str.length() - 1);
            goods_amount_str = goods_amount_str.substring(0, goods_amount_str.length() - 1);
            model.setMessage("购物车已经清空，快去屯点东西过年吧");
        }
        shopcar.setGood_id_list(goods_id_str);
        shopcar.setGood_amount_list(goods_amount_str);
        shopcarDao.upShopCar(shopcar);
        model.setResultCode(1);
        model.setData(shopcar);
    }
}
