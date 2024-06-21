package jx3api.api.ws.data.dilu;

import com.fasterxml.jackson.annotation.JsonProperty;
import jx3api.api.ws.data.BaseWsData;
import jx3api.api.ws.data.WsActionData;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 的卢 竞拍
 *
 * @author Grafie
 * @since 1.0.0
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@WsActionData(actionCode = 1012)
public class WsDataAction1012 extends BaseWsData {

    @JsonProperty("role_name")
    private String roleName;
    @JsonProperty("camp_name")
    private String campName;
    @JsonProperty("amount")
    private String amount;

    public String getMsg() {
        return String.format("的卢 竞拍：%s的%s于%s竞拍的卢，竞拍金额：%s", campName, roleName, getTime(), amount);
    }

}
