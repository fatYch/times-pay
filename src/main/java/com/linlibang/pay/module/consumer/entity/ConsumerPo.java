package com.linlibang.pay.module.consumer.entity;

import com.linlibang.pay.base.DataEntity;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("消费者")
public class ConsumerPo extends DataEntity {

    @ApiModelProperty("系统名称")
    private String name;
    @ApiModelProperty("来源标识")
    private String source;
    @ApiModelProperty("状态（0可用1不可用）")
    private String status;

}
