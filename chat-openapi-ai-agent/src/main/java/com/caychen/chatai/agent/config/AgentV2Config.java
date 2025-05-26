package com.caychen.chatai.agent.config;


import com.caychen.chatai.agent.store.MongoChatMemoryStore;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.memory.chat.ChatMemoryProvider;
import dev.langchain4j.memory.chat.MessageWindowChatMemory;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.rag.content.retriever.ContentRetriever;
import dev.langchain4j.rag.content.retriever.EmbeddingStoreContentRetriever;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 12:49
 * @Description:
 */
@Configuration
public class AgentV2Config {

    @Autowired
    private MongoChatMemoryStore mongoChatMemoryStore;

    /**
     *
     * 阿里百炼大平台
     *
     * 引入以下配置之后
     * langchain4j.community.dashscope.embedding-model.api-key=${DASH_SCOPE_API_KEY:}
     * langchain4j.community.dashscope.embedding-model.model-name=text-embedding-v3
     *
     * 就会注入 QwenEmbeddingModel
     */
    @Autowired
    private EmbeddingModel embeddingModel;

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
     * 使用Pinecone作为向量数据库，默认为基于内存的InMemoryEmbeddingStore
     *
     * @return
     */
    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        // 初始化向量存储
        return PineconeEmbeddingStore.builder()
                .apiKey("pcsk_46mJ8M_PA3sg8ihZ7uTnGq3dPq5AEcd8p3pfoq7mzQca1QrGuCyik7WuQtjvbDyhGHnoL6")
                .index("my-index")// 指定Pinecone的索引名称，如果不存在，则会自动创建一个新的索引
                .nameSpace("my-namespace")//指定Pinecone的命名空间，如果命名空间不存在，则会自动创建一个新的命名空间
                .createIndex(PineconeServerlessIndexConfig.builder()
                        .cloud("AWS")// 指定索引部署在AWS云上
                        .region("us-east-1")// 指定索引所在的AWS区域为us-east-1
                        .dimension(embeddingModel.dimension())// 指定索引的向量维度，改维度与embeddingModel生成的向量维度相同
                        .build()
                )
                .build();
    }

    /**
     * 创建基于嵌入存储的内容检索器
     *
     * @return
     */
    @Bean
    public ContentRetriever contentRetrieverWithPinecone(@Autowired EmbeddingStore embeddingStore) {
        // 创建一个内容检索器，用于从Pinecone向量数据库中检索内容
        return EmbeddingStoreContentRetriever.builder()
                // 设置用于生成嵌入向量的嵌入模型
                .embeddingModel(embeddingModel)
                // 设置要使用的嵌入存储
                .embeddingStore(embeddingStore)
                // 设置最大减数结果数据
                .maxResults(1)
                // 设置最小得分阈值
                .minScore(0.8)
                // 构建最终的内容检索器
                .build();

    }
}
