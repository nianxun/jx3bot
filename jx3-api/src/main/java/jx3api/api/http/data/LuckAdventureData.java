package jx3api.api.http.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import jx3api.api.util.TimeUtils;
import lombok.Data;

import java.time.Duration;
import java.time.Instant;

/**
 * 奇遇记录
 *
 * @author Grafie
 * @since 1.0.0
 */
@Data
public class LuckAdventureData {

    @JsonProperty("zone")
    private String zone;

    @JsonProperty("server")
    private String server;

    @JsonProperty("name")
    private String name;

    @JsonProperty("event")
    private String event;

    @JsonProperty("level")
    private Integer level;

    @JsonProperty("source")
    private Integer source;

    @JsonProperty("status")
    private Integer status;

    @JsonProperty("time")
    private Long time;

    private String timeStr;

    private String durationStr;



    public void setTime(long time) {
        this.time = time;
        this.timeStr = time == 0 ? "未知" : TimeUtils.timeFormatting(time);
        // 计算时间间隔
        this.durationStr = time == 0 ? "未知" : Duration.between(Instant.ofEpochSecond(time), Instant.now()).toDays() + " 天前";
    }
}
