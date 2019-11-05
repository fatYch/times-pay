package com.linlibang.pay.module.c2b.entity.po;

import com.linlibang.pay.module.c2b.entity.base.UnionBaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("查询订单状态返回体")
@Data
public class QueryBillResponsePo extends UnionBaseResponse {

    @ApiModelProperty("账单号")
    private String billNo;
    @ApiModelProperty("订单时间(yyyy-MM-dd)")
    private String billDate;
    @ApiModelProperty("账单创建时间(yyyy-MM-dd HH:mm:ss)")
    private String createTime;
    @ApiModelProperty("账单状态")
    private String billStatus;
    @ApiModelProperty("账单描述")
    private String billDesc;
    @ApiModelProperty("账单总金额")
    private int totalAmount;
    @ApiModelProperty("账单二维码")
    private String billQRCode;
    @ApiModelProperty("会员号")
    private String memberId;
    @ApiModelProperty("桌号")
    private String counterNo;
    @ApiModelProperty("商户名称")
    private String merName;
    @ApiModelProperty("付款附言")
    private String memo;
    @ApiModelProperty("担保状态")
    private String secureStatus;
    @ApiModelProperty("担保完成金额")
    private int completeAmount;
    @ApiModelProperty("账单支付信息")
    private List<BillPaymentPo> billPayment;
    @ApiModelProperty("账单退款信息")
    private List<RefundBillPaymentPo> refundBillPayment;
    @ApiModelProperty("借贷记标识")
    private String cardAttr;

}
