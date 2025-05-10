package com.caychen.chatai.deepseek;


import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 9:55
 * @Description:
 */
@SpringBootTest
public class DeepseekLLMTest2 {

    /**
     * 引入langchain4j-open-ai-spring-boot-starter之后，可以通过依赖注入的方式，获取OpenAiChatModel实例
     * <p>
     * 同时需要在配置文件中配置
     * langchain4j.open-ai.chat-model.*
     *
     * @see dev.langchain4j.openai.spring.Properties
     */
    @Autowired
    private OpenAiChatModel model;

    @Test
    public void testLLM() {
        String answer = model.chat("你好，我叫caychen");
        System.out.println(answer);
    }
}
