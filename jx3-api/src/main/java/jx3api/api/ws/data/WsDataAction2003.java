package jx3api.api.ws.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 游戏更新
 *
 * @author Grafie
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@WsActionData(actionCode = 2003)
public class WsDataAction2003 extends BaseWsData {
    @JsonProperty("old_version")
    private String oldVersion;

    @JsonProperty("new_version")
    private String newVersion;

    @JsonProperty("package_num")
    private int packageNum;

    @JsonProperty("package_size")
    private String packageSize;

    public String getMsg() {
        return String.format("游戏更新：游戏更新了，版本号：%s -> %s，更新包大小：%s，更新包数量：%d", oldVersion, newVersion, packageSize, packageNum);
    }
}
