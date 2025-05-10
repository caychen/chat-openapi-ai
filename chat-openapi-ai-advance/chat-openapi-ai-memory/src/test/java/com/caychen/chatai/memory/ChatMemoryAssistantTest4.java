package com.caychen.chatai.memory;


import com.caychen.chatai.memory.assistant.MemoryAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:33
 * @Description:
 */
@SpringBootTest
public class ChatMemoryAssistantTest4 {

    @Autowired
    private MemoryAssistant memoryAssistant;

    @Test
    public void testChatMemory() {
        String answer1 = memoryAssistant.chat("你好，我是caychen");
        System.out.println(answer1);
        String answer2 = memoryAssistant.chat("我是谁？");
        System.out.println(answer2);
    }
}
