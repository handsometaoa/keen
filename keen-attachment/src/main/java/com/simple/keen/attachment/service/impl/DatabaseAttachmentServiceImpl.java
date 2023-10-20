package com.simple.keen.attachment.service.impl;

import cn.hutool.core.codec.Base64Encoder;
import com.simple.keen.attachment.model.enums.AttachmentUploadPlatformType;
import com.simple.keen.attachment.model.vo.AttachmentStorageVO;
import com.simple.keen.attachment.model.vo.AttachmentUploadVO;
import com.simple.keen.common.consts.MsgConsts;
import com.simple.keen.common.exception.KeenException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/6/1
 */
@Slf4j
@Service
@RequiredArgsConstructor
@ConditionalOnMissingBean(AliyunOSSAttachmentServiceImpl.class)
public class DatabaseAttachmentServiceImpl extends AbstractAttachmentServiceImpl {

    private static final String BASE64_IMAGE_PREFIX = "data:image/png;base64,";

    @Override
    public AttachmentStorageVO addAttachmentStorage(MultipartFile file) {
        return AttachmentStorageVO.builder()
                .uploadUrl(attachmentStorageService.addAttachmentStorage(file).toString())
                .uploadPlatformType(AttachmentUploadPlatformType.DATABASE)
                .build();
    }

    @Override
    public String getImageUrl(MultipartFile file, AttachmentUploadVO attachmentUploadVO) {
        try {
            return BASE64_IMAGE_PREFIX + Base64Encoder.encode(file.getBytes());
        } catch (IOException e) {
            throw new KeenException(MsgConsts.IMAGE_UPLOAD_ERROR_MSG);
        }
    }

}
