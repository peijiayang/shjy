package tech.binaryer.shjy.biz.entity;

import java.math.BigDecimal;
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
@TableName("shjy_goods")
public class ShjyGoodsEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 商品id
     */
    @TableId(value = "id", type = IdType.AUTO)
    private Integer id;

    /**
     * 商品名称
     */
    private String goodsName;

    /**
     * 体重/体积
     */
    private String goodsSpecifically;

    /**
     * 度数
     */
    private String goodsDegree;

    /**
     * 建议零售价
     */
    private BigDecimal goodsGuidingPrice;

    /**
     * 团购价
     */
    private BigDecimal goodsGroupPrice;

    /**
     * 所属系列
     */
    private Integer goodsSeries;

    /**
     * 图片
     */
    private String goodsPictures;

    /**
     * 备注
     */
    private String goodsRemark;


}
