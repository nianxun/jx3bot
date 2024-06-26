package jx3api.api.http.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

/**
 * 歪歪频道
 *
 * @author Grafie
 * @since 1.0.0
 */
@Data
public class DuowanStatisticalData {
    @JsonProperty("campName")
    private String campName;

    @JsonProperty("sid")
    private int sid;

    @JsonProperty("logoUrl")
    private String logoUrl;

    @JsonProperty("users")
    private int users;

    @JsonProperty("snick")
    private String snick;

    @JsonProperty("limit")
    private int limit;

    @JsonProperty("logo")
    private int logo;

    @JsonProperty("asid")
    private int asid;
}
