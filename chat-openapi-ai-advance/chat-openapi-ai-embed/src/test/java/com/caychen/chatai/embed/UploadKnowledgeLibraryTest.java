package com.caychen.chatai.embed;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.model.embedding.EmbeddingModel;
import dev.langchain4j.store.embedding.EmbeddingStore;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Arrays;
import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2025/5/25 20:38
 * @Description:
 */
@SpringBootTest
public class UploadKnowledgeLibraryTest {

    @Autowired
    private EmbeddingStore embeddingStore;

    @Autowired
    private EmbeddingModel embeddingModel;

    @Test
    public void testUploadKnowledgeLibrary() {

        // 加载本地文档
        Document document1 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\公司简介.txt");
        Document document2 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\发展历程.txt");
        Document document3 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\公司业务.txt");
        Document document4 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\领导信息.md");
        Document document5 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\召回事件.txt");

        List<Document> documents = Arrays.asList(document1, document2, document3, document4, document5);

        // 上传文档到向量数据库中
        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .embeddingModel(embeddingModel)
                .build()
                .ingest(documents);
    }
}
