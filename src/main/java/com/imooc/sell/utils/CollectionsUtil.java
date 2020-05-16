package com.imooc.sell.utils;

import com.alibaba.fastjson.JSON;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * 复制集合
 */
public class CollectionsUtil {

    /**
     * 复制list
     * @param list
     * @param <T>
     * @return
     */
    public static <T> List copyList(List<T> list) {
        if (CollectionUtils.isEmpty(list)) {
            return new ArrayList();
        }
        return JSON.parseArray(JSON.toJSONString(list), list.get(0).getClass());
    }

    /**
     * 复制map
     * @param map
     * @return
     */
    public static Map<String, Object> copyMap(Map map) {
        return JSON.parseObject(JSON.toJSONString(map));
    }
}
