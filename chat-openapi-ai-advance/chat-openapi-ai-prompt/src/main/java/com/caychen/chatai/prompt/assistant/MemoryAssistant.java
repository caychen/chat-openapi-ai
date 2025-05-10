package com.caychen.chatai.prompt.assistant;


/**
 * @Author: Caychen
 * @Date: 2025/4/24 10:34
 * @Description:
 */

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
        chatMemory = "chatMemory"
)
public interface MemoryAssistant {

    String chat(String message);

    /**
     * 在方法上添加@UserMessage注解，此形式只支持只有一个参数的情况
     * 其中{{it}}表示唯一的参数占位符
     * 使用@UserMessage 注解,会在每一轮对话开始的时候，给智能体设定角色/提示词
     *
     * @param message
     * @return
     */
    @UserMessage("You are my friend, please talk with me by english, {{it}}")
    String chat2(String message);

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
    String chat3(String message);

    /**
     * 使用@V注解，可以指定参数名，这样就可以在@UserMessage注解的提示词中使用@V注解的参数名
     * 同时，如果参数有两个及以上，则必须使用@V注解并指定参数名，才能在@UserMessage中使用占位符
     * 如果只有一个参数，可以不使用@V，简单在@UserMessage中使用{{it}}
     *
     * @param message
     * @return
     */
    @UserMessage("你是我的好朋友，请使用上海话回答问题, {{message}}")
    String chat4(@V("message") String message);
}
