package com.pandatv.controller;

import com.pandatv.error.BusinessException;
import com.pandatv.error.EmBusinessError;
import com.pandatv.response.CommonReturnType;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import javax.servlet.http.HttpServletRequest;
import java.util.HashMap;

/**
 * @author: likaiqing
 * @create: 2019-01-28 12:39
 **/
public class BaseController {
    public static final String CONTENT_TYPE_FORMED = "application/x-www-form-urlencoded";
    Logger logger = LoggerFactory.getLogger(getClass());

    //定义Exceptionhandler解决未被controller层吸收的exception
    @ExceptionHandler(Exception.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public Object handlerException(HttpServletRequest request, Exception ex) {
        logger.info(ex.getMessage());
        ex.printStackTrace();
        if (ex instanceof BusinessException) {
            BusinessException businessException = (BusinessException) ex;
//        CommonReturnType commonReturnType = new CommonReturnType();
//        commonReturnType.setStatus("fail");
//        HashMap<Object, Object> responseData = new HashMap<>();
//        responseData.put("errCode", businessException.getErrCode());
//        responseData.put("errMsg", businessException.getErrMsg());
////        commonReturnType.setData(ex);
//        commonReturnType.setData(responseData);
            HashMap<Object, Object> responseData = new HashMap<>();
//            responseData.put("errCode", businessException.getErrCode());
//            responseData.put("errMsg", businessException.getErrMsg());
            return CommonReturnType.create(responseData, businessException.getErrCode(), ex.getMessage());
        } else {
            HashMap<Object, Object> responseData = new HashMap<>();
//            responseData.put("errCode", EmBusinessError.UNKNOWN_ERROR.getErrCode());
//            responseData.put("errMsg", EmBusinessError.UNKNOWN_ERROR.getErrMsg());
            return CommonReturnType.create(responseData, EmBusinessError.UNKNOWN_ERROR.getErrCode(), ex.getMessage());
        }

    }
}
