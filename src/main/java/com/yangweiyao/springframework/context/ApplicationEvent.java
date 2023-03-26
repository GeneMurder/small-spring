package com.yangweiyao.springframework.context;

import java.util.EventObject;

/**
 * @author YangWeiYao
 * @Description
 * @date 2023-3-26 16:43
 */
public class ApplicationEvent extends EventObject {
    /**
     * Constructs a prototypical Event.
     *
     * @param source The object on which the Event initially occurred.
     * @throws IllegalArgumentException if source is null.
     */
    public ApplicationEvent(Object source) {
        super(source);
    }
}
