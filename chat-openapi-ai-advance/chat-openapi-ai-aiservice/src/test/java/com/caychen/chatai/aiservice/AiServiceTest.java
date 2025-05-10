package com.caychen.chatai.aiservice;


import com.caychen.chatai.aiservice.assistant.Assistant;
import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.service.AiServices;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 11:28
 * @Description:
 */
@SpringBootTest
public class AiServiceTest {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testAiService() {
        // 根据模型创建一个AI服务
        Assistant assistant = AiServices.create(Assistant.class, qwenChatModel);
        String response = assistant.chat("你好,你是谁");
        System.out.println(response);
    }
}
