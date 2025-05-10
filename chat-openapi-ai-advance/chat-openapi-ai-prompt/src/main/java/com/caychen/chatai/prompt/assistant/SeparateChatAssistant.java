package com.caychen.chatai.prompt.assistant;


/**
 * @Author: Caychen
 * @Date: 2025/4/24 10:34
 * @Description:
 */

import dev.langchain4j.service.MemoryId;
import dev.langchain4j.service.SystemMessage;
import dev.langchain4j.service.UserMessage;
import dev.langchain4j.service.V;
import dev.langchain4j.service.spring.AiService;
import dev.langchain4j.service.spring.AiServiceWiringMode;

@AiService(wiringMode = AiServiceWiringMode.EXPLICIT,
        // 使用的模型，该名称必须要在上下文中有对应的bean
        // 如果项目中有多个模型，则需要指定
        chatModel = "qwenChatModel",
        // 带有聊天记忆的智能体
//        chatMemory = "chatMemory",
        // 通过provider 指定聊天记录的存储方式
        chatMemoryProvider = "chatMemoryProvider"
)
public interface SeparateChatAssistant {

    /**
     * 系统提示词：给AI设定角色
     * 其中@SystemMessage设定角色之后，它只会在第一轮对话开始之前添加一次，后续不会再设定，除非用户重新设置角色
     * 而@UserMessage则不同，每轮对话之前都会重新设定角色，所以可以在@UserMessage参数中使用{{it}}用于获取本轮的参数值
     *
     * @param memoryId
     * @param message
     * @return
     */
    @SystemMessage("你是我的好朋友，请使用上海话回答问题")
    /**
     *  分离聊天记录
     *
     * @param memoryId ： 聊天id
     * @param message ： 用户消息
     * @return
     */
    String chat(@MemoryId int memoryId, @UserMessage String message);


    /**
     * 系统提示词：给AI设定角色
     *
     * @param memoryId
     * @param message  {{current_date}}： 表示当前日期
     * @return
     */
    @SystemMessage("你是我的好朋友，请使用上海话回答问题，今天是{{current_date}}")
    String chat2(@MemoryId int memoryId, @UserMessage String message);

    @SystemMessage(fromResource = "my-prompt-template.txt")
    String chat3(@MemoryId int memoryId, @UserMessage String message);


    @SystemMessage(fromResource = "my-prompt-template2.txt")
    String chat4(@MemoryId int memoryId,
                 @UserMessage String message,
                 @V("username") String username,
                 @V("age") int age
    );
}
