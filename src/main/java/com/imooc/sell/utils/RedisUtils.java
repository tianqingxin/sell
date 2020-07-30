//package com.imooc.sell.utils;
//
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.data.redis.core.StringRedisTemplate;
//import org.springframework.util.StringUtils;
//
//public class RedisUtils {
//
//    @Autowired
//    private static StringRedisTemplate redisTemplate;
//
//    /**加锁
//     * @param key
//     * @param value 超时时间戳
//     * @return
//     */
//    public static boolean lock(String key, String value) {
//        //返回值false，说明没有获取锁
//        if (!redisTemplate.opsForValue().setIfAbsent(key, value)) {
//            return false;
//        }
//        String currentValue = redisTemplate.opsForValue().get(key);
//        if (currentValue != null
//                && Long.getLong(currentValue).equals(System.currentTimeMillis())) {
//            //判断getAndSet方法后是否相同，即该key是否正在被其他线程使用
//            String oldValue = redisTemplate.opsForValue().getAndSet(key, value);
//            if (oldValue != null && currentValue.equals(oldValue))
//                return true;
//        }
//        return false;
//    }
//
//    public static void unLock(String key){
//        String value = redisTemplate.opsForValue().get(key);
//        if(!StringUtils.isEmpty(value)){
//            redisTemplate.delete(key);
//        }
//    }
//}
