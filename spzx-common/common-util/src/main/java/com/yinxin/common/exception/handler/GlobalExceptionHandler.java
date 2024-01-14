package com.yinxin.common.exception.handler;

import com.yinxin.common.exception.AppException;
import com.yinxin.spzx.model.vo.common.Result;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author YinXin
 * @date 2024-01-12 20:07
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    @ExceptionHandler(Exception.class)
    public Result error(Exception e) {
        e.printStackTrace();
        return Result.build(null, 500, e.getMessage());
    }

    @ExceptionHandler(AppException.class)
    public Result error(AppException e) {
        e.printStackTrace();
        return Result.build(null, e.getResultCodeEnum());
    }
}
