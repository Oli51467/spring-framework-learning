package com.sdu.springframework.publisher;

import com.sdu.springframework.event.MyEvent;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@Component
public class EventPublisher {

    @Autowired
    private ApplicationEventPublisher applicationEventPublisher;

    public void publish(String msg) {
        applicationEventPublisher.publishEvent(new MyEvent(this, msg));
    }
}
