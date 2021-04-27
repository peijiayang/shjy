package tech.binaryer.shjy.biz.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
import tech.binaryer.shjy.biz.dto.page.QueryParam;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class GoodsDto extends QueryParam  implements Serializable {
    private Integer id;

    /**
     * 所属系列
     */
    private Integer goodsSeries;

}
