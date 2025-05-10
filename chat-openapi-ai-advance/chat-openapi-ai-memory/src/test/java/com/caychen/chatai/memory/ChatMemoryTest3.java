package com.caychen.chatai.memory;


import com.caychen.chatai.memory.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:33
 * @Description:
 */
@SpringBootTest
public class ChatMemoryTest3 {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChatMemory() {
        // 带有窗口记忆的对话
        MessageWindowChatMemory chatMemory = MessageWindowChatMemory.withMaxMessages(10);

        Assistant assistant = AiServices.builder(Assistant.class)
                .chatModel(qwenChatModel)
                .chatMemory(chatMemory)
                .build();

        String answer1 = assistant.chat("你好，我是caychen");
        System.out.println(answer1);
        String answer2 = assistant.chat("我是谁？");
        System.out.println(answer2);
    }
}
