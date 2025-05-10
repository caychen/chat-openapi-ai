package com.caychen.chatai.aiservice;


import com.caychen.chatai.aiservice.assistant.QwenAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 11:28
 * @Description:
 */
@SpringBootTest
public class AiServiceTest2 {

    @Autowired
    private QwenAssistant qwenAssistant;

    @Test
    public void testAiService() {
        String response = qwenAssistant.chat("我是谁");
        System.out.println(response);
    }
}
