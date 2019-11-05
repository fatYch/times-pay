package com.linlibang.pay.module.b2c.entity.po;

import com.linlibang.pay.module.b2c.entity.base.BaseBToCResponsePo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("订单查询返回")
@Data
public class QueryResponsePo extends BaseBToCResponsePo {

    @ApiModelProperty("原始交易时间")
    private String originalTransactionTime;
    @ApiModelProperty("原始交易清算日期")
    private String originalSettlementDate;
    @ApiModelProperty("查询结果")
    private String queryResCode;
    @ApiModelProperty("查询结果描述")
    private String queryResDesc;
    @ApiModelProperty("原交易付款码")
    private String originalPayCode;
    @ApiModelProperty("原交易批次号")
    private String originalBatchNo;
    @ApiModelProperty("原交易流水号")
    private String originalSystemTraceNum;
    @ApiModelProperty("原检索参考号")
    private String originalRetrievalRefNum;
    @ApiModelProperty("原交易金额")
    private int originalTransactionAmount;
    @ApiModelProperty("商户订单号")
    private String merchantOrderId;
    @ApiModelProperty("银商订单号")
    private String orderId;
    @ApiModelProperty("已退货金额")
    private int refundedAmount;
    @ApiModelProperty("营销联盟优惠后交易金额")
    private int actualTransactionAmount;
    @ApiModelProperty("实际支付金额")
    private int amount;
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
    @ApiModelProperty("商户让利金额")
    private int merchantAllowance;
    @ApiModelProperty("第三方补贴金额")
    private int thirdPattyAllowance;

}
