package tech.binaryer.shjy.biz.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class AddGoodsDto implements Serializable {
    private List<Integer> ids;

 }
