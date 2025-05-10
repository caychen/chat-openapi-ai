package com.caychen.chatai.agent.controller;


import com.caychen.chatai.agent.assistant.AgentAssistant;
import com.caychen.chatai.agent.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 16:59
 * @Description:
 */
@Tag(name = "小智")
@RestController
@RequestMapping("/agent")
public class AgentController {

    @Autowired
    private AgentAssistant agentAssistant;

    @Operation(description = "对话")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatForm chatForm) {
        return agentAssistant.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
