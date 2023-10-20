package com.simple.keen.server.message.handler;

import com.simple.keen.message.model.enums.MessageType;
import com.simple.keen.server.message.MessageHandler;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * .
 *
 * @author SinceNovember
 * @date 2023/3/15
 */
@Component
public class MessageHandlerFactory {

    @Resource
    private Map<String, MessageHandler> context = new ConcurrentHashMap<>();

    public MessageHandler getHandler(MessageType messageType) {
        return context.get(messageType.name());
    }

}
