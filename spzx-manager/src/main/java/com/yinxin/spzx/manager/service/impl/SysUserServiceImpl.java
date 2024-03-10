package com.yinxin.spzx.manager.service.impl;

import cn.hutool.core.util.StrUtil;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.yinxin.common.contest.RedisContest;
import com.yinxin.common.exception.AppException;
import com.yinxin.common.redis.RedisCache;
import com.yinxin.common.utils.AssertUtil;
import com.yinxin.common.utils.SysAuthContextUtil;
import com.yinxin.common.utils.MinIoUtil;
import com.yinxin.spzx.manager.mapper.SysRoleUserMapper;
import com.yinxin.spzx.manager.mapper.SysUserMapper;
import com.yinxin.spzx.manager.service.SysUserService;
import com.yinxin.spzx.model.dto.system.AssginRoleDto;
import com.yinxin.spzx.model.dto.system.LoginDto;
import com.yinxin.spzx.model.dto.system.SysUserDto;
import com.yinxin.spzx.model.entity.system.SysUser;
import com.yinxin.spzx.model.vo.common.ResultCodeEnum;
import com.yinxin.spzx.model.vo.system.LoginVo;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.DigestUtils;

import java.util.List;
import java.util.UUID;
import java.util.concurrent.TimeUnit;

/**
 * @author YinXin
 * @date 2024-01-12 17:24
 */
@Service
@Transactional(readOnly = true)
@RequiredArgsConstructor
public class SysUserServiceImpl implements SysUserService {
    private final SysUserMapper sysUserMapper;
    private final SysRoleUserMapper sysRoleUserMapper;
    private final RedisCache redisCache;

    @Override
    public LoginVo login(LoginDto loginDto) {
        String validateCode = redisCache.getCacheObject(RedisContest.SYS_LOGIN_VALIDATE_CODE + loginDto.getCodeKey(), String.class);
        AssertUtil.isTrueThrow(StrUtil.isEmpty(validateCode) || !StrUtil.equalsIgnoreCase(validateCode, loginDto.getCaptcha()), () -> new AppException(ResultCodeEnum.VALIDATECODE_ERROR));

        SysUser sysUser = sysUserMapper.getByUsername(loginDto.getUserName());
        AssertUtil.isTrueThrow(sysUser == null, () -> new AppException(ResultCodeEnum.LOGIN_ERROR));
        AssertUtil.isFalseThrow(sysUser.getPassword().equals(DigestUtils.md5DigestAsHex(loginDto.getPassword().getBytes())), () -> new AppException(ResultCodeEnum.LOGIN_ERROR));

        String token = UUID.randomUUID().toString().replaceAll("-", "");
        redisCache.setCacheObject(RedisContest.SYS_LOGIN_USER + token, sysUser, 30, TimeUnit.MINUTES);
        redisCache.deleteObject(RedisContest.SYS_LOGIN_VALIDATE_CODE + loginDto.getCodeKey());
        return LoginVo.build(token, null);
    }

    @Override
    public SysUser getUserInfo(String token) {
        return SysAuthContextUtil.get();
    }

    @Override
    public void logout(String token) {
        redisCache.deleteObject(RedisContest.SYS_LOGIN_USER + token);
    }

    @Override
    public PageInfo<SysUser> pageBy(SysUserDto sysUserDto, Integer pageNum, Integer pageSize) {
        PageHelper.startPage(pageNum, pageSize);
        List<SysUser> sysUsers = sysUserMapper.pageAllByNameAndCreateTime(sysUserDto);
        PageInfo pageInfo = new PageInfo(sysUsers);
        return pageInfo;
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void create(SysUser sysUser) {
        SysUser user = sysUserMapper.getByUsername(sysUser.getUserName());
        AssertUtil.isTrueThrow(user != null, () -> new AppException(ResultCodeEnum.USER_NAME_IS_EXISTS));

        sysUser.md5Password();
        sysUser.setStatus(1);
        sysUserMapper.insert(sysUser);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void update(SysUser sysUser, boolean predicate) {
        if (!predicate) {
            SysUser user = sysUserMapper.getByUsername(sysUser.getUserName());
            AssertUtil.isTrueThrow(user != null, () -> new AppException(ResultCodeEnum.USER_NAME_IS_EXISTS));
        }

        SysUser user = sysUserMapper.getById(sysUser.getId());
        sysUserMapper.update(sysUser);
        if (!StrUtil.isEmpty(sysUser.getAvatar())) {
            MinIoUtil.removeFile(user.getAvatar());
        }
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void delete(Long id) {
        SysUser user = sysUserMapper.getById(id);
        sysUserMapper.deleteById(id);
        MinIoUtil.removeFile(user.getAvatar());
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRoles(AssginRoleDto assginRoleDto) {
        sysRoleUserMapper.deleteByUserId(assginRoleDto.getUserId());

        assginRoleDto.getRoleIdList().forEach(x -> sysRoleUserMapper.insert(assginRoleDto.getUserId(), x));
    }
}
