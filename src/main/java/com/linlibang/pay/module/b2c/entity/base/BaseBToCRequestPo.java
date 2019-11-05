package com.linlibang.pay.module.b2c.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 肥美的洪洪哥
 * @version 2019-11-03
 */
@ApiModel("B扫C请求通用参数")
@Data
public class BaseBToCRequestPo {

    @ApiModelProperty("商户号")
    private String merchantCode;
    @ApiModelProperty("终端号")
    private String terminalCode;
    @ApiModelProperty("商户冗余信息")
    private String srcReserved;

}
