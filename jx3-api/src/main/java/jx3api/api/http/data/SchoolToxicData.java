package jx3api.api.http.data;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @author Field
 * @date 2024-06-21 15:35
 **/
@NoArgsConstructor
@Data
public class SchoolToxicData {

    @JsonProperty("code")
    private Integer code;
    @JsonProperty("msg")
    private String msg;
    @JsonProperty("data")
    private List<DataDTO> data;
    @JsonProperty("time")
    private Integer time;

    @NoArgsConstructor
    @Data
    public static class DataDTO {
        @JsonProperty("id")
        private Integer id;
        @JsonProperty("class")
        private String classX;
        @JsonProperty("name")
        private String name;
        @JsonProperty("toxic")
        private String toxic;
        @JsonProperty("attribute")
        private String attribute;
        @JsonProperty("status")
        private Integer status;
        @JsonProperty("datetime")
        private String datetime;
    }
}
