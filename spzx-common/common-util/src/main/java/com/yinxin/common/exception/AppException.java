package com.yinxin.common.exception;

import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import lombok.Data;

/**
 * @author YinXin
 * @date 2024-01-12 20:04
 */
@Data
public class AppException extends RuntimeException {
    private Integer code;

    private String message;

    private ResultCodeEnum resultCodeEnum;

    public AppException(ResultCodeEnum resultCodeEnum) {
        this.code = resultCodeEnum.getCode();
        this.message = resultCodeEnum.getMessage();
        this.resultCodeEnum = resultCodeEnum;
    }
}
