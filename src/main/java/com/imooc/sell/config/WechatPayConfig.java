package com.imooc.sell.config;

import com.lly835.bestpay.config.WxPayH5Config;
import com.lly835.bestpay.service.impl.BestPayServiceImpl;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.stereotype.Component;

@Component
public class WechatPayConfig {

    @Autowired
    private WechatInfoConfig wechatInfoConfig;

    @Bean
    public BestPayServiceImpl bestPayService() {
        BestPayServiceImpl bestPayService = new BestPayServiceImpl();

        bestPayService.setWxPayH5Config(wxPayConfig());
        return bestPayService;
    }

    @Bean
    public WxPayH5Config wxPayConfig() {
        WxPayH5Config wxPayConfig = new WxPayH5Config();
        BeanUtils.copyProperties(wechatInfoConfig, wxPayConfig);
        wxPayConfig.setAppId(wechatInfoConfig.getMpAppId());
        wxPayConfig.setAppSecret(wechatInfoConfig.getMpAppSecret());
        return wxPayConfig;
    }
}
