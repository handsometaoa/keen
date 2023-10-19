package com.simple.keen.auth.model.param;

import lombok.Data;

import java.io.Serializable;


@Data
public class LoginParam implements Serializable {

    // 账号
    private String username;
    // 密码
    private String password;

    // 验证码sign
    private String captchaSign;
    // 验证码code
    private String captchaCode;

    // 记住密码
    private boolean rememberMe;
}
