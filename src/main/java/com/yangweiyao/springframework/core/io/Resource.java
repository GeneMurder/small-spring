package com.yangweiyao.springframework.core.io;

import java.io.IOException;
import java.io.InputStream;

/**
 * 用于处理资源加载流
 * 分别实现三种不同的流文件操作：classPath、FileSystem、URL
 */
public interface Resource {

    /**
     * 获取 InputStream 流
     * @return InputStream
     * @throws IOException
     */
    InputStream getInputStream() throws IOException;

}
