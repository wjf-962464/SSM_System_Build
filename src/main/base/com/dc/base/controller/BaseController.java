package com.dc.base.controller;

import com.dc.base.contants.ErrorMesgEnum;
import com.dc.base.pojo.BaseModel;
import com.dc.base.pojo.BusinessException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.sql.SQLException;

/**
 * @author Enzo
 * @Description TODO
 * @date 2018-11-12 15:47
 */
@Controller
@ResponseBody
public class BaseController {
    private static Logger log = Logger.getLogger(BaseController.class);
    protected HttpServletRequest request;
    protected HttpServletResponse response;
    protected HttpSession session;

    @InitBinder
    public void initHttpParams(HttpServletRequest req, HttpServletResponse res) {
        request = req;
        response = res;
        session = req.getSession();
    }

    /**
     * @return void
     * @title:<h3> baseModel参数添加前缀 <h3>
     * @author: Enzo
     * @date: 2018-8-16 17:07
     * @params [binder]
     **/
    @InitBinder("baseModel")
    public void initBinder1(WebDataBinder binder) {
        binder.setFieldDefaultPrefix("baseModel.");
    }
    /**
     * @title:<h3> 统一处理业务异常 <h3>
     * @author: Enzo
     * @date: 2018-11-13 14:50
     * @params [ex]
     * @return com.dc.base.exception.BusinessException
     **/
    @ExceptionHandler(BusinessException.class)
    public BaseModel businessException(BusinessException ex) {
        BaseModel baseModel = new BaseModel();
        baseModel.setResultCode(1000);
        baseModel.setMessage(ex.getMessage());
        log.info(ex.getMessage());
        return baseModel;
    }
    /**
     * @title:<h3> 统一处理SQL异常 <h3>
     * @author: Enzo
     * @date: 2018-11-13 14:50
     * @params [ex]
     * @return com.dc.base.exception.BusinessException
     **/
    @ExceptionHandler({SQLException.class})
    public BaseModel SQLException(SQLException sqlEx) {
        BusinessException ex = new BusinessException(ErrorMesgEnum.SQL_ERROR);
        BaseModel baseModel = new BaseModel();
        baseModel.setResultCode(ex.getCode());
        baseModel.setMessage(ex.getMessage());
        return baseModel;
    }

    /**
     * @title:<h3> 统一处理系统异常 <h3>
     * @author: Enzo
     * @date: 2018-11-13 14:50
     * @params [ex]
     * @return com.dc.base.exception.BusinessException
     **/
    @ExceptionHandler(Exception.class)
    public BaseModel exception(Exception ex) {
        BaseModel baseModel = new BaseModel();
        BusinessException bex = new BusinessException();
        if("org.springframework.jdbc.support.SQLErrorCodeSQLExceptionTranslator".equals(ex.getStackTrace()[0].getClassName())){
            bex.setMessage(ErrorMesgEnum.SQL_ERROR);
        }else{
            bex.setMessage(ErrorMesgEnum.SYS_ERROR);

        }
        baseModel.setResultCode(bex.getCode());
        baseModel.setMessage(bex.getMessage());
        return baseModel;
    }
}
