package uk.zwfield.jx3bot.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * @author Field
 * @date 2024-06-20 13:13
 **/
@Slf4j
public class JXServerConfig {

    /**
     * resources/data/server.json
     */
    public static Map<String, List<String>> SERVER_MAP;

    static {
        try {
            SERVER_MAP = new ObjectMapper().readValue(JXServerConfig.class.getResourceAsStream("/data/server.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("loading……加载服务器列表失败{}", e.getMessage());
        }
    }
}
