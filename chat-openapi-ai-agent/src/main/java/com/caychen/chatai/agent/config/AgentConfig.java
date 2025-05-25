package com.caychen.chatai.agent.config;


import com.caychen.chatai.agent.store.MongoChatMemoryStore;
import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:49
 * @Description:
 */
//@Configuration
public class AgentConfig {

    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    @Bean
    public ChatMemoryProvider chatMemoryProvider() {
        // 设置聊天记录的存储方式，携带memoryId，并使用消息窗口存储，即只存储最近的10条消息
        return memoryId -> MessageWindowChatMemory.builder()
                .id(memoryId)
                .maxMessages(20)
                //  使用内存存储
//                .chatMemoryStore(new InMemoryChatMemoryStore())

                // 默认为SingleSlotChatMemoryStore，但是SingleSlotChatMemoryStore标记了@Internal，不能直接被使用或者new
//                .chatMemoryStore(new SingleSlotChatMemoryStore())

                // 自定义存储
                .chatMemoryStore(mongoChatMemoryStore)
                .build();
    }

    /**
     * 使用基于内存的向量数据库
     *
     * @return
     */
    @Bean
    public ContentRetriever contentRetriever() {
        // 创建一个内容检索器，用于从数据库中检索内容
        Document document1 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\公司简介.txt");
        Document document2 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\发展历程.txt");
        Document document3 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\公司业务.txt");
        Document document4 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\领导信息.md");
        Document document5 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\召回事件.txt");
        List<Document> documents = Arrays.asList(document1, document2, document3, document4, document5);

        // 使用内粗向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();
        // 使用默认的文档分割器
        EmbeddingStoreIngestor.ingest(documents, embeddingStore);
        //从嵌入存储重检索和查询内容相关的信息
        return EmbeddingStoreContentRetriever.from(embeddingStore);
    }
}
