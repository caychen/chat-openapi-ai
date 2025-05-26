package com.caychen.chatai.agent.controller;


import com.caychen.chatai.agent.assistant.AgentAssistant;
import com.caychen.chatai.agent.assistant.AgentAssistantStream;
import com.caychen.chatai.agent.bean.ChatForm;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

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

    @Autowired
    private AgentAssistantStream agentAssistantStream;

    @Operation(description = "对话")
    @PostMapping("/chat")
    public String chat(@RequestBody ChatForm chatForm) {
        return agentAssistant.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }

    @Operation(description = "对话V2")
    @PostMapping(value = "/chatV2", produces = "text/stream;charset=utf-8")
    public Flux<String> chatV2(@RequestBody ChatForm chatForm) {
        return agentAssistantStream.chat(chatForm.getMemoryId(), chatForm.getMessage());
    }
}
