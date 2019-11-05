package com.linlibang.pay.module.b2c.entity.po;

import com.linlibang.pay.module.b2c.entity.base.BaseBToCResponsePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("交易退款请求返回")
@Data
public class RefundResponsePo extends BaseBToCResponsePo {

    @ApiModelProperty("交易时间（hhmmss）")
    private String transactionTime;
    @ApiModelProperty("交易日期（yyyyMMdd）")
    private String transactionDateWithYear;
    @ApiModelProperty("结算日期(yyyyMMdd)")
    private String settlementDateWithYear;
    @ApiModelProperty("检索参考号")
    private String retrievalRefNum;
    @ApiModelProperty("第三方名称")
    private String thirdPartyName;
    @ApiModelProperty("卡类型")
    private String cardAttr;
    @ApiModelProperty("实际退款金额")
    private int refundInvoiceAmount;

}
