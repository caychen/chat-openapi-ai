package com.caychen.chatai.memory;


import com.caychen.chatai.memory.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:33
 * @Description:
 */
@SpringBootTest
public class ChatMemorySeparateTest5 {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testChatMemory() {
        // 第一个用户，指定memoryid=1
        String answer1 = separateChatAssistant.chat(1, "你好，我是caychen");
        System.out.println(answer1);
        String answer2 = separateChatAssistant.chat(1, "我是谁？");
        System.out.println(answer2);

        // 另一身份的用户，指定memoryid=2
        String answer3 = separateChatAssistant.chat(2, "我是谁？");
        System.out.println(answer3);
    }
}
