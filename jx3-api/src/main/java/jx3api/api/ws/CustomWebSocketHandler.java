package jx3api.api.ws;

import jx3api.api.util.TimeUtils;
import jx3api.api.ws.action.WsActionHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.socket.CloseStatus;
import org.springframework.web.socket.PingMessage;
import org.springframework.web.socket.TextMessage;
import org.springframework.web.socket.WebSocketSession;
import org.springframework.web.socket.handler.TextWebSocketHandler;

import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * ws 处理器
 *
 * @author Grafie
 * @since 1.0.0
 */

public class CustomWebSocketHandler extends TextWebSocketHandler {
    private final Logger logger = LoggerFactory.getLogger(CustomWebSocketHandler.class);
    private final WebSocketClientInitializer webSocketClientInitializer;
    private final WsActionHandler wsActionHandler;
    private WebSocketSession webSocketSession;
    public volatile boolean isConnected = false;

    public CustomWebSocketHandler(WebSocketClientInitializer webSocketClientInitializer, IWsDataPushService iWsDataPushService) {
        this.webSocketClientInitializer = webSocketClientInitializer;
        wsActionHandler = new WsActionHandler(iWsDataPushService);
    }


    @Override
    public void afterConnectionEstablished(WebSocketSession session) throws Exception {
        super.afterConnectionEstablished(session);
        logger.info("WebSocket connect remote server success");
        this.webSocketSession = session;
        startPingTask();
        this.isConnected = true;
    }

    @Override
    protected void handleTextMessage(WebSocketSession session, TextMessage message) throws Exception {
        super.handleTextMessage(session, message);
        wsActionHandler.pushMessage(message);
    }

    @Override
    public void afterConnectionClosed(WebSocketSession session, CloseStatus status) throws Exception {
        super.afterConnectionClosed(session, status);
        logger.error("WebSocket connection closed, try reConnect");
        this.isConnected = false;
        webSocketClientInitializer.reConnect();
    }

    /**
     * 发送ping消息
     *
     */
    private void startPingTask() {
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(this::sendPingMessage, 0, 5, TimeUnit.SECONDS);
    }

    private void sendPingMessage() {
        if (webSocketClientInitializer.getConnectStatus()) {
            try {
                this.webSocketSession.sendMessage(new PingMessage());
                logger.debug("Sent Ping message, now time=>{}", TimeUtils.getNowString());
            } catch (Exception e) {
                logger.error("sent ping message error", e);
            }

        }
    }
}
