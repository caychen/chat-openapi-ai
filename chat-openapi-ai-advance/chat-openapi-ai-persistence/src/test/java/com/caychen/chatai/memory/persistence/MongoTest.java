package com.caychen.chatai.memory.persistence;


import com.caychen.chatai.memory.persistence.bean.CustomChatMessage;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.data.mongodb.core.MongoTemplate;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 14:02
 * @Description:
 */
@SpringBootTest
public class MongoTest {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Test
    public void testMongo() {
//        CustomChatMessage chatMessage = new CustomChatMessage("123", "聊天记录");
//        mongoTemplate.insert(chatMessage);
    }

    @Test
    public void testMongo2() {
        CustomChatMessage chatMessage = new CustomChatMessage();
        chatMessage.setContent("聊天记录2");
        mongoTemplate.insert(chatMessage);
    }

    @Test
    public void testMongoFind() {
        CustomChatMessage chatMessage = mongoTemplate.findById("681eef754f0d6411fe880c50", CustomChatMessage.class);
        System.out.println(chatMessage);
    }

}
