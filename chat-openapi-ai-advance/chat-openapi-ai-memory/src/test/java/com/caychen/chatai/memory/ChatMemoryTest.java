package com.caychen.chatai.memory;


import com.caychen.chatai.memory.assistant.QwenAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:33
 * @Description:
 */
@SpringBootTest
public class ChatMemoryTest {

    @Autowired
    private QwenAssistant qwenAssistant;

    @Test
    public void testChatMemory() {
        String response = qwenAssistant.chat("你好,我叫caychen");
        System.out.println(response);

        // 上一个的问题，模型不会自动关联
        String answer = qwenAssistant.chat("我是谁？");
        System.out.println(answer);
    }
}
