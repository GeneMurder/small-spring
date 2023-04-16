package com.yangweiyao.springframework.test.service.impl;

import cn.hutool.core.util.StrUtil;
import com.yangweiyao.springframework.beans.factory.annotation.Autowired;
import com.yangweiyao.springframework.beans.factory.annotation.Value;
import com.yangweiyao.springframework.stereotype.Component;
import com.yangweiyao.springframework.test.dao.UserNamePasswordQueryRepository;
import com.yangweiyao.springframework.test.service.LoginService;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-15 15:56
 */
@Component
public class LoginServiceImpl implements LoginService {

    @Value("${token}")
    private String token;

    @Autowired
    private UserNamePasswordQueryRepository userNamePasswordQueryRepository;

    @Override
    public boolean login(String token) {
        System.out.printf("token:%s, your token:%s\n", this.token, token);

        if(this.token.equals(token) || userNamePasswordQueryRepository.checkUsername(token)) {
            System.out.println("登录成功");
            return true;
        } else {
            System.out.println("账号或者密码错误");
            return false;
        }
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
