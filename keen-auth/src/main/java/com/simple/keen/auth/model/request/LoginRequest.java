package com.simple.keen.auth.model.request;

import com.simple.keen.auth.model.param.LoginParam;
import com.simple.keen.common.base.dto.Request;
import lombok.Getter;


@Getter
public class LoginRequest extends Request<LoginParam> {

    public LoginRequest() {
    }

    public LoginRequest(LoginParam data) {
        super(data);
    }
}
