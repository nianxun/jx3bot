package jx3api.api.config;

import jakarta.annotation.Resource;
import jx3api.api.ws.IWsDataPushService;
import jx3api.api.ws.WebSocketClientInitializer;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Conditional;
import org.springframework.context.annotation.Configuration;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动装配类
 *
 * @author Grafie
 * @since 1.0.0
 */
@ConditionalOnClass(value = EnableJX3ApiWS.class)
@Configuration
@Conditional(OnEnableJX3ApiWSCondition.class)
public class JX3ApiWsAutoConfiguration {
    /**
     * 默认ws data的包路径
     */
    private static final String DEFAULT_WS_DATA_PACKAGE = "jx3api.api.ws.data";
    private static final Logger logger = LoggerFactory.getLogger(JX3ApiWsAutoConfiguration.class);
    @Resource
    private WebSocketProperties jx3WebSocketProperties;
    @Resource
    private IWsDataPushService iWsDataPushService;

    @Bean
    public WebSocketClientInitializer webSocketClientInitializer() throws ClassNotFoundException,
            InvocationTargetException, NoSuchMethodException, InstantiationException, IllegalAccessException {
        if (StringUtils.isBlank(jx3WebSocketProperties.getWsUrl())) {
            throw new NullPointerException("ws远程地址不允许为空,请检查配置信息");
        }
        if (StringUtils.isBlank(jx3WebSocketProperties.getWsToken())) {
            logger.error("未获取到wsToken，付费ws推送将无法使用");
        }
        if (jx3WebSocketProperties.getWsDataBeanBasePackage() == null) {
            List<String> wsDataBasePackageList = new ArrayList<>();
            jx3WebSocketProperties.setWsDataBeanBasePackage(wsDataBasePackageList);
        }
        jx3WebSocketProperties.getWsDataBeanBasePackage().add(DEFAULT_WS_DATA_PACKAGE);
        logger.info("欢迎使用JX3 API Java sdk websocket");
        return new WebSocketClientInitializer(jx3WebSocketProperties, iWsDataPushService);
    }

}
