package com.linlibang.pay.module.c2b.entity.po;

import com.linlibang.pay.module.c2b.entity.base.SubOrdersPo;
import com.linlibang.pay.module.c2b.entity.base.UnionBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("退款请求体")
@Data
public class RefundBillRequestPo extends UnionBaseRequest {

    @ApiModelProperty("账单号")
    private String billNo;
    @ApiModelProperty("账单时间(yyyy-MM-dd)")
    private String billDate;
    @ApiModelProperty("退款订单号")
    private String refundOrderId;
    @ApiModelProperty("要退货的金额")
    private int refundAmount;
    @ApiModelProperty("平台商户退款分账金额")
    private int platformAmount;
    @ApiModelProperty("子订单信息")
    private List<SubOrdersPo> subOrders;
    @ApiModelProperty("退货说明")
    private String refundDesc;

}
