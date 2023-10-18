package com.simple.keen.auth.model.dto;

import lombok.Data;
import java.io.Serializable;

@Data
public class CaptchaDTO implements Serializable {
    // 验证码标识
    private String captchaSign;
    // 验证码图片
    private String captchaCode;

    public CaptchaDTO(String captchaSign, String captchaCode) {
        this.captchaSign = captchaSign;
        this.captchaCode = captchaCode;
    }
}
