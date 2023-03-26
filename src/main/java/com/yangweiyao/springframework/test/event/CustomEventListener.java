package com.yangweiyao.springframework.test.event;

import com.yangweiyao.springframework.context.ApplicationListener;

import java.util.Date;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-3-26 18:06
 */
public class CustomEventListener implements ApplicationListener<CustomEvent>  {
    @Override
    public void onApplicationEvent(CustomEvent event) {
        System.out.println("收到：" + event.getSource() + "消息;时间：" + new Date());
        System.out.println("消息：" + event.getId() + ":" + event.getMessage());
    }
}
