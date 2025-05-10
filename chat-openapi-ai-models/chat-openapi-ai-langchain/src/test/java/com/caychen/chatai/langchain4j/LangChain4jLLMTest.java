package com.caychen.chatai.langchain4j;


import dev.langchain4j.model.openai.OpenAiChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 9:55
 * @Description:
 */
@SpringBootTest
public class LangChain4jLLMTest {

    /**
     * 原始通过apikey的方式，获取OpenAiChatModel对象，即未引入springboot相关langchain4j的starter的配置
     */
    @Test
    public void testLLM() {
        OpenAiChatModel model = OpenAiChatModel.builder()
                .baseUrl("http://langchain4j.dev/demo/openai/v1")
                .apiKey("demo")
                .modelName("gpt-4o-mini")
                .build();

        String answer = model.chat("你好，我叫caychen");
        System.out.println(answer);
    }
}
