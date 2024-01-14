package com.yinxin.spzx.manager.service;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YinXin
 * @date 2024-01-14 19:50
 */
@Service
public interface SysFileService {

    String upload(MultipartFile file);
}
