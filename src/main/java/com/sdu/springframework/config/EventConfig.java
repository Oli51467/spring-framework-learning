package com.sdu.springframework.config;

import com.sdu.springframework.event.MyEvent;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.event.EventListener;

@Configuration
public class EventConfig {

    @EventListener(classes = {MyEvent.class})
    public void listen(MyEvent event) {
        System.out.println("事件触发：" + event.getMsg());
    }
}
