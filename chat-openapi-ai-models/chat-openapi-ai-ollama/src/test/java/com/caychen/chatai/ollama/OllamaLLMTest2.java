package com.caychen.chatai.ollama;


import dev.langchain4j.model.ollama.OllamaChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 9:55
 * @Description:
 */
@SpringBootTest
public class OllamaLLMTest2 {

    /**
     * 引入langchain4j-ollama-spring-boot-starter之后，可以通过依赖注入的方式，获取OllamaChatModel实例
     * <p>
     * 同时需要在配置文件中配置
     * langchain4j.ollama.chat-model.*
     *
     * @see dev.langchain4j.ollama.spring.Properties
     */
    @Autowired
    private OllamaChatModel model;

    @Test
    public void testLLM() {
        String answer = model.chat("你好，我叫caychen");
        System.out.println(answer);
    }
}
