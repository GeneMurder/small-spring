package com.yangweiyao.springframework.beans.factory;

public interface DisposableBean {

    void destroy() throws Exception;

}
