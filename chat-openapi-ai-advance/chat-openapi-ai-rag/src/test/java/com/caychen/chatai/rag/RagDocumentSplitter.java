package com.caychen.chatai.rag;


import dev.langchain4j.community.model.dashscope.QwenTokenCountEstimator;
import dev.langchain4j.data.message.UserMessage;
import dev.langchain4j.model.embedding.onnx.HuggingFaceTokenCountEstimator;
import org.junit.jupiter.api.Test;

/**
 * @Author: Caychen
 * @Date: 2025/5/11 21:17
 * @Description:
 */
public class RagDocumentSplitter {

    @Test
    public void testDocumentSplitter() {
        // 创建一个DocumentSplitter对象
        String message = "上汽大众汽车有限公司（简称“上汽大众”）是一家中德合资企业，企业成立于1985年2月16日，由上汽集团和大众汽车集团合资经营，是国内历史悠久的汽车合资企业之一。公司总部位于上海安亭，并先后在南京、仪征、乌鲁木齐、宁波、长沙等地建立了生产基地。\n" +
                "上汽大众生产与销售大众 、奥迪 和斯柯达 三个品牌的产品，覆盖A0级 、A级 、B级 、C级 、SUV 、MPV等细分市场。 其中，大众品牌车型有Polo、朗逸、朗逸新锐、凌渡L、帕萨特家族、途观家族、途昂家族、途安L、途铠、途岳家族、威然、ID.纯电家族等产品系列； 斯柯达品牌车型有明锐Pro、速派、柯迪亚克家族、柯珞克、柯米克家族等产品系列；上汽奥迪车型包括A7L、Q5e-tron、Q6等产品系列。\n" +
                "面对汽车产业电动化、智能网联化的技术变革，公司持续推动转型升级和新能源产品的研发，已相继推出途观L插电式混合动力版、帕萨特插电式混合动力版、ID.4X、ID.6X、ID.3以及奥迪Q5e-tron等多款插电混动、纯电车型，并将继续拓展新能源产品的矩阵。\n" +
                "上汽大众将坚持以创新驱动发展 ，以市场为导向 ，提升用户满意度 ，成为“值得信赖 、富有创新精神的汽车合资企业”。\n" +
                "2018年6月，奥迪汽车股份公司获得大众汽车集团所持有的上汽大众1%的股权。从法规和产业政策上，上汽大众已经具备生产奥迪的资质。";
        UserMessage userMessage = UserMessage.userMessage(message);

        HuggingFaceTokenCountEstimator tokenCountEstimator = new HuggingFaceTokenCountEstimator();
        int count = tokenCountEstimator.estimateTokenCountInMessage(userMessage);
        System.out.println("token长度：" + count); //534

        QwenTokenCountEstimator qwenTokenCountEstimator = new QwenTokenCountEstimator(System.getenv("DASH_SCOPE_API_KEY"), "qwen-max");
        int count2 = qwenTokenCountEstimator.estimateTokenCountInMessage(userMessage);
        System.out.println("qwen-token长度：" + count2); //381

    }
}
