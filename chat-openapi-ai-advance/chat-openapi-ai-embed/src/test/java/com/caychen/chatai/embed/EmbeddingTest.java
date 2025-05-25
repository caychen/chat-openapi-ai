package com.caychen.chatai.embed;


import dev.langchain4j.data.embedding.Embedding;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.model.output.Response;
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

    @Test
    public void testEmbeddingModel() {
        Response<Embedding> embedded = embeddingModel.embed("你好");

        System.out.println("向量维度：" + embedded.content().vector().length);
        System.out.println("向量输出:" + embedded.toString());
    }
}
