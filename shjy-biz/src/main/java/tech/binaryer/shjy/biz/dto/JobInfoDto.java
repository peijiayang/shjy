package tech.binaryer.shjy.biz.dto;


import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
public class JobInfoDto {
    private Integer jobId;

    private String jobName;

    private String jobContent;

    private String jobParam;

    private String realName;

}
