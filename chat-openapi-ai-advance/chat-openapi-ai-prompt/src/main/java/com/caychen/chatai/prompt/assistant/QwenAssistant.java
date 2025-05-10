package com.caychen.chatai.prompt.assistant;


/**
 * @Author: Caychen
 * @Date: 2025/4/24 10:34
 * @Description:
 */

import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        // 使用的模型，该名称必须要在上下文中有对应的bean
        // 如果项目中有多个模型，则需要指定
        chatModel = "qwenChatModel"
)
public interface QwenAssistant {

    String chat(String message);
}
