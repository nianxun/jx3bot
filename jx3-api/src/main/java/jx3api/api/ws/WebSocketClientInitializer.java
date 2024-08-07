package jx3api.api.ws;

import jx3api.api.config.WebSocketProperties;
import jx3api.api.ws.action.WsActionDataManager;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.web.socket.client.WebSocketClient;
import org.springframework.web.socket.client.WebSocketConnectionManager;
import org.springframework.web.socket.client.standard.StandardWebSocketClient;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.Executors;
import java.util.concurrent.ScheduledExecutorService;
import java.util.concurrent.TimeUnit;

/**
 * webSocket服务类
 *
 * @author Grafie
 * @since 1.0.0
 */

public class WebSocketClientInitializer {
    private final Logger logger = LoggerFactory.getLogger(WebSocketClientInitializer.class);
    private final WebSocketProperties jx3WebSocketProperties;
    private CustomWebSocketHandler webSocketHandler;
    private WebSocketConnectionManager webSocketConnectionManager;
    private final IWsDataPushService iWsDataPushService;

    public WebSocketClientInitializer(WebSocketProperties jx3WebSocketProperties, IWsDataPushService iWsDataPushService) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        this.jx3WebSocketProperties = jx3WebSocketProperties;
        this.iWsDataPushService = iWsDataPushService;
        connect();
        // 初始化ws推送事件中，序列化相关信息
        initWsActionData(jx3WebSocketProperties);
        // 初始化ws连接检查器
        initCheckConnect();
    }
    private void initWsActionData(WebSocketProperties jx3WebSocketProperties) throws ClassNotFoundException, InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        WsActionDataManager wsActionDataManager = new WsActionDataManager();
        wsActionDataManager.init(jx3WebSocketProperties.getWsDataBeanBasePackage());
    }

    private void connect() {
        beforeStartCheck();
        onConnect();
    }

    private void beforeStartCheck() {
        checkProperties();
        checkWsHandler();
        checkConnectionManager();
    }

    private void checkConnectionManager() {
        if (this.webSocketConnectionManager != null) {
            return;
        }
        WebSocketClient webSocketClient = new StandardWebSocketClient();
        WebSocketConnectionManager connectionManager = new WebSocketConnectionManager(webSocketClient,
                this.webSocketHandler, jx3WebSocketProperties.getWsUrl());
        connectionManager.setAutoStartup(true);
        List<String> token = new ArrayList<>();
        token.add(jx3WebSocketProperties.getWsToken());
        connectionManager.getHeaders().put("token", token);
        this.webSocketConnectionManager = connectionManager;
    }

    private void checkWsHandler() {
        if (this.webSocketHandler == null) {
            this.webSocketHandler = new CustomWebSocketHandler(this, iWsDataPushService);
        }
    }

    private void checkProperties() {
        if (jx3WebSocketProperties == null || jx3WebSocketProperties.getWsUrl() == null
                || jx3WebSocketProperties.getWsUrl().trim().length() <= 0) {
            throw new NullPointerException("webSocketProperties or wsUrl can not be null");
        }
    }

    /**
     * 异步校验连接状态，如果当前连接关闭，则尝试重连
     */
    @Async
    public void onConnect() {
        synchronized (WebSocketClientInitializer.class) {
            try {
                beforeStartCheck();
                webSocketConnectionManager.start();

            } catch (Exception e) {
                logger.error("webSocket reConnect error，remote server url=>{}", jx3WebSocketProperties.getWsUrl(), e);
            }
        }
    }

    public void reConnect() {
        webSocketConnectionManager.stop();
        webSocketConnectionManager = null;
    }

    /**
     * 获取连接状态
     *
     * @return true:connected
     */
    public boolean getConnectStatus() {
        synchronized (WebSocketClientInitializer.class) {
            return webSocketConnectionManager.isConnected();
        }
    }


    private void initCheckConnect() {
        if (!jx3WebSocketProperties.getUnlimitedReconnection()) {
            return;
        }
        ScheduledExecutorService executorService = Executors.newSingleThreadScheduledExecutor();
        executorService.scheduleAtFixedRate(() -> {
            if (webSocketConnectionManager == null || !getConnectStatus() || !webSocketHandler.isConnected) {
                try {
                    connect();
                } catch (Exception ignored) {
                }

            }
        }, 0, jx3WebSocketProperties.getReConnectInterval(), TimeUnit.SECONDS);
    }

}
