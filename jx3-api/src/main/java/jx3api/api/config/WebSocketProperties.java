package jx3api.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import java.util.List;

/**
 * ws配置相关类
 *
 * @author Grafie
 * @since 1.0.0
 */
@Configuration("jx3WebSocketProperties")
@ConfigurationProperties(prefix = "jx3api.ws")
@Data
public class WebSocketProperties {
    /**
     * wsToken，ws有些接口需要校验你的token
     */
    private String wsToken;
    /**
     * wsUrl
     */
    private String wsUrl;
    /**
     * 无线重连，默认true
     */
    private Boolean unlimitedReconnection = true;

    /**
     * 重新连接间隔时间，单位秒，如果为空，则默认20秒
     */
    private Integer reConnectInterval = 20;
    /**
     * ws数据解析默认包地址，可以为空
     */
    private List<String> wsDataBeanBasePackage;

}
