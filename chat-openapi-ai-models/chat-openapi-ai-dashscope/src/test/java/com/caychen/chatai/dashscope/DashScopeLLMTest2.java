package com.caychen.chatai.dashscope;


import dev.langchain4j.community.model.dashscope.QwenChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 10:55
 * @Description:
 */
@SpringBootTest
public class DashScopeLLMTest2 {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testText() {
        String answer = qwenChatModel.chat("你好，我叫caychen");
        System.out.println(answer);
    }

}
