package com.linlibang.pay.module.c2b.entity.po;

import com.linlibang.pay.module.c2b.entity.base.UnionBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("查询订单请求体")
@Data
public class QueryBillRequestPo extends UnionBaseRequest {

    @ApiModelProperty("账单号")
    private String billNo;
    @ApiModelProperty("退款订单号")
    private String refundOrderId;
    @ApiModelProperty("订单日期(yyyy-MM-dd)")
    private String billDate;

}
