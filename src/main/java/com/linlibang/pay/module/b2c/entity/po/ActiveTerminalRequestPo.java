package com.linlibang.pay.module.b2c.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * @author 肥美的洪洪哥
 * @version 2019-11-03
 */
@ApiModel("激活终端请求")
@Data
public class ActiveTerminalRequestPo {

    @ApiModelProperty("商户号")
    private String merchantCode;
    @ApiModelProperty("终端号")
    private String terminalCode;

}
