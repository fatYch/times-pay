package com.linlibang.pay.module.cToB.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("账单退款信息")
@Data
public class RefundBillPaymentPo {
    @ApiModelProperty("商户退款单号")
    private String merOrderId;
    @ApiModelProperty("账单业务类型")
    private String billBizType;
    @ApiModelProperty("交易参考号")
    private String paySeqId;
    @ApiModelProperty("帐单流水总金额")
    private int totalAmount;
    @ApiModelProperty("实付金额")
    private int buyerPayAmount;
    @ApiModelProperty("开票金额")
    private int invoiceAmount;
    @ApiModelProperty("折扣金额")
    private int discountAmount;
    @ApiModelProperty("买家id")
    private String buyerId;
    @ApiModelProperty("买家用户名")
    private String buyerUsername;
    @ApiModelProperty("支付时间（yyyy-MM-dd HH:mm:ss）")
    private String payTime;
    @ApiModelProperty("结算时间（yyyy-MM-dd）")
    private String settleDate;
    @ApiModelProperty("交易状态")
    private String status;
    @ApiModelProperty("目标平台单号")
    private String targetOrderId;
    @ApiModelProperty("目标系统")
    private String targetSys;

}
