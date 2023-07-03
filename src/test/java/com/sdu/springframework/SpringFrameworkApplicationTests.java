package com.sdu.springframework;

import com.sdu.springframework.factory.CglibProxyFactory;
import com.sdu.springframework.factory.JdkProxyFactory;
import com.sdu.springframework.publisher.EventPublisher;
import com.sdu.springframework.service.AliSmsService;
import com.sdu.springframework.service.SmsService;
import com.sdu.springframework.service.impl.SmsServiceImpl;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SpringFrameworkApplicationTests {

    @Autowired
    private EventPublisher eventPublisher;

    @Test
    void contextLoads() {
        eventPublisher.publish("hello");
    }

    @Test
    void JdkProxyTest() {
        SmsService smsService = (SmsService) JdkProxyFactory.getProxy(new SmsServiceImpl());
        smsService.send("hello");
    }

    @Test
    void CglibProxyTest() {
        AliSmsService aliSmsService = (AliSmsService) CglibProxyFactory.getProxy(AliSmsService.class);
        aliSmsService.send("Ali SmsService send");
    }

}
