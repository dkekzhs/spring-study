package com.example.nettyrealtimeexcutionprice.handler;

import com.fasterxml.jackson.databind.JsonNode;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelDuplexHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelPromise;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.util.CharsetUtil;
import org.springframework.stereotype.Component;

@Component
public class KoreaInvestmentHandler extends ChannelDuplexHandler {


    private final String appKey = "";
    private final String trId ="";
    private final String appSecret ="";
    private final String trKey ="";


    private final ObjectMapper objectMapper = new ObjectMapper();
    @Override
    public void write(ChannelHandlerContext ctx, Object msg, ChannelPromise promise) throws Exception {
        sendSubscriptionRequest(ctx, appKey, appSecret, trId, trKey);
    }


    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) {
        String message = "";
        if (msg instanceof ByteBuf) {
            ByteBuf byteBuf = (ByteBuf) msg;
            message = byteBuf.toString(CharsetUtil.UTF_8); // UTF-8로 변환
        }
        System.out.println("Received message: " + message);

        try {
            JsonNode jsonNode = objectMapper.readTree(message);
            System.out.println(jsonNode);
        } catch (Exception e) {
            System.err.println("Failed to parse JSON: " + e.getMessage());
        }
    }


    public void sendSubscriptionRequest(ChannelHandlerContext ctx, String appKey, String appSecret, String trId, String trKey) {
        String request = String.format(
                "{\"header\": {\"appkey\": \"%s\", \"appsecret\": \"%s\", \"custtype\": \"P\", \"tr_type\": \"1\", \"content-type\": \"utf-8\"}, " +
                        "\"body\": {\"input\": {\"tr_id\": \"%s\", \"tr_key\": \"%s\"}}}",
                appKey, appSecret, trId, trKey
        );
        ctx.writeAndFlush(request);
    }

}
