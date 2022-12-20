package com.secdn.secdnrapid.generator;


import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.config.*;

import java.util.Collections;
import java.util.ResourceBundle;

/**
 * <p>
 *     代码生成器
 * </p>
 */
public class Generator {

    public static void main(String[] args){
        final ResourceBundle rb = ResourceBundle.getBundle("mybatis-plus");
        final DataSourceConfig DATA_SOURCE_CONFIG = new DataSourceConfig
                .Builder(rb.getString("url"), rb.getString("username"), rb.getString("password"))
                .build();
        AutoGenerator autoGenerator = new AutoGenerator(DATA_SOURCE_CONFIG);
        autoGenerator.global(new GlobalConfig.Builder()
                        .author(rb.getString("author")) // 设置作者
                        .enableSwagger() // 开启 swagger 模式
                        .fileOverride() // 覆盖已生成文件
                        .outputDir(rb.getString("outputDir"))
                        .build())
                .packageInfo(new PackageConfig.Builder()
                        .parent(rb.getString("parent")) // 设置父包名
//                        .moduleName("system") // 设置父包模块名
                        .pathInfo(Collections.singletonMap(OutputFile.xml, rb.getString("outputDir")))
                        .build())
                .strategy(new StrategyConfig.Builder()
                        .addInclude(rb.getString("tableName").split(","))
                        .build()
                        .entityBuilder() // 设置实体配置
                        .enableLombok() // 开启Lombok
                        .enableTableFieldAnnotation()
                        .build()
                        .controllerBuilder()
                        .enableRestStyle()
                        .build()
                        .serviceBuilder()
                        .formatServiceFileName("%sService")
                        .formatServiceImplFileName("%sServiceImpl")
                        .build())
                .template(new TemplateConfig.Builder()
                        .controller("/templates/controller.java.vm")
                        .entity("/templates/entity.java.vm")
                        .xml("/templates/mapper.xml.vm")
                        .mapper("/templates/mapper.java.vm")
                        .service("/templates/service.java.vm")
                        .serviceImpl("/templates/serviceImpl.java.vm")
                        .build())
                .injection(injectionConfig())
                .execute();
    }
    /**
     * 注入配置
     */
    private static InjectionConfig injectionConfig() {
        // 测试自定义输出文件之前注入操作，该操作再执行生成代码前 debug 查看
        return new InjectionConfig.Builder().beforeOutputFile((tableInfo, objectMap) -> {
            System.out.println("tableInfo: " + tableInfo.getEntityName() + " objectMap: " + objectMap.size());
        }).build();
    }
}
