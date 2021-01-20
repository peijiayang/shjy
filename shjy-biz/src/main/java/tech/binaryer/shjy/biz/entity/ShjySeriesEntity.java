package tech.binaryer.shjy.biz.entity;

import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.io.Serializable;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

/**
 * <p>
 * 
 * </p>
 *
 * @author peijiayang
 * @since 2021-01-19
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@TableName("shjy_series")
public class ShjySeriesEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    private String seriesName;

    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;


}