package com.simple.keen.message.model.vo;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.simple.keen.message.model.enums.ChatMessageContentType;
import com.simple.keen.message.model.enums.ChatMessageReadType;
import lombok.Data;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/3/31
 */
@Data
@ToString
public class ChatMessagePageVO {

    private Integer id;

    private String fromName;

    private String toName;

    private ChatMessageContentType contentType;

    private String content;

    private ChatMessageReadType isRead;

    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    private LocalDateTime createTime;

}
