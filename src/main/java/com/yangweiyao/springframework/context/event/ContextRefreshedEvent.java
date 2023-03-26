package com.yangweiyao.springframework.context.event;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-3-26 16:46
 */
public class ContextRefreshedEvent extends ApplicationContextEvent {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ContextRefreshedEvent(Object source) {
        super(source);
    }
}
