package com.caychen.chatai.prompt.store;


import com.caychen.chatai.prompt.bean.CustomChatMessage;
import dev.langchain4j.data.message.ChatMessage;
import dev.langchain4j.data.message.ChatMessageDeserializer;
import dev.langchain4j.data.message.ChatMessageSerializer;
import dev.langchain4j.store.memory.chat.ChatMemoryStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.data.mongodb.core.query.Update;
import org.springframework.stereotype.Component;

import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 14:23
 * @Description:
 */
@Component
public class MongoChatMemoryStore implements ChatMemoryStore {

    @Autowired
    private MongoTemplate mongoTemplate;

    @Override
    public List<ChatMessage> getMessages(Object memoryId) {
        CustomChatMessage customChatMessage = mongoTemplate.findOne(Query.query(Criteria.where("memoryId").is(memoryId)), CustomChatMessage.class);
        if (customChatMessage == null) {
            return List.of();
        }
        String content = customChatMessage.getContent();
        return ChatMessageDeserializer.messagesFromJson(content);
    }

    @Override
    public void updateMessages(Object memoryId, List<ChatMessage> list) {
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = Query.query(criteria);
        String content = ChatMessageSerializer.messagesToJson(list);
        Update update = Update.update("content", content);
        mongoTemplate.upsert(query, update, CustomChatMessage.class);
    }

    @Override
    public void deleteMessages(Object memoryId) {
        Criteria criteria = Criteria.where("memoryId").is(memoryId);
        Query query = Query.query(criteria);
        mongoTemplate.remove(query, CustomChatMessage.class);
    }
}
