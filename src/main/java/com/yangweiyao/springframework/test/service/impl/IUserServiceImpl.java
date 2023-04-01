package com.yangweiyao.springframework.test.service.impl;

import com.yangweiyao.springframework.test.service.IUserService;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-1 15:44
 */
public class IUserServiceImpl implements IUserService {
    @Override
    public String queryUserName() {
        return "aop";
    }
}
