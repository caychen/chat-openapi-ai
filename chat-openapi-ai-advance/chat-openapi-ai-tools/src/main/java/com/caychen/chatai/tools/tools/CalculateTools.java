package com.caychen.chatai.tools.tools;


import dev.langchain4j.agent.tool.P;
import dev.langchain4j.agent.tool.Tool;
import dev.langchain4j.agent.tool.ToolMemoryId;
import org.springframework.stereotype.Component;

/**
 * @Author: Caychen
 * @Date: 2025/5/10 20:45
 * @Description:
 */
@Component
public class CalculateTools {

    @Tool
    public double sum(double a, double b) {
        System.out.println("调用了加法运算");
        return a + b;
    }

    @Tool
    public double sqrt(double a) {
        System.out.println("调用了平方根运算");
        return Math.sqrt(a);
    }

    @Tool(name = "减法运算", value = "将两个参数a和b进行相减并返回运算结果")
    public double sub(double a, double b) {
        System.out.println("调用了减法运算");
        return a - b;
    }

    @Tool(name = "乘法运算")
    public double multi(@P("乘数1") double a, @P("乘数2") double b) {
        System.out.println("调用了乘法运算");
        return a * b;
    }

    /**
     * 如果要使用@ToolMemoryId，则在AiService注解标注的类上必须指定chatMemoryProvider，因为它是根据memoryId作为隔离存储
     * 还因为@ToolMemoryId要使用memoryId作为隔离，则chatmemory就需要支持隔离，
     * 而普通的chatmemory不支持隔离，所以需要提供chatMemoryProvider
     *
     * @param memoryId
     * @param a
     * @param b
     * @return
     */
    @Tool(name = "除法运算")
    public double division(
            @ToolMemoryId String memoryId,
            @P(value = "被除数", required = false) double a,
            @P(value = "除数", required = false)double b) {
        System.out.println("调用了除法运算, memoryId=" + memoryId);
        return a / b;
    }
}
