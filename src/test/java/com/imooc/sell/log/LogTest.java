package com.imooc.sell.log;

import lombok.extern.slf4j.Slf4j;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * 日志测试
 */
@SpringBootTest
@Slf4j
public class LogTest {

    @Test
    void test1(){
        String userName="imooc";
        String password="password...";
        log.info("info.....,userName:{},password:{}",userName,password);
        log.error("error.....,userName:{},password:{}",userName,password);
        log.debug("debug.....,userName:{},password:{}",userName,password);
    }
}
