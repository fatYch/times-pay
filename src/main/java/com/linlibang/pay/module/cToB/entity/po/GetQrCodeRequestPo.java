package com.linlibang.pay.module.cToB.entity.po;

import com.linlibang.pay.module.base.UnionBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@Data
@ApiModel("获取二维码请求体")
public class GetQrCodeRequestPo extends UnionBaseRequest {

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
    private List<GoodsPo> goods;
    @ApiModelProperty("子订单信息")
    private List<SubOrdersPo> subOrders;
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
