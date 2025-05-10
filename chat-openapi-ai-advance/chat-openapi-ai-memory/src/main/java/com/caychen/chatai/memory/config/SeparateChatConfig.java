package com.caychen.chatai.memory.config;


import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.store.memory.chat.InMemoryChatMemoryStore;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:49
 * @Description:
 */
@Configuration
public class SeparateChatConfig {

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        // 设置聊天记录的存储方式，携带memoryId，并使用消息窗口存储，即只存储最近的10条消息
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(10)
                //  使用内存存储
                .chatMemoryStore(new InMemoryChatMemoryStore())
                // 默认为SingleSlotChatMemoryStore，但是SingleSlotChatMemoryStore标记了@Internal，不能直接被使用或者new
//                .chatMemoryStore(new SingleSlotChatMemoryStore())
                .build();
    }
}
