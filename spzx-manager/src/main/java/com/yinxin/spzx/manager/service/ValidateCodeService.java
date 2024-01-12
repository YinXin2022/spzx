package com.yinxin.spzx.manager.service;

import com.yinxin.spzx.model.vo.system.ValidateCodeVo;
import org.springframework.stereotype.Service;

/**
 * @author YinXin
 * @date 2024-01-12 20:42
 */
@Service
public interface ValidateCodeService {
    ValidateCodeVo generateValidateCode();
}
