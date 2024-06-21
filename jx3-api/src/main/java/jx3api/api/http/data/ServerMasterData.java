package jx3api.api.http.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;

import java.util.List;
import java.util.Map;

/**
 * 搜索区服
 *
 * @author Grafie
 * @since 1.0.0
 */
@Data
public class ServerMasterData {
    @JsonProperty("id")
    private String id;

    @JsonProperty("zone")
    private String zone;

    @JsonProperty("type")
    private String type;

    @JsonProperty("name")
    private String name;

    @JsonProperty("event")
    private String event;

    @JsonProperty("center")
    private String center;

    @JsonProperty("duowan")
    private Map<String, List<Integer>> duowan;

    @JsonProperty("abbreviation")
    private List<String> abbreviation;

    @JsonProperty("subordinate")
    private List<String> subordinate;
}
