package com.imooc.sell.utils;

import com.imooc.sell.VO.ResponseData;

/**
 * 返回方法
 */
public class ResultVOUtils {

    /**
     * 返回成功
     * @param o
     * @return
     */
    public static ResponseData successResult(Object o){
        ResponseData responseData = new ResponseData();
        responseData.setData(o);
        responseData.setCode(0);
        responseData.setMsg("成功");
        return responseData;
    }
    /**
     * 返回失败
     * @param msg 失败信息
     * @return
     */
    public static ResponseData failureResult(String msg){
        ResponseData responseData = new ResponseData();
        responseData.setData(null);
        responseData.setCode(1);
        responseData.setMsg(msg);
        return responseData;
    }
}
