package com.caychen.chatai.dashscope;


import dev.langchain4j.community.model.dashscope.QwenChatModel;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 10:55
 * @Description:
 */
@SpringBootTest
public class DashScopeLLMTest {

    @Test
    public void testLLM() {
        QwenChatModel model = QwenChatModel.builder()
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .modelName("qwen-max")
                .build();

        String answer = model.chat("你好，你是谁");
        System.out.println(answer);
    }
}
