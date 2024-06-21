package uk.zwfield.jx3bot.entity;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Getter;
import lombok.Setter;

import java.io.Serial;
import java.io.Serializable;

/**
 * <p>
 *
 * </p>
 *
 * @author field
 * @since 2024-06-20
 */
@Getter
@Setter
@TableName("t_data_config")
public class DataConfig implements Serializable {

    @Serial
    private static final long serialVersionUID = 1L;

    @TableId("id")
    private Integer id;

    @TableField("data_name")
    private String dataName;

    @TableField("data_value")
    private String dataValue;

    @TableField("type")
    private Integer type;
}
