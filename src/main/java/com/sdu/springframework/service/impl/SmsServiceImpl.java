package com.sdu.springframework.service.impl;

import com.sdu.springframework.service.SmsService;
import org.springframework.stereotype.Service;

@Service("SmsService")
public class SmsServiceImpl implements SmsService {

    @Override
    public void send(String message) {
        System.out.println("Proxy send: " + message);
    }
}
