package jx3api.api.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

/**
 * Api相关配置信息
 *
 * @author Grafie
 * @since 1.0.0
 */
@Data
@Configuration
@ConfigurationProperties(prefix = "jx3api.api")
public class ApiProperties {
    /**
     * api访问地址, 如果为空，则默认 <a href="https://api.jx3api.com">https://api.jx3api.com</a>
     */
    private String apiUrl = "https://api.jx3api.com";
    /**
     * api访问token，有些api接口，需要校验你的token
     */
    private String apiToken;

    /**
     * ticket
     */
    private String ticket;
}
