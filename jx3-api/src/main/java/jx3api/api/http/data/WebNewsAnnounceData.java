package jx3api.api.http.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 维护公告
 *
 * @author Grafie
 * @since 1.0.0
 */
@Data
public class WebNewsAnnounceData {
    @JsonProperty("id")
    private Integer id;

    @JsonProperty("value")
    private Integer value;

    @JsonProperty("type")
    private String type;

    @JsonProperty("title")
    private String title;

    @JsonProperty("date")
    private String date;

    @JsonProperty("url")
    private String url;
}
