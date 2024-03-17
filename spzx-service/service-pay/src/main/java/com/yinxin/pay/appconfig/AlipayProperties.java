package com.yinxin.pay.appconfig;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author YinXin
 * @date 2024-03-17 9:48
 */
@Data
@Component
@ConfigurationProperties(prefix = "spzx.alipay")
public class AlipayProperties {
    /**
     * 支付宝网关
     */
    private String alipayUrl;
    /**
     * 应用私钥
     */
    private String appPrivateKey;
    /**
     * 支付宝公钥
     */
    public String alipayPublicKey;
    /**
     * 应用ID
     */
    private String appId;
    /**
     * 回跳地址：支付成功后返回的页面
     */
    public String returnPaymentUrl;
    /**
     * 异步通知地址：支付成功后置处理
     */
    public String notifyPaymentUrl;

    public final static String format = "json";
    public final static String charset = "utf-8";
    public final static String sign_type = "RSA2";
}
