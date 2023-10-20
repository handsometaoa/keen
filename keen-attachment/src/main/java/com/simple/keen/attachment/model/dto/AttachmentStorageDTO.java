package com.simple.keen.attachment.model.dto;

import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/5/10
 */
@Data
@ToString
public class AttachmentStorageDTO {

    /**
     * id
     */
    private Integer id;

    /**
     * 文件名
     */
    private String storageData;

    /**
     * 文件名
     */
    private String storageSize;

    /**
     * 附件标识
     */
    private Integer attachmentId;

    /**
     * 创建时间
     */
    private LocalDateTime createTime;

}
