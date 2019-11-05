package com.linlibang.pay.module.c2b.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.Min;

@ApiModel("获取二维码对外表单")
@Data
public class GetQrCodeForm {

    @ApiModelProperty("支付总金额（分）")
    @Min(value = 1, message = "请输入金额大于1分的数值")
    private int totalAmount;
    @ApiModelProperty("订单描述（业务系统的订单号）")
    private String billDesc;
    @ApiModelProperty("系统来源")
    private String srcReserve;

}
