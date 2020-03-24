package com.study.p6_3.chat_room.server;

import com.study.p6_3.chat_room.protocol.IMDecoder;
import com.study.p6_3.chat_room.protocol.IMEncoder;
import com.study.p6_3.chat_room.server.handler.TerminalServerHandler;
import com.study.p6_3.chat_room.server.handler.WebSocketServerHandler;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpObjectAggregator;
import io.netty.handler.codec.http.HttpServerCodec;
import io.netty.handler.codec.http.websocketx.WebSocketServerProtocolHandler;
import io.netty.handler.stream.ChunkedWriteHandler;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class ChatServer {
    public void start(int port) {
        EventLoopGroup boos = new NioEventLoopGroup();
        EventLoopGroup work = new NioEventLoopGroup();
        try {
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(boos, work).channel(NioServerSocketChannel.class).option(ChannelOption.SO_BACKLOG, 1024).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    ChannelPipeline pipeline = socketChannel.pipeline();
                    pipeline.addLast(new IMEncoder());
                    pipeline.addLast(new IMDecoder());

                    //控制台客户端处理handler
                    pipeline.addLast(TerminalServerHandler.INSTAINCE);

                    //websocket协议本身是基于http协议的，所以这边也要使用http解编码器
                    pipeline.addLast(new HttpServerCodec());
                    //以块的方式来写的处理器
                    pipeline.addLast(new ChunkedWriteHandler());
                    //netty是基于分段请求的，HttpObjectAggregator的作用是将请求分段再聚合,参数是聚合字节的最大长度
                    pipeline.addLast(new HttpObjectAggregator(1024*1024*1024));
                    pipeline.addLast(new WebSocketServerProtocolHandler("/im"));
                    pipeline.addLast(WebSocketServerHandler.INSTAINCE);
                }

            });
            ChannelFuture sync = serverBootstrap.bind(port).sync();
            log.info("服务已启动,监听端口" + port);
            sync.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            boos.shutdownGracefully();
            work.shutdownGracefully();
        }
    }

    public static void main(String[] args) {
        new ChatServer().start(10086);
    }
}
