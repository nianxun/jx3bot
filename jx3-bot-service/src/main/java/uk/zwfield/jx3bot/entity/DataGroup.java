package uk.zwfield.jx3bot.entity;

import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * @author Field
 * @date 2024-06-20 16:26
 **/
@Data
@Accessors(chain = true)
@NoArgsConstructor
@AllArgsConstructor
@TableName("t_data_group")
public class DataGroup {

    @TableId(value = "id")
    private String id;

    /**
     * 群号
     */
    private Long groupNum;

    /**
     * 服务器名称
     */
    private String serverName;

    /**
     * 机器人推送总开关, 里面存放action数组
     */
    private String botSwitch;

    private Long botNum;

}
