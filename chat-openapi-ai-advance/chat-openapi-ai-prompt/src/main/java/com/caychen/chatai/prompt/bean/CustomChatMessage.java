package com.caychen.chatai.prompt.bean;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 14:01
 * @Description:
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Document("chat_message")
public class CustomChatMessage {

    @Id
    private String id;

    private String memoryId;

    private String content;


}
