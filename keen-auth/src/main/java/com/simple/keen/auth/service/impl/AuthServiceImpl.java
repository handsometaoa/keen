package com.simple.keen.auth.service.impl;

import cn.dev33.satoken.stp.SaTokenInfo;
import cn.dev33.satoken.stp.StpUtil;
import cn.hutool.crypto.digest.BCrypt;
import com.github.pagehelper.PageSerializable;
import com.simple.keen.auth.mapper.AuthMapper;
import com.simple.keen.auth.model.param.LoginParam;
import com.simple.keen.auth.model.param.RegisterParam;
import com.simple.keen.auth.model.request.AuthQuery;
import com.simple.keen.auth.model.vo.LoginUserInfoVO;
import com.simple.keen.auth.service.IAuthService;
import com.simple.keen.auth.utils.AuthUtil;
import com.simple.keen.common.consts.Consts;
import com.simple.keen.common.consts.MsgConsts;
import com.simple.keen.common.consts.ResultCode;
import com.simple.keen.common.exception.KeenException;
import com.simple.keen.common.utils.RedisUtil;
import com.simple.keen.common.utils.StringUtils;
import com.simple.keen.message.service.IChatMessageService;
import com.simple.keen.monitor.model.query.LoginLogQuery;
import com.simple.keen.monitor.model.query.OperateLogQuery;
import com.simple.keen.monitor.model.vo.LoginLogVO;
import com.simple.keen.monitor.service.ILoginLogService;
import com.simple.keen.monitor.service.IOperateLogService;
import com.simple.keen.system.mapper.UserMapper;
import com.simple.keen.system.model.entity.User;
import com.simple.keen.system.model.enums.StatusType;
import com.simple.keen.system.model.query.UserQuery;
import com.simple.keen.system.service.IUserService;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.Objects;

/**
 * @author SinceNovember
 * @date 2023/1/18
 */
@Service
@RequiredArgsConstructor
public class AuthServiceImpl implements IAuthService {

    private final AuthMapper authMapper;

    private final IUserService userService;

    private final IChatMessageService messageHistoryService;

    private final ILoginLogService loginLogService;

    private final IOperateLogService operateLogService;

    @Resource
    private UserMapper userMapper;


    @Override
    public LoginUserInfoVO getLoginUserInfo() {
        LoginUserInfoVO userInfoDetailVO = new LoginUserInfoVO();
        userInfoDetailVO.setUnreadMessageCount(
                messageHistoryService.countUnreadChatMessage(null, StpUtil.getLoginIdAsInt()));
        BeanUtils.copyProperties(userService.getUserInfoById(StpUtil.getLoginIdAsInt()),
                userInfoDetailVO);
        return userInfoDetailVO;
    }

    @Override
    public PageSerializable<LoginLogVO> pageUserLoginLog(LoginLogQuery loginLogQuery) {
        loginLogQuery.setUserId(StpUtil.getLoginIdAsInt());
        return loginLogService.pageLoginLog(loginLogQuery);
    }

    @Override
    public Object pageUserOperateLog(OperateLogQuery operateLogQuery) {
        operateLogQuery.setUserId(StpUtil.getLoginIdAsInt());
        return operateLogService.pageOperateLog(operateLogQuery);
    }

    @Override
    public SaTokenInfo login(LoginParam loginParam) {
        User user = userMapper.getUserInfoByAccount(loginParam.getUsername());
        //UserVO userVO = authMapper.selectUserIdByUsernameAndPassword(loginParam);
        if (user == null) {
            throw new KeenException(MsgConsts.LOGIN_ERROR_MSG);
        }
        if (user.getStatus() == StatusType.LOCK) {
            throw new KeenException(MsgConsts.USER_LOCK_MSG);
        }
        if (!BCrypt.checkpw(loginParam.getPassword(), user.getPassword()) && !Objects.equals(loginParam.getPassword(), user.getPassword())) {
            throw new KeenException(ResultCode.FAIL.getCode(), "密码输入错误，请重新输入！");
        }
        String cacheCaptchaCode = RedisUtil.StringOps.get(Consts.CAPTCHA_CACHE_PREFIX + loginParam.getCaptchaSign());
        if (cacheCaptchaCode == null || !cacheCaptchaCode.equalsIgnoreCase(loginParam.getCaptchaCode())) {
            throw new KeenException(ResultCode.FAIL.getCode(), "验证码错误！");
        } else {
            RedisUtil.KeyOps.delete(Consts.CAPTCHA_CACHE_PREFIX + loginParam.getCaptchaSign());
        }

        StpUtil.login(user.getId(), loginParam.isRememberMe());
        loginLogService.addLoginLog(user.getNickname());
        return StpUtil.getTokenInfo();
    }

    @Override
    public void logout(String token) {
        StpUtil.logoutByTokenValue(token);
    }

    @Override
    public void updateUsername(AuthQuery authQuery) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        if (!Objects.equals(authQuery.getPassword(), user.getPassword())) {
            throw new KeenException(MsgConsts.PASSWORD_ERROR_MSG);
        }
        user.setUsername(authQuery.getUsername());
        userService.updateById(user);
    }

    @Override
    public void updatePassword(AuthQuery authQuery) {
        User user = userService.getById(StpUtil.getLoginIdAsInt());
        if (!Objects.equals(authQuery.getPassword(), user.getPassword())) {
            throw new KeenException(MsgConsts.PASSWORD_ERROR_MSG);
        }
        user.setPassword(authQuery.getNewPassword());
        userService.updateById(user);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void register(RegisterParam data) {
        if (StringUtils.isEmpty(data.getUsername()) || StringUtils.isEmpty(data.getPassword())) {
            throw new KeenException(ResultCode.FAIL.getCode(), "请检查注册参数无误");
        }
        int userNum = authMapper.countByUsername(data.getUsername());
        if (userNum > 0) {
            throw new KeenException(ResultCode.FAIL.getCode(), "注册名称已存在");
        }

        String salt = BCrypt.gensalt();
        String encryptPassword = BCrypt.hashpw(data.getPassword(), salt);
        UserQuery userQuery = new UserQuery();
        userQuery.setNickname(AuthUtil.generateRandomNickname());
        userQuery.setUsername(data.getUsername());
        userQuery.setPassword(encryptPassword);
        userQuery.setEmail(data.getEmail());
        userQuery.setStatus(StatusType.VALID);
        userService.addOrUpdateUser(userQuery);
    }
}
