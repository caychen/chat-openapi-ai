package com.caychen.chatai.dashscope;


import dev.langchain4j.community.model.dashscope.WanxImageModel;
import dev.langchain4j.data.image.Image;
import dev.langchain4j.model.output.Response;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 10:55
 * @Description:
 */
@SpringBootTest
public class DashScopeImageModelTest3 {

    @Test
    public void testImageModel1() {
        WanxImageModel imageModel = WanxImageModel.builder()
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .modelName("wanx2.1-t2i-plus")
                .build();

        Response<Image> imageResponse = imageModel.generate("一条红色巨龙在雪山上飞翔，翅膀展开，电影光效，幻想风插画，Frank Frazetta 风格，–ar 16:9");

        // 返回
        System.out.println(imageResponse.content().url());
    }

    @Test
    public void testImageModel2() {
        WanxImageModel imageModel = WanxImageModel.builder()
                .apiKey(System.getenv("DASH_SCOPE_API_KEY"))
                .modelName("wanx2.1-t2i-plus")
                .build();

        Response<Image> imageResponse = imageModel.generate("夜晚的未来城市天际线，霓虹灯，飞行汽车，Blade Runner 风格");

        // 返回
        System.out.println(imageResponse.content().url());
    }
}
