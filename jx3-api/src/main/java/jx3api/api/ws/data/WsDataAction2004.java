package jx3api.api.ws.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

/**
 * 八卦速报
 *
 * @author Grafie
 * @since 1.0.0
 */
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@WsActionData(actionCode = 2004)
public class WsDataAction2004 extends BaseWsData {
    @JsonProperty("class")
    private String subclass;

    @JsonProperty("name")
    private String name;

    @JsonProperty("title")
    private String title;

    @JsonProperty("url")
    private String url;

    @JsonProperty("date")
    private String date;

    public String getMsg() {
        return String.format(subclass + "：" + title + "\n" + url);
    }
}
