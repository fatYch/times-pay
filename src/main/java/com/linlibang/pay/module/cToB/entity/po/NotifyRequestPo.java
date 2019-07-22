package com.linlibang.pay.module.cToB.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("异步通知请求体")
@Data
public class NotifyRequestPo {
    @ApiModelProperty("商户号")
    private String mid;
    @ApiModelProperty("终端号")
    private String tid;
    @ApiModelProperty("业务类型")
    private String instMid;
    @ApiModelProperty("账单号")
    private String billNo;
    @ApiModelProperty("账单二维码")
    private String billQRCode;
    @ApiModelProperty("订单时间")
    private String billDate;
    @ApiModelProperty("账单创建时间")
    private String createTime;
    @ApiModelProperty("账单状态")
    private String billStatus;
    @ApiModelProperty("账单描述")
    private String billDesc;
    @ApiModelProperty("账单总金额")
    private int totalAmount;
    @ApiModelProperty("会员号")
    private String memberId;
    @ApiModelProperty("桌号、柜台号、房号")
    private String counterNo;
    @ApiModelProperty("商户名称")
    private String merName;
    @ApiModelProperty("付款附言")
    private String memo;
    @ApiModelProperty("支付通知id")
    private String notifyId;
    @ApiModelProperty("担保状态")
    private String secureStatus;
    @ApiModelProperty("担保完成金额")
    private int completeAmount;
    @ApiModelProperty("账单支付信息")
    private List<BillPaymentPo> billPayment;
    @ApiModelProperty("签名")
    private String sign;
}
