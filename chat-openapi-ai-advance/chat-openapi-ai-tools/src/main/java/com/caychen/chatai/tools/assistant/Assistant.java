package com.caychen.chatai.tools.assistant;


import dev.langchain4j.service.spring.AiService;

/**
 * @Author: Caychen
 * @Date: 2025/4/24 10:34
 * @Description:
 */

@AiService
public interface Assistant {

    String chat(String message);
}
