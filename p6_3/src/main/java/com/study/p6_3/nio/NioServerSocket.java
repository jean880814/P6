package com.study.p6_3.nio;

import java.io.IOException;
import java.net.InetSocketAddress;
import java.nio.ByteBuffer;
import java.nio.channels.SelectionKey;
import java.nio.channels.Selector;
import java.nio.channels.ServerSocketChannel;
import java.nio.channels.SocketChannel;
import java.util.Iterator;
import java.util.Set;

public class NioServerSocket {
    private Selector selector;
    private ByteBuffer buffer = ByteBuffer.allocate(1024);

    public NioServerSocket(int port){
        try {
            ServerSocketChannel serverSocketChannel = ServerSocketChannel.open();
            selector = Selector.open();
            serverSocketChannel.bind(new InetSocketAddress(port));
            serverSocketChannel.configureBlocking(false);
            serverSocketChannel.register(selector, SelectionKey.OP_ACCEPT);
            System.out.println("监听端口：" + port);
        } catch (IOException e) {
            System.out.println("监听端口失败" + e.getStackTrace().toString());
        }

    }

    public void listen(){
        try {
            while (true) {
                selector.select();
                Set<SelectionKey> selectionKeys = selector.selectedKeys();
                Iterator<SelectionKey> iterator = selectionKeys.iterator();
                while (iterator.hasNext()) {
                    SelectionKey next = iterator.next();
                    iterator.remove();
                    process(next);
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void process(SelectionKey key) throws IOException {
        if (key.isAcceptable()) {
            ServerSocketChannel serverSocketChannel = (ServerSocketChannel) key.channel();
            SocketChannel accept = serverSocketChannel.accept();
            accept.configureBlocking(false);
            System.out.println("连接的地址:" + accept.getRemoteAddress() );
            key.interestOps(SelectionKey.OP_READ);
        } else if(key.isReadable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            int len = socketChannel.read(buffer);
            if (len > 0) {
                buffer.flip();
                String content = new String(buffer.array(), 0, len);
                System.out.println("接收到来着" + socketChannel.getRemoteAddress() + "的信息 ：" +content);
                key.interestOps(SelectionKey.OP_WRITE);
                key.attach(content);
                buffer.clear();
            }
        } else if (key.isWritable()) {
            SocketChannel socketChannel = (SocketChannel) key.channel();
            String attachment = (String) key.attachment();
            socketChannel.write(ByteBuffer.wrap(("输出" + attachment).getBytes()));
            socketChannel.close();
        }
    }

    public static void main(String[] args) {
        new NioServerSocket(8080).listen();
    }
}
