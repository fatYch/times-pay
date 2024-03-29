package com.linlibang.pay.module.c2b.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("账单支付信息")
@Data
public class BillPaymentPo {
    @ApiModelProperty("商户订单号")
    private String merOrderId;
    @ApiModelProperty("账单业务类型")
    private String billBizType;
    @ApiModelProperty("交易参考号")
    private String paySeqId;
    @ApiModelProperty("账单流水总金额")
    private int totalAmount;
    @ApiModelProperty("实付金额")
    private int buyerPayAmount;
    @ApiModelProperty("开票金额")
    private int invoiceAmount;
    @ApiModelProperty("折扣金额")
    private int discountAmount;
    @ApiModelProperty("钱包折扣金额")
    private int couponAmount;
    @ApiModelProperty("买家id")
    private String buyerId;
    @ApiModelProperty("买家用户名")
    private String buyerUsername;
    @ApiModelProperty("支付详情")
    private String payDetail;
    @ApiModelProperty("支付时间(yyyy-MM-dd HH:mm:ss)")
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
