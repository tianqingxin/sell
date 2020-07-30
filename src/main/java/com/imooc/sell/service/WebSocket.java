package com.imooc.sell.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

import javax.websocket.OnClose;
import javax.websocket.OnMessage;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;
import java.io.IOException;
import java.util.concurrent.CopyOnWriteArraySet;

@Component
@ServerEndpoint("/webSocket")
@Slf4j
public class WebSocket {
    private Session session;
    public static CopyOnWriteArraySet<WebSocket> webSocketSet = new CopyOnWriteArraySet();

    @OnOpen
    public void open(Session session) {
        this.session = session;
        webSocketSet.add(this);
        log.info("【WebSocket消息】有新的连接，总数为:{}", webSocketSet.size());
    }

    @OnClose
    public void close() {
        webSocketSet.remove(this);
        log.info("【WebSocket消息】连接断开，总数为:{}", webSocketSet.size());
    }

    @OnMessage
    public void onMessage(String message) {
        log.info("客户端发来消息：{}", message);
    }

    public void sendMessage(String message) {
        for (WebSocket webSocket : webSocketSet) {
            log.info("【WebSocket消息】广播消息", message);
            try {
                webSocket.session.getBasicRemote().sendText(message);
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}
