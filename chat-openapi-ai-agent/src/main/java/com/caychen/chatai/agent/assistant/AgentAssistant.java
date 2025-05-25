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
        // 配置会话历史记录存储器
        chatMemoryProvider = "chatMemoryProvider",
        // 配置工具方法
        tools = "appointmentTools",
        // 配置向量数据库
//        contentRetriever = "contentRetriever"

        // 配置向量数据库，基于pinecone
        contentRetriever = "contentRetrieverWithPinecone"
)
public interface AgentAssistant {

    // 配置引入系统提示模板文件
    @SystemMessage(fromResource = "agent-prompt-template.txt")
    String chat(@MemoryId String memoryId, @UserMessage String message);
}
