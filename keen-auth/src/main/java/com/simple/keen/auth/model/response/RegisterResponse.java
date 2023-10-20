package com.simple.keen.auth.model.response;

import com.simple.keen.auth.model.dto.UserInfoDTO;
import com.simple.keen.common.base.dto.Response;

public class RegisterResponse extends Response<UserInfoDTO> {

    public RegisterResponse(Integer code, String message, UserInfoDTO data) {
        super(code, message, data);
    }
}
