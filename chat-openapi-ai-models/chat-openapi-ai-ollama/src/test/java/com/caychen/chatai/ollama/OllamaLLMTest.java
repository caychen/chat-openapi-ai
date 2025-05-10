package com.caychen.chatai.ollama;


import dev.langchain4j.model.ollama.OllamaChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 9:55
 * @Description:
 */
@SpringBootTest
public class OllamaLLMTest {

    /**
     * 原始通过apikey的方式，获取OpenAiChatModel对象，即未引入springboot相关langchain4j的starter的配置
     */
    @Test
    public void testLLM() {
        OllamaChatModel model = OllamaChatModel.builder()
                .baseUrl("http://localhost:11434")
                .modelName("llama3.1")
                .build();

        String answer = model.chat("你好，我叫caychen");
        System.out.println(answer);
    }
}
