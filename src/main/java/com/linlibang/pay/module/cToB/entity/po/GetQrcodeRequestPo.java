package com.linlibang.pay.module.cToB.entity.po;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取二维码请求体")
public class GetQrcodeRequestPo {

    @ApiModelProperty("消息id")
    private String msgId;
    @ApiModelProperty("报文请求时间")
    private String requestTimestamp;
    @ApiModelProperty("请求系统预留字段")
    private String srcReserve;
    @ApiModelProperty("商户号")
    private String mid;
    @ApiModelProperty("终端号")
    private String tid;
    @ApiModelProperty("业务类型,固定QRPAYDEFAULT")
    private String instMid = "QRPAYDEFAULT";
    @ApiModelProperty("账单号")
    private String billNo;
    @ApiModelProperty("账单日期(yyyy-MM-dd)")
    private String billDate;
    @ApiModelProperty("账单描述")
    private String billDesc;
    @ApiModelProperty("支付总金额")
    private int totalAmount;
    @ApiModelProperty("分账标记")
    private boolean divisionFlag;
    @ApiModelProperty("平台商户分账金额")
    private int platformAmount;
    @ApiModelProperty("商品")
    private GoodsPo goods;
    @ApiModelProperty("子订单信息")
    private SubOrdersPo subOrders;
    @ApiModelProperty("会员号")
    private String memberId;
    @ApiModelProperty("桌号、柜台号、房号")
    private String counterNo;
    @ApiModelProperty("账单过期时间")
    private String expireTime;
    @ApiModelProperty("支付结果通知地址")
    private String notifyUrl;
    @ApiModelProperty("网页跳转地址")
    private String returnUrl;
    @ApiModelProperty("二维码id")
    private String qrCodeId;
    @ApiModelProperty("系统id")
    private String systemId;
    @ApiModelProperty("担保交易标识")
    private String secureTransaction;
    @ApiModelProperty("钱包选项")
    private String walletOption;
    @ApiModelProperty("实名认证姓名")
    private String name;
    @ApiModelProperty("实名认证手机号")
    private String mobile;
    @ApiModelProperty(value = "实名认证类型")
    private String certType;
    @ApiModelProperty("实名认证证件号")
    private String certNo;
    @ApiModelProperty("是否需要实名认证")
    private String fixBuyer;
    @ApiModelProperty("是否需要限制信用卡支付")
    private String limitCreditCard;




}
