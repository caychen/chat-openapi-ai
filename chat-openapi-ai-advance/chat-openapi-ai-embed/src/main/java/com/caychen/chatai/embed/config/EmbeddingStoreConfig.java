package com.caychen.chatai.embed.config;


import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeEmbeddingStore;
import dev.langchain4j.store.embedding.pinecone.PineconeServerlessIndexConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @Author: Caychen
 * @Date: 2025/5/25 17:15
 * @Description:
 */
@Configuration
public class EmbeddingStoreConfig {

    @Autowired
    private EmbeddingModel embeddingModel;

    @Bean
    public EmbeddingStore<TextSegment> embeddingStore() {
        // 初始化向量存储
        return PineconeEmbeddingStore.builder()
                .apiKey(System.getenv("PINECONE_API_KEY"))
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
}
