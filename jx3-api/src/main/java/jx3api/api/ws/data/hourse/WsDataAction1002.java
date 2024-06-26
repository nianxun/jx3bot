package jx3api.api.ws.data.hourse;

import com.fasterxml.jackson.annotation.JsonProperty;
import jx3api.api.ws.data.BaseWsData;
import jx3api.api.ws.data.WsActionData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 抓马报时 刷新
 * @author Grafie
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@WsActionData(actionCode = 1002)
public class WsDataAction1002 extends BaseWsData {
    @JsonProperty("map_name")
    private String mapName;

    @JsonProperty("min_time")
    private int minTime;

    @JsonProperty("max_time")
    private int maxTime;

    public String getMsg() {
        return String.format("抓马报时 刷新：%s，时间：%d-%d", mapName, minTime, maxTime);
    }
}
