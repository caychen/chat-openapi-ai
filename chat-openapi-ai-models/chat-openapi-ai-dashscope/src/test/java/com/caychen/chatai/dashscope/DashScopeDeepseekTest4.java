package com.caychen.chatai.dashscope;


import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 10:55
 * @Description: 阿里百炼集成deepseek
 */
@SpringBootTest
public class DashScopeDeepseekTest4 {

    @Autowired
    private OpenAiChatModel model;

    @Test
    public void testDeepseek() {
        String answer = model.chat("你好，我叫caychen，你是谁");
        System.out.println(answer);
    }
}
