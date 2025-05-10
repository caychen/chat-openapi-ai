package com.caychen.chatai.memory;


import dev.langchain4j.community.model.dashscope.QwenChatModel;
import dev.langchain4j.data.message.AiMessage;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.chat.response.ChatResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:33
 * @Description:
 */
@SpringBootTest
public class ChatMemoryTest2 {

    @Autowired
    private QwenChatModel qwenChatModel;

    @Test
    public void testChatMemory() {
        UserMessage userMessage1 = UserMessage.userMessage("你好,我叫caychen");

        ChatResponse chatResponse1 = qwenChatModel.chat(userMessage1);
        AiMessage aiMessage1 = chatResponse1.aiMessage();
        //输出大语言模型的回复
        System.out.println(aiMessage1.text());

        // 第二轮对话
        UserMessage userMessage2 = UserMessage.userMessage("我是谁？");
        // 通过将第一轮的对话作为第二轮对话的请求参数，让大预言模型去识别
        ChatResponse chatResponse2 = qwenChatModel.chat(Arrays.asList(userMessage1, aiMessage1, userMessage2));

        AiMessage aiMessage2 = chatResponse2.aiMessage();
        //输出大语言模型的回复
        System.out.println(aiMessage2.text());
    }
}
