package com.caychen.chatai.prompt;


import com.caychen.chatai.prompt.assistant.SeparateChatAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 14:50
 * @Description:
 */
@SpringBootTest
public class ChatPromptTest {

    @Autowired
    private SeparateChatAssistant separateChatAssistant;

    @Test
    public void testChatMemory() {
        String answer1 = separateChatAssistant.chat(3, "我是谁");
        System.out.println(answer1);

        String answer2 = separateChatAssistant.chat(3, "我是caychen");
        System.out.println(answer2);

        String answer3 = separateChatAssistant.chat(3, "你再猜一下，我是谁？");
        System.out.println(answer3);
    }

    @Test
    public void testChatMemoryWithDate2() {
        String answer1 = separateChatAssistant.chat2(4, "今天是几号啊");
        System.out.println(answer1);

        String answer2 = separateChatAssistant.chat2(4, "今天天气怎么样");
        System.out.println(answer2);

        String answer3 = separateChatAssistant.chat2(4, "有什么地方可以去玩吗？");
        System.out.println(answer3);

    }

    @Test
    public void testChatMemoryByTemplate3() {
        String answer1 = separateChatAssistant.chat3(3, "我想咨询今天去北京的车次");
        System.out.println(answer1);

    }

    @Test
    public void testUserInfo() {
        String username = "张三丰";
        int age = 99;
        String answer1 = separateChatAssistant.chat4(10, "我是谁，我多大了？", username, age);
        System.out.println(answer1);

    }
}
