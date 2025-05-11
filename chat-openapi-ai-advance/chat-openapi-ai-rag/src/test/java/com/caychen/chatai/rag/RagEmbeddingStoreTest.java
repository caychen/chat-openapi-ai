package com.caychen.chatai.rag;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.splitter.DocumentByParagraphSplitter;
import dev.langchain4j.data.segment.TextSegment;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenCountEstimator;
import dev.langchain4j.store.embedding.EmbeddingStoreIngestor;
import dev.langchain4j.store.embedding.inmemory.InMemoryEmbeddingStore;
import org.junit.jupiter.api.Test;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 20:30
 * @Description:
 *
 * DocumentByParagraphSplitter： 按段落文档分割器（默认）
 * DocumentByLineSplitter：按行文档分割器
 * DocumentBySentenceSplitter：按句子文档分割器
 * DocumentByWordSplitter：按单词文档分割器
 * DocumentByCharacterSplitter：按字符文档分割器
 * DocumentByRegexSplitter：按正则表达式文档分割器
 *  默认情况下每个文本片段最多不超过300个token
 *  假如按照段落分割完之后，段落内超过了300个token，则再将该段落按行进行分割，直到不超过300个token
 *  如果按行还是超过了300个token，则将该段落按单词进行分割，直到不超过300个token
 *  依次类推，直到所有文本片段都小于300个token
 *
 */
public class RagEmbeddingStoreTest {

    @Test
    public void testReadDocumentAndStore() {
        // 读取文件
        Document document = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\公司简介.txt");

        // 基于内存的向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        //1、分割文档，默认使用递归分割器，将文档分割成多个文本片段，每个片段包含不超过300个token，并且有30个token的重叠部分保证连贯性
        // DocumentByParagraphSplitter --> DocumentByLineSplitter --> DocumentBySentenceSplitter
        // --> DocumentByWordSplitter --> DocumentByCharacterSplitter

        //2、文本向量化，使用一个langchain4j内置的轻量化向量模型对每个文本片段进行向量化
        //3、将原始文本和向量存储到向量数据库中（InMemoryEmbeddingStore）
        EmbeddingStoreIngestor.ingest(document, embeddingStore);

        // 查看向量数据库内容
        System.out.println(embeddingStore);
    }

    @Test
    public void testReadDocumentSplitter() {
        // 读取文件
        Document document = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\公司简介.txt");

        // 基于内存的向量存储
        InMemoryEmbeddingStore<TextSegment> embeddingStore = new InMemoryEmbeddingStore<>();

        // 自定义文档分割器
        // 安装段落分割文档，每个片段包含不超过300个token，并且有30个token的重叠部分保证连贯性
        DocumentByParagraphSplitter documentByParagraphSplitter = new DocumentByParagraphSplitter(
                300,
                30,
                // token分词器，按token计算
                new HuggingFaceTokenCountEstimator());

        EmbeddingStoreIngestor.builder()
                .embeddingStore(embeddingStore)
                .documentSplitter(documentByParagraphSplitter)
                .build()
                .ingest(document);
    }
}
