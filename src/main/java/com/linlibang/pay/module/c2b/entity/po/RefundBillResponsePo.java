package com.linlibang.pay.module.c2b.entity.po;

import com.linlibang.pay.module.c2b.entity.base.UnionBaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("退款请求返回体")
@Data
public class RefundBillResponsePo extends UnionBaseResponse {

    @ApiModelProperty("账单号")
    private String billNo;
    @ApiModelProperty("订单时间")
    private String billDate;
    @ApiModelProperty("账单状态")
    private String billStatus;
    @ApiModelProperty("账单二维码")
    private String billQRCode;
    @ApiModelProperty("原订单商户订单号")
    private String merOrderId;
    @ApiModelProperty("退货订单号")
    private String refundOrderId;
    @ApiModelProperty("目标系统退货订单号")
    private String refundTargetOrder;
    @ApiModelProperty("退款时间")
    private String refundPayTime;
    @ApiModelProperty("退款结果(SUCCESS成功;FAIL失败;PROCESSING处理中;UNKNOWN异常)")
    private String refundStatus;
    @ApiModelProperty("借贷记标识")
    private String cardAttr;

}
