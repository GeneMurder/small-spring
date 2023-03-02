package com.yangweiyao.springframework.test;

import cn.hutool.core.io.IoUtil;
import com.yangweiyao.springframework.core.io.DefaultResourceLoader;
import com.yangweiyao.springframework.core.io.Resource;
import com.yangweiyao.springframework.core.io.ResourceLoader;

import java.io.IOException;

public class ResourceLoaderTest {

    public static void main(String[] args) throws IOException {
        ResourceLoader resourceLoader = new DefaultResourceLoader();
        Resource resourceProperties = resourceLoader.getResource("classpath:important.properties");
        Resource resourceXml = resourceLoader.getResource("src/main/resources/spring.xml");
        String contentProperties = IoUtil.readUtf8(resourceProperties.getInputStream());
        String contentXml = IoUtil.readUtf8(resourceXml.getInputStream());
        System.out.println(contentProperties);
        System.out.println(contentXml);
    }

}
