package com.study.p6_3.tomcat;

import com.study.p6_3.tomcat.http.NRequest;
import com.study.p6_3.tomcat.http.NResponse;
import com.study.p6_3.tomcat.http.NServlet;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpRequestDecoder;
import io.netty.handler.codec.http.HttpResponseEncoder;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

public class NTomcat {
    private final int port;
    private Properties webPropertis = new Properties();
    private Map<String, NServlet> servletMap = new HashMap<>();

    public NTomcat(int port) {
        this.port = port;
    }

    private void init() {
        String WEB_INF = this.getClass().getResource("/").getPath();
        FileInputStream fis = null;
        try {
            fis = new FileInputStream(WEB_INF + "web.properties");
            webPropertis.load(fis);
            for (Object k : webPropertis.keySet()) {
                String key = k.toString();
                if (key.endsWith(".url")) {
                    String servletName = key.replaceAll("\\.url$", "");
                    String url = webPropertis.getProperty(key);
                    String className = webPropertis.getProperty(servletName + ".className");
                    NServlet obj = (NServlet) Class.forName(className).newInstance();
                    servletMap.put(url, obj);
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }

    public void start() {
        init();
        EventLoopGroup bossEventLoopGroup = null;
        EventLoopGroup workEventLoopGroup = null;
        try {
            bossEventLoopGroup = new NioEventLoopGroup();
            workEventLoopGroup = new NioEventLoopGroup();
            ServerBootstrap serverBootstrap = new ServerBootstrap();
            serverBootstrap.group(bossEventLoopGroup, workEventLoopGroup).channel(NioServerSocketChannel.class).localAddress(port).childHandler(new ChannelInitializer<SocketChannel>() {
                @Override
                protected void initChannel(SocketChannel socketChannel) throws Exception {
                    socketChannel.pipeline().addLast(new HttpResponseEncoder());
                    socketChannel.pipeline().addLast(new HttpRequestDecoder());
                    socketChannel.pipeline().addLast(new NTomcatHandler());
                }
            }).option(ChannelOption.SO_BACKLOG, 128)// 针对主线程的配置 分配线程最大数量 128
                    .childOption(ChannelOption.SO_KEEPALIVE, true);// 针对子线程的配置 保持长连接

            ChannelFuture channelFuture = serverBootstrap.bind().sync();
            System.out.println("开始监听，端口为：" + channelFuture.channel().localAddress());
            channelFuture.channel().closeFuture().sync();
        } catch (InterruptedException e) {
            e.printStackTrace();
        } finally {
            try {
                bossEventLoopGroup.shutdownGracefully().sync();
                workEventLoopGroup.shutdownGracefully().sync();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    public static void main(String[] args) {
        new NTomcat(8080).start();
    }

    private class NTomcatHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            if (msg instanceof HttpRequest){
                HttpRequest req = (HttpRequest) msg;
                // 转交给我们自己的request实现
                NRequest request = new NRequest(ctx,req);
                // 转交给我们自己的response实现
                NResponse response = new NResponse(ctx,req);
                // 实际业务处理
                String url = request.getUrl();

                if(servletMap.containsKey(url)){
                    servletMap.get(url).service(request, response);
                }else{
                    response.write("404 - Not Found");
                }
            }
        }
    }
}
