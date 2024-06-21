package uk.zwfield.jx3bot.utils;

import com.baomidou.mybatisplus.generator.FastAutoGenerator;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;

/**
 * @author Field
 * @date 2024-06-20 16:32
 **/
public class GeneratorCode {

    public static void main(String[] args) {
        String tableName = "t_data_config";
        // 使用 FastAutoGenerator 快速配置代码生成器
        FastAutoGenerator.create("jdbc:sqlite:src/main/resources/db/bot.sqlite", "root", "password")
                .globalConfig(builder -> {
                    builder.author("field") // 设置作者
                            .outputDir("src/main/java"); // 输出目录
                })
                .packageConfig(builder -> {
                    builder.parent("uk.zwfield.jx3bot") // 设置父包名
                            .entity("entity") // 设置实体类包名
                            .mapper("mapper") // 设置 Mapper 接口包名
                            .xml("mapper"); // 设置 Mapper XML 文件包名
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName)
                            .addTablePrefix("t_", "c_"); // 设置过滤表前缀
                })
                .strategyConfig(builder -> {
                    builder.addInclude(tableName) // 设置需要生成的表名
                            .entityBuilder()
                            .enableLombok() // 启用 Lombok
                            .enableTableFieldAnnotation() // 启用字段注解
                            .serviceBuilder().disableServiceImpl().disableService()
                            .controllerBuilder()
                            .disable()
                            .enableRestStyle(); // 启用 REST 风格
                })
                .templateEngine(new FreemarkerTemplateEngine()) // 使用 Freemarker 模板引擎
                .execute(); // 执行生成
    }
}
