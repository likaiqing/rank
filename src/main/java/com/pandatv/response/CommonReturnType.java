package com.pandatv.response;

/**
 * @author: likaiqing
 * @create: 2019-01-28 12:44
 **/
public class CommonReturnType {
    //表明对应请求的返回处理结果"success"或"fail"
//    private String status;
    //为了统一接口格式，改为errno,errmsg,data
    private Integer errno;
    private String errmsg;

    //若status=success,则data内返回前端需要的json数据
    //若status=fail,则data内使用通用的错误码格式
    private Object data;

    //定义一个通用的创建方法
    public static CommonReturnType create(Object result) {
        return CommonReturnType.create(result, "");
    }

    public static CommonReturnType create(Object result, String errmsg) {
        CommonReturnType type = new CommonReturnType();
        type.setData(result);
        type.setErrmsg(errmsg);
        return type;
    }

    public static CommonReturnType create(Object result, Integer errno, String errmsg) {
        CommonReturnType type = new CommonReturnType();
        type.setErrno(errno);
        type.setErrmsg(errmsg);
        type.setData(result);
        return type;
    }

//    public String getStatus() {
//        return status;
//    }
//
//    public void setStatus(String status) {
//        this.status = status;
//    }


    public Integer getErrno() {
        return errno;
    }

    public void setErrno(Integer errno) {
        this.errno = errno;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
