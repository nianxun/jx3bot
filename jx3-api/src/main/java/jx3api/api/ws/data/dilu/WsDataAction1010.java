package jx3api.api.ws.data.dilu;

import com.fasterxml.jackson.annotation.JsonProperty;
import jx3api.api.ws.data.BaseWsData;
import jx3api.api.ws.data.WsActionData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 的卢 刷新
 *
 * @author Grafie
 * @since 1.0.0
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@WsActionData(actionCode = 1010)
public class WsDataAction1010 extends BaseWsData {

    @JsonProperty("map_name")
    private String mapName;

    public String getMsg() {
        return String.format("的卢 刷新：%s于在%s刷新的卢", getServer(), mapName);
    }

}
