package com.caychen.chatai.agent.assistant;


import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 16:36
 * @Description:
 */
@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        // 使用的模型，该名称必须要在上下文中有对应的bean
        // 如果项目中有多个模型，则需要指定
        chatModel = "qwenChatModel",
        chatMemoryProvider = "chatMemoryProvider"
)
public interface AgentAssistant {

    @SystemMessage(fromResource = "agent-prompt-template.txt")
    String chat(@MemoryId String memoryId, @UserMessage String question);
}
