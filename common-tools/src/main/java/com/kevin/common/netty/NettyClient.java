package com.kevin.common.netty;

import io.netty.bootstrap.Bootstrap;
import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.util.ReferenceCountUtil;

/**
 * Created by kevin on 2018/5/3.
 */
public class NettyClient {
    public static void main(String[] args) throws InterruptedException {
        EventLoopGroup workerGroup = new NioEventLoopGroup();
        Bootstrap bootstrap = new Bootstrap();
        bootstrap.group(workerGroup)
                .channel(NioSocketChannel.class)
                .handler(new ChannelInitializer<SocketChannel>() {
                    @Override
                    protected void initChannel(SocketChannel socketChannel) throws Exception {
                        socketChannel.pipeline().addLast(new ClientHandler());
                    }
                });
        ChannelFuture future = bootstrap.connect("127.0.0.1", 8888).sync();
        future.channel().writeAndFlush(Unpooled.copiedBuffer("客户端发出呼叫".getBytes()));
        future.channel().closeFuture().sync();
        workerGroup.shutdownGracefully();
    }

    private static class ClientHandler extends ChannelHandlerAdapter {

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            try {
                ByteBuf buf = (ByteBuf) msg;
                byte[] data = new byte[buf.readableBytes()];
                buf.readBytes(data);
                System.out.println("Client：" + new String(data).trim());
            } finally {
                ReferenceCountUtil.release(msg);
            }
        }


        @Override
        public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
            System.out.println("Throwable........");
            cause.printStackTrace();
            ctx.close();
        }

    }

}
