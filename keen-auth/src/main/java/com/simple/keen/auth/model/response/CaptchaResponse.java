package com.simple.keen.auth.model.response;

import com.simple.keen.auth.model.dto.CaptchaDTO;
import com.simple.keen.common.base.dto.Response;
import lombok.Data;

@Data
public class CaptchaResponse extends Response<CaptchaDTO> {

    public CaptchaResponse(Integer code, String message) {
        super(code, message);
    }

    public CaptchaResponse(Integer code, String message, CaptchaDTO data) {
        super(code, message, data);
    }
}
