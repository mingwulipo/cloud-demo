package org.study.cloud.generator;

import org.mybatis.generator.api.MyBatisGenerator;
import org.mybatis.generator.config.Configuration;
import org.mybatis.generator.config.xml.ConfigurationParser;
import org.mybatis.generator.internal.DefaultShellCallback;

import java.util.ArrayList;
import java.util.List;

public class GeneratorMain {
    public static void main(String[] args) throws Exception {
        List<String> warnings = new ArrayList<>();

        ConfigurationParser cp = new ConfigurationParser(warnings);
        Configuration config = cp.parseConfiguration(GeneratorMain.class.getResourceAsStream("/generatorConfig.xml"));

        DefaultShellCallback callback = new DefaultShellCallback(true);
        MyBatisGenerator myBatisGenerator = new MyBatisGenerator(config, callback, warnings);

        myBatisGenerator.generate(null);
    }
}

