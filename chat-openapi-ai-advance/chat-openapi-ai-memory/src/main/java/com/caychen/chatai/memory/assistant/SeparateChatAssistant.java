package com.caychen.chatai.memory.assistant;


/**
 * @Author: Caychen
 * @Date: 2025/4/24 10:34
 * @Description:
 */

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        // 使用的模型，该名称必须要在上下文中有对应的bean
        // 如果项目中有多个模型，则需要指定
        chatModel = "qwenChatModel",
        // 带有聊天记忆的智能体
//        chatMemory = "chatMemory",
        // 通过provider 指定聊天记录的存储方式
        chatMemoryProvider = "chatMemoryProvider"
)
public interface SeparateChatAssistant {

    /**
     * 分离聊天记录
     *
     * @param memoryId ： 聊天id
     * @param message  ： 用户消息
     * @return
     */
    String chat(@MemoryId int memoryId, @UserMessage String message);
}
