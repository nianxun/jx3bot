package jx3api.api.ws.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 * 新闻资讯
 *
 * @author Grafie
 * @since 1.0.0
 */
@NoArgsConstructor
@EqualsAndHashCode(callSuper = true)
@Data
@ToString(callSuper = true)
@WsActionData(actionCode = 2002)
public class WsDataAction2002 extends BaseWsData {


    @JsonProperty("type")
    private String type;
    @JsonProperty("title")
    private String title;
    @JsonProperty("url")
    private String url;
    @JsonProperty("date")
    private String date;

    public String getMsg() {
        return String.format(type + "：" + title + "\n" + url + "\n" + date);
    }
}
