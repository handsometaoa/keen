package com.simple.keen.auth.controller;

import cn.hutool.core.util.RandomUtil;
import com.simple.keen.auth.model.dto.CaptchaDTO;
import com.simple.keen.auth.model.request.AuthQuery;
import com.simple.keen.auth.model.request.LoginRequest;
import com.simple.keen.auth.model.request.RegisterRequest;
import com.simple.keen.auth.model.response.CaptchaResponse;
import com.simple.keen.auth.service.IAuthService;
import com.simple.keen.auth.utils.VerifyCodeUtils;
import com.simple.keen.common.base.Response;
import com.simple.keen.common.consts.Consts;
import com.simple.keen.common.consts.ResultCode;
import com.simple.keen.common.exception.KeenException;
import com.simple.keen.common.utils.RedisUtil;
import com.simple.keen.common.utils.StringUtils;
import com.simple.keen.monitor.model.query.LoginLogQuery;
import com.simple.keen.monitor.model.query.OperateLogQuery;
import lombok.RequiredArgsConstructor;
import org.springframework.util.Base64Utils;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/1/18
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/auth")
public class AuthController {


    private final IAuthService authService;

    @GetMapping("userInfo")
    public Response getUserInfo() {
        return Response.ok(authService.getLoginUserInfo());
    }

    @GetMapping("/loginLog")
    public Response pageUserLoginLog(LoginLogQuery loginLogQuery) {
        return Response.ok(authService.pageUserLoginLog(loginLogQuery));
    }

    @GetMapping("/operateLog")
    public Response pageUserOperateLog(OperateLogQuery operateLogQuery) {
        return Response.ok(authService.pageUserOperateLog(operateLogQuery));
    }

    @PostMapping("/login")
    public Response login(@RequestBody LoginRequest request) {
        if (request.getData() == null
                || StringUtils.isBlank(request.getData().getUsername())
                || StringUtils.isBlank(request.getData().getPassword())) {
            throw new KeenException(ResultCode.FAIL.getCode(), "请检查账号密码输入是否正确!");
        }
        if (StringUtils.isBlank(request.getData().getCaptchaCode()) || StringUtils.isBlank(request.getData().getCaptchaSign())) {
            throw new KeenException(ResultCode.FAIL.getCode(), "请输入验证码!");
        }
        return Response.ok(authService.login(request.getData()));
    }

    @PostMapping("logout")
    public Response logout(@RequestBody AuthQuery query) {
        authService.logout(query.getTokenValue());
        return Response.ok();
    }

    @PostMapping("register")
    public Response register(@RequestBody RegisterRequest registerRequest) {
        if (registerRequest == null || registerRequest.getData() == null) {
            throw new KeenException(ResultCode.FAIL.getCode(), "缺少参数,请检查！");
        }
        authService.register(registerRequest.getData());
        return Response.ok();
    }

    @PutMapping("username")
    public Response updateUsername(@RequestBody AuthQuery query) {
        authService.updateUsername(query);
        return Response.ok();
    }

    @PutMapping("password")
    public Response updatePassword(@RequestBody AuthQuery query) {
        authService.updatePassword(query);
        return Response.ok();
    }


    /**
     * 生成验证码图片
     */
    @GetMapping("/getCaptcha")
    public CaptchaResponse getCaptcha() throws IOException {
        // 1.使用工具类生成验证码
        String code = VerifyCodeUtils.generateVerifyCode(4);
        // 2.将 sgin、code 存进reids 并设置过期时间。
        String sign = RandomUtil.randomString(10);
        // 将sign、code 存进redis
        RedisUtil.StringOps.setEx(Consts.CAPTCHA_CACHE_PREFIX + sign, code, 60, TimeUnit.SECONDS);
        // 3.将图片转为字节数组输出流
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        VerifyCodeUtils.outputImage(220, 60, byteArrayOutputStream, code);
        String encryptCode = Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        CaptchaDTO captchaDTO = new CaptchaDTO(sign, encryptCode);

        // 4.将字节数组输出流编码为base64返回
        //return "data:image/png;base64," + Base64Utils.encodeToString(byteArrayOutputStream.toByteArray());
        return new CaptchaResponse(ResultCode.OK.getCode(), ResultCode.OK.getMessage(), captchaDTO);
    }

}
