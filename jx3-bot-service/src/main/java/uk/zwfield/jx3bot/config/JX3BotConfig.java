package uk.zwfield.jx3bot.config;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import lombok.extern.slf4j.Slf4j;

import java.io.IOException;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Field
 * @date 2024-06-20 13:13
 **/
@Slf4j
public class JX3BotConfig {

    /**
     * resources/data/server.json
     */
    public static Map<String, List<String>> SERVER_MAP;

    public static Map<String, Integer> LUCK_MAP;

    static {
        try {
            SERVER_MAP = new ObjectMapper().readValue(JX3BotConfig.class.getResourceAsStream("/data/server.json"), new TypeReference<>() {
            });
        } catch (IOException e) {
            log.error("loading……加载服务器列表失败{}", e.getMessage());
        }

        try {
            List<Map<String, String>> list = new ObjectMapper().readValue(JX3BotConfig.class.getResourceAsStream("/data/luck.json"), new TypeReference<>() {
            });
            LUCK_MAP = list.stream().collect(Collectors.toMap(e -> e.get("name"), e -> Integer.parseInt(e.get("id"))));
        } catch (IOException e) {
            log.error("loading……加载奇遇数据列表失败{}", e.getMessage());
        }
    }
}
