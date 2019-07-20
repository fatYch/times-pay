package com.linlibang.pay.module.cToB.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("子订单信息")
@Data
public class SubOrdersPo {
    @ApiModelProperty("子商户号")
    private String mid;
    @ApiModelProperty("子商户分账金额")
    private int totalAmount;
}
