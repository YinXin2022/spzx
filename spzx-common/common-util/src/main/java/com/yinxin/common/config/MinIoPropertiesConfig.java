package com.yinxin.common.config;

import io.minio.MinioClient;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

/**
 * @author YinXin
 * @date 2024-01-14 19:15
 */
@Data
@Component
@ConfigurationProperties(prefix = "spzx.minio")
public class MinIoPropertiesConfig {
    /**
     *  端点地址
     */
    private String endpointUrl;
    /**
     * key
     */
    private String accessKey;
    /**
     * secret
     */
    private String secretKey;
    /**
     * 存储桶名称
     */
    private String bucketName;

    /**
     * 获取minio客户端
     * @return
     */
    public MinioClient getClient() {
        return MinioClient.builder()
                .endpoint(endpointUrl)
                .credentials(accessKey, secretKey)
                .build();
    }
}
