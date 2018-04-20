package com.accenture.im.handler;

import io.netty.channel.Channel;
import io.netty.channel.ChannelInitializer;

import com.accenture.im.proxy.NetMsgHeaderHandler;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

@Component
@Qualifier("channelInitializer")
public class ChannelInitializerImpl<T extends Channel> extends ChannelInitializer<T> {

    @Override
    protected void initChannel(T channel) throws Exception {
        channel.pipeline().addLast(new NetMsgHeaderHandler());
    }

}