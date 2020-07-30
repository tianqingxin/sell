package com.imooc.sell.utils;

import lombok.extern.slf4j.Slf4j;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.format.FormatStyle;
import java.util.concurrent.BlockingDeque;
import java.util.concurrent.LinkedBlockingDeque;

@Slf4j
public class TestUtils {

    private BlockingDeque blockingDeque = new LinkedBlockingDeque(1);
    public static void getTest(String ... strings){
        for (int i =0 ; i < strings.length; i++){
            String str=strings[i];
            log.info("str={}",str);
        }
    }

    public static void main(String[] args){

//        getTest("1","2","3");
//        dataFormatter();
        //启动线程方法;start()和run()区别：
        //start()方法是真正意义上的启动线程，该方法会创建一个新线程，去执行线程中的程序；
        //run()方法跟普通程序执行一致，按顺序执行，在执行完成以后再进行下一步程序的执行。
//        newThread().start();
    }

    public static void dataFormatter(){
        LocalDateTime dateTime = LocalDateTime.now();
//        LocalDateTime dateTime = LocalDateTime.of(2019,10,10,10,10,10);
        //yyyy年MM月dd日
        DateTimeFormatter formatterZw = DateTimeFormatter.ofLocalizedDate(FormatStyle.LONG);
        //例：2020年5月22日 星期五
        DateTimeFormatter formatter = DateTimeFormatter.ofLocalizedDate(FormatStyle.FULL);
        //2020-5-22
        DateTimeFormatter formatter1 = DateTimeFormatter.ofLocalizedDate(FormatStyle.MEDIUM);
        //20-5-22
        DateTimeFormatter formatter2 = DateTimeFormatter.ofLocalizedDate(FormatStyle.SHORT);
        //2020-05-22
        DateTimeFormatter formatter3 = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        log.info("dateTime={}",dateTime.format(formatterZw));
        log.info("dateTime={}",dateTime.format(formatter));
        log.info("dateTime={}",dateTime.format(formatter1));
        log.info("dateTime={}",dateTime.format(formatter2));
        log.info("dateTime={}",dateTime.format(formatter3));
    }

    public static Thread newThread(){
       return new Thread(()->dataFormatter());
    }
}
