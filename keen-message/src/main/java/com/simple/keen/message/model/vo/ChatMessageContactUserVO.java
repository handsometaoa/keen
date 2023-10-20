package com.simple.keen.message.model.vo;

import com.simple.keen.system.model.vo.UserVO;
import lombok.Data;
import lombok.ToString;

import java.util.List;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/3/19
 */
@Data
@ToString
public class ChatMessageContactUserVO {

    private UserVO user;

    private List<ChatMessageVO> messageList;

    private long unreadMessageCount;

    private boolean hidden;

}
