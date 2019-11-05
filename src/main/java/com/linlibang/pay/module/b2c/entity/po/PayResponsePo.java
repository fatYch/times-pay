package com.linlibang.pay.module.b2c.entity.po;

import com.linlibang.pay.module.b2c.entity.base.BaseBToCResponsePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("支付请求返回")
@Data
public class PayResponsePo extends BaseBToCResponsePo {

    @ApiModelProperty("交易时间（格式hhmmss）")
    private String transactionTime;
    @ApiModelProperty("交易日期（格式yyyyMMdd）")
    private String transactionDateWithYear;
    @ApiModelProperty("结算日期")
    private String settlementDateWithYear;
    @ApiModelProperty("检索参考号")
    private String retrievalRefNum;
    @ApiModelProperty("订单金额")
    private int transactionAmount;
    @ApiModelProperty("营销联盟优惠后交易金额")
    private int actualTransactionAmount;
    @ApiModelProperty("实际支付金额")
    private int amount;
    @ApiModelProperty("订单号")
    private String orderId;
    @ApiModelProperty("营销联盟优惠说明")
    private String marketingAllianceDiscountInstruction;
    @ApiModelProperty("第三方优惠说明")
    private String thirdPartyDiscountInstruction;
    @ApiModelProperty("第三方名称")
    private String thirdPartyName;
    @ApiModelProperty("第三方买家id")
    private String thirdPartyBuyerId;
    @ApiModelProperty("第三方买家用户名")
    private String thirdPartyBuyerUserName;
    @ApiModelProperty("第三方订单号")
    private String thirdPartyOrderId;
    @ApiModelProperty("第三方支付信息")
    private String thirdPartyPayInformation;
    @ApiModelProperty("优惠金额（合计）")
    private int totalDiscountAmount;
    @ApiModelProperty("优惠状态")
    private String discountStatus;
    @ApiModelProperty("优惠活动活动列表")
    private List<PromotionPo> promotionList;
    @ApiModelProperty("卡类型")
    private String cardAttr;
    @ApiModelProperty("商户让利金额")
    private int merchantAllowance;
    @ApiModelProperty("第三方补贴金额")
    private int thirdPattyAllowance;





}
