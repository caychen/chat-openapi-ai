package com.caychen.chatai.rag;


import dev.langchain4j.data.document.Document;
import dev.langchain4j.data.document.loader.FileSystemDocumentLoader;
import dev.langchain4j.data.document.parser.TextDocumentParser;
import dev.langchain4j.data.document.parser.apache.pdfbox.ApachePdfBoxDocumentParser;
import org.junit.jupiter.api.Test;

import java.nio.file.FileSystems;
import java.nio.file.PathMatcher;
import java.util.List;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 16:42
 * @Description:
 */
//@SpringBootTest
public class RagDocumentParserTest {

    @Test
    public void testReadDocument1() {
//        URL url = getClass().getClassLoader().getResource("test.txt");
//        Document document = FileSystemDocumentLoader.loadDocument(url.getPath());

        // 使用FileSystemDocumentLoader读取指定目录下的知识库文档
        // 并使用默认的文档解析器TextDocumentParser对文档进行解析
        // 文档解析器:
        //  TextDocumentParser: 解析纯文本格式的文件，如txt，html，md等
        //  ApachePdfBoxDocumentParser: 解析PDF格式的文件，但需要引入langchain4j-document-parser-apache-pdfbox依赖
        //  ApachePoiDocumentParser: 解析办公软件的格式文件，如DOC,DOCX,PPT,PPTX,XLS,XLSX，但需要引入langchain4j-document-parser-apache-poi依赖
        //  ApacheTikaDocumentParser: 自动检测并解析几乎所有现有的文件格式，但需要引入langchain4j-document-parser-apache-tika依赖
        Document document = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\公司简介.txt");
        System.out.println(document.text());
    }

    /**
     * 读取单个文件
     */
    @Test
    public void testReadSingleDocument2() {
        Document document1 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\公司简介.txt", new TextDocumentParser());
        System.out.println("文档1：" + document1.text());

        // 乱码
        Document document2 = FileSystemDocumentLoader.loadDocument("C:\\Users\\EDY\\Desktop\\knowledges\\发展历程.doc", new TextDocumentParser());
        System.out.println("文档2：" + document2.text());

    }

    /**
     * 读取目录下的所有文件
     */
    @Test
    public void testReadDirectory() {
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("C:\\Users\\EDY\\Desktop\\knowledges", new TextDocumentParser());
        System.out.println("文档数：" + documents.size());
        documents.forEach(document -> {
            System.out.println("====================");
            System.out.println(document.text());
            System.out.println(document.metadata());
        });

    }

    /**
     * 根据匹配模式读取文件
     */
    @Test
    public void testReadPatternDocuments() {
        // 获取某个目录中所有格式为txt的文件
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.txt");
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("C:\\Users\\EDY\\Desktop\\knowledges", pathMatcher, new TextDocumentParser());
        System.out.println("匹配到文档数：" + documents.size());
        documents.forEach(document -> {
            System.out.println("====================");
            System.out.println(document.text());
//            System.out.println(document.metadata());
        });
    }

    /**
     * 读取pdf文件
     */
    @Test
    public void testReadPdfDocuments() {
        // 获取某个目录中所有格式为txt的文件
        PathMatcher pathMatcher = FileSystems.getDefault().getPathMatcher("glob:**.pdf");
        List<Document> documents = FileSystemDocumentLoader.loadDocuments("C:\\Users\\EDY\\Desktop\\knowledges", pathMatcher, new ApachePdfBoxDocumentParser());
        System.out.println("匹配到文档数：" + documents.size());
        documents.forEach(document -> {
            System.out.println("====================");
            System.out.println(document.text());
//            System.out.println(document.metadata());
        });
    }

    /**
     * 递归读取子目录下的所有文件
     */
    @Test
    public void testReadChildDirectory() {
        List<Document> documents = FileSystemDocumentLoader.loadDocumentsRecursively("C:\\Users\\EDY\\Desktop\\knowledges");
        System.out.println("文档数：" + documents.size());
        documents.forEach(document -> {
            System.out.println("====================");
//            System.out.println(document.text());
            System.out.println(document.metadata());
        });
    }
}
