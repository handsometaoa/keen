package com.simple.keen.auth.model.param;

import lombok.Data;

import java.io.Serializable;

@Data
public class RegisterParam implements Serializable {

    /**
     * 账号
     */
    private String username;

    /**
     * 密码
     */
    private String password;

    /**
     * 邮箱
     */
    private String email;

}
