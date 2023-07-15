package com.sdu.springframework.io.netty.base;

import com.sdu.springframework.io.netty.base.server.DiscardServer;

public class NettyStarter {

    public static void main(String[] args) throws Exception {
        new DiscardServer(9001).run();
    }
}
