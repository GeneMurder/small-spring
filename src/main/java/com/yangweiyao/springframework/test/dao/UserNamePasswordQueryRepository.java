package com.yangweiyao.springframework.test.dao;

import com.yangweiyao.springframework.stereotype.Component;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-4-15 17:55
 */
@Component
public class UserNamePasswordQueryRepository {

    public boolean checkUsername(String username) {
        return "root".equals(username);
    }

}
