package com.linlibang.pay.module.b2c.entity.po;

import com.linlibang.pay.module.b2c.entity.base.BaseBToCRequestPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("支付撤销请求")
@Data
public class VoidPaymentRequestPo extends BaseBToCRequestPo {

    @ApiModelProperty("商户订单号")
    private String merchantOrderId;
    @ApiModelProperty("银商订单号")
    private String originalOrderId;
    @ApiModelProperty("是否限制跨终端")
    private String limitCrossDevice;
    @ApiModelProperty("业务标识")
    private String bizIdentifier;

}
