package com.study.p6_3.chat_room.client;

import com.study.p6_3.chat_room.client.handler.ClientHander;
import com.study.p6_3.chat_room.protocol.IMDecoder;
import com.study.p6_3.chat_room.protocol.IMEncoder;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;

public class ClientServer {
    private final ClientHander clientHander;

    public ClientServer(ClientHander clientHander) {
        this.clientHander = clientHander;
    }

    public void start(String add, int port) {
        EventLoopGroup eventLoopGroup = new NioEventLoopGroup();
        try {
            Bootstrap bootstrap = new Bootstrap();
            bootstrap.group(eventLoopGroup).channel(NioSocketChannel.class).handler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new IMDecoder());
                    pipeline.addLast(new IMEncoder());
                    pipeline.addLast(clientHander);
                }
            }).option(ChannelOption.SO_KEEPALIVE, true);
            ChannelFuture sync = bootstrap.connect(add, port).sync();
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            eventLoopGroup.shutdownGracefully();
        }

    }

    public static void main(String[] args) {
        new ClientServer(new ClientHander("Jean")).start("localhost", 10086);
    }
}
