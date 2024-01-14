package com.yinxin.common.utils;

import com.yinxin.common.config.MinIoPropertiesConfig;
import com.yinxin.common.contest.UrlContest;
import io.minio.MinioClient;
import io.minio.PutObjectArgs;
import io.minio.RemoveObjectArgs;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YinXin
 * @date 2024-01-14 19:24
 */
public class MinIoUtil {
    private static MinIoPropertiesConfig minIoPropertiesConfig;

    private MinIoUtil() {
    }

    @Autowired
    public void setMinIoPropertiesConfig(MinIoPropertiesConfig minIoPropertiesConfig) {
        MinIoUtil.minIoPropertiesConfig = minIoPropertiesConfig;
    }

    public static MinIoUtil getInstance() {
        return MinIoHolder.INSTANCE;
    }

    /**
     * 上传文件
     */
    @SneakyThrows
    public static String uploadFile(MultipartFile file, String fileName) {
        MinioClient minioClient = minIoPropertiesConfig.getClient();
        PutObjectArgs putObjectArgs = PutObjectArgs.builder()
                .bucket(minIoPropertiesConfig.getBucketName())
                .stream(file.getInputStream(), file.getSize(), -1)
                .object(fileName)
                .build();
        minioClient.putObject(putObjectArgs);
        return minIoPropertiesConfig.getEndpointUrl() + "/" + minIoPropertiesConfig.getBucketName() + "/" + fileName;
    }

    /**
     * 删除文件
     */
    @SneakyThrows
    public static void removeFile(String fileName) {
        MinioClient minioClient = minIoPropertiesConfig.getClient();
        minioClient.removeObject(RemoveObjectArgs.builder()
                .bucket(minIoPropertiesConfig.getBucketName())
                .object(getObjectName(fileName))
                .build());
    }

    /**
     * 获取文件对象名
     */
    private static String getObjectName(String fileName) {
        String prefix = minIoPropertiesConfig.getEndpointUrl() + UrlContest.URI_SEPARATOR + minIoPropertiesConfig.getBucketName();
        return fileName.substring(prefix.length() + 1);
    }

    public static class MinIoHolder {
        private static final MinIoUtil INSTANCE = new MinIoUtil();
    }
}
