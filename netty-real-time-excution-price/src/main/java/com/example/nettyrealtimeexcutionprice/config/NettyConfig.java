package com.example.nettyrealtimeexcutionprice.config;

import com.example.nettyrealtimeexcutionprice.handler.KoreaInvestmentHandler;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Configuration;

import java.net.InetSocketAddress;
import java.net.SocketAddress;

@Configuration
public class NettyConfig {

    @Value("${data.host}")
    private String host;

    @Value("${data.port}")
    private int port;


    public void run() throws InterruptedException {
        NioEventLoopGroup group = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(group)
                    .channel(NioSocketChannel.class)
                    .option(ChannelOption.SO_KEEPALIVE, true)
                    .handler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        public void initChannel(SocketChannel ch) throws Exception {
                            ChannelPipeline p = ch.pipeline();
                            p.addLast(new KoreaInvestmentHandler());
                        }
                    });

            System.out.println(host  + " " + port);
            ChannelFuture future = bootstrap.bind(0).sync();
            SocketAddress address = new InetSocketAddress(host, port);
            bootstrap.connect(address).sync().channel();
            future.channel().closeFuture().await();
        } finally {
            group.shutdownGracefully();
        }


    }
}
