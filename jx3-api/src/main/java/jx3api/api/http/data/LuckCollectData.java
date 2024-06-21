package jx3api.api.http.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 奇遇汇总
 *
 * @author Grafie
 * @since 1.0.0
 */
@Data
public class LuckCollectData {
    @JsonProperty("server")
    private String server;

    @JsonProperty("event")
    private String event;

    @JsonProperty("count")
    private Integer count;

    @JsonProperty("data")
    private CatData data;
}

@Data
class CatData {
    @JsonProperty("name")
    private String name;

    @JsonProperty("time")
    private long time;
}