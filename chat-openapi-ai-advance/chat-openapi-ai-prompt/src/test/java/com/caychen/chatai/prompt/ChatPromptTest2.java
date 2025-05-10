package com.caychen.chatai.prompt;


import com.caychen.chatai.prompt.assistant.MemoryAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 14:50
 * @Description:
 */
@SpringBootTest
public class ChatPromptTest2 {

    @Autowired
    private MemoryAssistant memoryAssistant;

    @Test
    public void testChatMemoryWithUserMessage() {
        String answer1 = memoryAssistant.chat2("what is your name?");
        System.out.println(answer1);

        String answer2 = memoryAssistant.chat2("today，i am eighteen years old。");
        System.out.println(answer2);

        String answer3 = memoryAssistant.chat2("how about you？");
        System.out.println(answer3);
    }

    @Test
    public void testChatMemoryWithSystemMessage() {
        String answer1 = memoryAssistant.chat3("what is your name?");
        System.out.println(answer1);

        String answer2 = memoryAssistant.chat3("today，i am eighteen years old。");
        System.out.println(answer2);

        String answer3 = memoryAssistant.chat3("how about you？");
        System.out.println(answer3);
    }

    @Test
    public void testChatMemoryWithSystemMessageAndV() {
        String answer1 = memoryAssistant.chat4("what is your name?");
        System.out.println(answer1);

        String answer2 = memoryAssistant.chat4("today，i am eighteen years old。");
        System.out.println(answer2);

        String answer3 = memoryAssistant.chat4("how about you？");
        System.out.println(answer3);
    }
}
