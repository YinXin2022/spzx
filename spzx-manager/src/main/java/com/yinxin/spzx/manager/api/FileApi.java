package com.yinxin.spzx.manager.api;

import com.yinxin.spzx.manager.service.SysFileService;
import com.yinxin.spzx.model.vo.common.Result;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import io.swagger.v3.oas.annotations.tags.Tag;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author YinXin
 * @date 2024-01-14 19:21
 */
@Tag(name = "文件接口")
@RestController
@RequiredArgsConstructor
@RequestMapping("/sys/file")
public class FileApi {
    private final SysFileService sysFileService;

    @PostMapping("/upload")
    public Result<String> uploadBy(@RequestParam(value = "file") MultipartFile file) {
        return Result.build(sysFileService.upload(file), ResultCodeEnum.SUCCESS);
    }
}
