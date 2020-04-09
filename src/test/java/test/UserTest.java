package test;

import com.alibaba.fastjson.JSON;
import com.dc.base.pojo.BaseModel;
import com.dc.pojo.MsOrders;
import com.dc.pojo.User;
import com.dc.pojo.WpPark;
import com.dc.service.*;
import org.apache.log4j.Logger;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:spring-mybatis.xml")
public class UserTest {
    private Logger log = Logger.getLogger(UserTest.class);
    @Autowired
    private MsGoodsService goodsService;
    @Autowired
    private UserService service;
    @Autowired
    private WpParkService parkService;
    @Autowired
    private MsShopcarService shopcarService;
    @Autowired
    private WpSpotOrderService orderService;

    private BaseModel baseModel = new BaseModel();
    private User user = new User();


    public void shopCarTest()throws Exception{
        shopcarService.getShopCar(1,baseModel);
        log.info("SQLtest"+JSON.toJSONString(baseModel));
    }
/*    @Test
    public void getRadioByChapter() throws Exception {
        radioService.getRadioByChapter(1,baseModel);
        log.info("SQLtest"+ JSON.toJSONString(baseModel));
    }*/

    @Test
    public void insertRadio() throws Exception {
        service.userLogin("17374131097","123456",baseModel);
        log.info("SQLtest"+JSON.toJSONString(baseModel));
//        log.info("新增用户的id为："+user.getUser_name());
    }


    public void addInventory()throws Exception{
        goodsService.getGoodsList(1,5,baseModel);
        log.info("SQLtest"+JSON.toJSONString(baseModel));
    }


    public void userRegister()throws Exception{
        service.userRegister("17374131093","1234",baseModel);
        log.info("SQLtest"+JSON.toJSONString(baseModel));
    }


    public void getGoodList()throws Exception{
        goodsService.getGoodsList(0,3,baseModel);
        log.info("SQLtest"+JSON.toJSONString(baseModel));
    }

    public void parkRegister()throws Exception{
        orderService.addSpotOrder(1,4,baseModel);
        log.info("SQLtest"+JSON.toJSONString(baseModel));
    }
}
