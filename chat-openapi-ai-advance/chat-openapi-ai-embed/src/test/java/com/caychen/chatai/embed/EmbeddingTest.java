package com.caychen.chatai.embed;


import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
import dev.langchain4j.store.embedding.EmbeddingMatch;
import dev.langchain4j.store.embedding.EmbeddingSearchRequest;
import dev.langchain4j.store.embedding.EmbeddingSearchResult;
import dev.langchain4j.store.embedding.EmbeddingStore;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/25 16:50
 * @Description:
 */
@SpringBootTest
public class EmbeddingTest {

    // 基于内存的向量模型
    @Autowired
    private EmbeddingModel embeddingModel;

    @Autowired
    private EmbeddingStore embeddingStore;

    @Test
    public void testEmbeddingModel() {
        Response<Embedding> embedded = embeddingModel.embed("你好");

        System.out.println("向量维度：" + embedded.content().vector().length);
        System.out.println("向量输出:" + embedded.toString());
    }

    @Test
    public void testEmbeddingModel2() {
        TextSegment frommed = TextSegment.from("我喜欢游泳");
        Embedding embedding = embeddingModel.embed(frommed).content();

        embeddingStore.add(embedding, frommed);

        TextSegment frommed1 = TextSegment.from("今天天气真好");
        Embedding embedding1 = embeddingModel.embed(frommed1).content();
        embeddingStore.add(embedding1, frommed1);
    }

    @Test
    public void testEmbeddingSearch() {
        // 将问题转换成向量数据
        Embedding queryEmbedding = embeddingModel.embed("你最喜欢的运动是什么").content();

        // 创建搜索请求对象
        EmbeddingSearchRequest searchRequest = EmbeddingSearchRequest.builder()
                .queryEmbedding(queryEmbedding)
                .maxResults(1)//匹配最相似的一条记录
                .minScore(0.8)// 最低分
                .build();

        // 根据请求在向量存储中进行相似度匹配
        EmbeddingSearchResult<TextSegment> searchResult = embeddingStore.search(searchRequest);

        // 获取匹配项列表中第一个匹配值
        EmbeddingMatch<TextSegment> embeddingMatch = searchResult.matches().get(0);

        System.out.println("最相似的记录：" + embeddingMatch.embedded().text());
        System.out.println("最相似的记录的得分：" + embeddingMatch.score());
    }
}
