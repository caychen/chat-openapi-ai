package com.caychen.chatai.tools;


import com.caychen.chatai.tools.assistant.Assistant;
import com.caychen.chatai.tools.assistant.ToolsAssistant;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 20:41
 * @Description:
 */
@SpringBootTest
public class ToolsTest {

    @Autowired
    private Assistant assistant;

    @Autowired
    private ToolsAssistant toolsAssistant;

    @Test
    public void testTools() {
        // 答案：5
        String answer1 = assistant.chat("帮忙算下3+2等于多少");
        System.out.println(answer1);

        // 答案：196144.7740
        String answer2 = assistant.chat("帮忙算下38472772372的平方根是多少");
        System.out.println(answer2);
    }

    @Test
    public void testTools2() {
        // 答案：5
        String answer1 = toolsAssistant.chat("帮忙算下3+2等于多少");
        System.out.println(answer1);

        // 答案：196144.7740
        String answer2 = toolsAssistant.chat("帮忙算下38472772372的平方根是多少");
        System.out.println(answer2);
    }

    @Test
    public void testTools3() {
        // 答案：6
        String answer1 = toolsAssistant.chat("帮忙算下3乘以2等于多少");
        System.out.println(answer1);

    }

    @Test
    public void testTools4() {
//        String answer1 = toolsAssistant.chat2("30", "你是谁？");
//        System.out.println(answer1);

        // 答案：1.5
        String answer2 = toolsAssistant.chat2("30", "帮忙算下3除以2等于多少");
        System.out.println(answer2);

    }
}
