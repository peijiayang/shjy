package tech.binaryer.shjy.biz.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class UserDto implements Serializable {


    private String  userName;

    /**
     * yonghu
     */
    private String  passWord;


}
