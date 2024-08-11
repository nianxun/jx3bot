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
    private Long id;

    @JsonProperty("token")
    private Long token;

    @JsonProperty("class")
    private String type;

    @JsonProperty("title")
    private String title;

    @JsonProperty("date")
    private String date;

    @JsonProperty("url")
    private String url;
}
