package com.caychen.chatai.prompt.config;


import com.caychen.chatai.prompt.store.MongoChatMemoryStore;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:49
 * @Description:
 */
@Configuration
public class SeparateChatConfig {

    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        // 设置聊天记录的存储方式，携带memoryId，并使用消息窗口存储，即只存储最近的10条消息
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                // 保存最近的10条消息
                .maxMessages(10)
                //  使用内存存储
//                .chatMemoryStore(new InMemoryChatMemoryStore())

                // 默认为SingleSlotChatMemoryStore，但是SingleSlotChatMemoryStore标记了@Internal，不能直接被使用或者new
//                .chatMemoryStore(new SingleSlotChatMemoryStore())

                // 自定义存储
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }
}
