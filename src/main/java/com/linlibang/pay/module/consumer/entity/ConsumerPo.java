package com.linlibang.pay.module.consumer.entity;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * 接入系统信息
 *
 * @author yaocanhong
 * @version 2019-08-07
 */

@Data
@ApiModel("接入系统")
public class ConsumerPo {

    @ApiModelProperty("系统名称")
    private String name;
    @ApiModelProperty("来源标识")
    private String source;
    @ApiModelProperty("秘钥")
    private String key;
    @ApiModelProperty("异步成功回调URL")
    private String notifyUrl;
    @ApiModelProperty("状态（0可用1不可用）")
    private String status;

}
