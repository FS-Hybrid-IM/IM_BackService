package com.accenture.im.proxy;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;

import java.util.Properties;

import org.apache.log4j.PropertyConfigurator;

import com.accenture.im.datacenter.CacheData;
import com.accenture.im.handler.ChannelInitializerImpl;
import com.accenture.im.logicserver.ProxySession;

public class ProxyServer {

    static {
        Properties pro = new Properties();
        pro.put("log4j.rootLogger", "DEBUG,stdout,R");

        pro.put("log4j.appender.stdout", "org.apache.log4j.ConsoleAppender");
        pro.put("log4j.appender.stdout.layout", "org.apache.log4j.PatternLayout");
        pro.put("log4j.appender.stdout.layout.ConversionPattern", "%5p [%t] (%F:%L) - %m%n");

        pro.put("log4j.appender.R", "org.apache.log4j.DailyRollingFileAppender");
        pro.put("log4j.appender.R.Threshold", "INFO");
        pro.put("log4j.appender.R.File", "${user.home}/logs/mars/info.log");
        pro.put("log4j.appender.R.DatePattern", ".yyyy-MM-dd");
        pro.put("log4j.appender.R.layout", "org.apache.log4j.PatternLayout");
        pro.put("log4j.appender.R.layout.ConversionPattern", "[%d{HH:mm:ss:SSS}] [%p] - %l - %m%n");

        PropertyConfigurator.configure(pro);
    }


    private int port;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    private ServerBootstrap serverBootstrap;

    private ChannelHandler channelHandler;

    /**
     *
     * @param port
     */
    public ProxyServer(int port) {
        this.port = port;
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();

        channelHandler = new ChannelInitializerImpl<SocketChannel>();
    }

    public void start() throws Exception {
        try {
            serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
                    .childHandler(channelHandler)
                    .option(ChannelOption.SO_BACKLOG, 128)
                    .childOption(ChannelOption.SO_KEEPALIVE, true);

            ChannelFuture channelFuture = serverBootstrap.bind(port).sync();
            channelFuture.channel().closeFuture().sync();
        }
        catch (Exception e) {

        }
        finally {
            workerGroup.shutdownGracefully();
            bossGroup.shutdownGracefully();
        }
    }

    public static void main(String[] args) throws Exception {

//        // init non-persistent database
        CacheData.connect();
        ProxySession.Manager.connect();

        int port;
        if (args.length > 0) {
            port = Integer.parseInt(args[0]);
        } else {
            port = 8081;
        }
        new ProxyServer(port).start();

        ProxySession.Manager.disconnect();
//        CacheData.disconnect();
    }
}

