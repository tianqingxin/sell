package com.imooc.sell.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.UUID;
@Slf4j
public class StringUtils {
    //得到32位的uuid
    public static String getUuid(){
        return UUID.randomUUID().toString().replace("-", "").toLowerCase();
    }

//    public static void main(String[] args) {
//        log.info("uuid={},length={}",getUuid(),getUuid().length());
//    }
}
