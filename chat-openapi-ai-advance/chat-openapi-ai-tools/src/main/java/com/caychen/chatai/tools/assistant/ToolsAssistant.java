package com.caychen.chatai.tools.assistant;


import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.spring.AiService;

/**
 * @Author: Caychen
 * @Date: 2025/4/24 10:34
 * @Description:
 */
@AiService(
        tools = "calculateTools",
//        chatMemory = "chatMemory"
        chatMemoryProvider = "chatMemoryProvider"
)
public interface ToolsAssistant {

    String chat(String message);

    /**
     * 使用chat2方法，传递了memoryId，在通过@Tool注解标注的方法中，如果使用了@ToolMemoryId的参数，则可以在对应的Tool方法中自动获取memoryId
     *
     * @param memoryId
     * @param message
     * @return
     */
    String chat2(@MemoryId String memoryId, @UserMessage String message);
}
