package com.yinxin.spzx.manager.service.impl;

import cn.hutool.core.date.DateUtil;
import com.yinxin.common.contest.FileContest;
import com.yinxin.common.utils.MinIoUtil;
import com.yinxin.spzx.manager.service.SysFileService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;
import java.util.UUID;

/**
 * @author YinXin
 * @date 2024-01-14 19:53
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysFileServiceImpl implements SysFileService {

    @Override
    public String upload(MultipartFile file) {
        String dateDir = DateUtil.format(new Date(), FileContest.FILE_DATE_FORMAT);
        String uuid = UUID.randomUUID().toString().replace("-", "");
        String fileName = dateDir + FileContest.FILE_SEPARATOR + uuid + file.getOriginalFilename();
        return MinIoUtil.uploadFile(file, fileName);
    }
}
