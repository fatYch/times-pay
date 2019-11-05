package com.linlibang.pay.module.b2c.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("优惠活动")
@Data
public class PromotionPo {

    @ApiModelProperty("渠道方")
    private String channelName;
    @ApiModelProperty("优惠流水id")
    private String discountId;
    @ApiModelProperty("活动编号")
    private String eventNo;
    @ApiModelProperty("活动优惠金额")
    private int eventDiscountAmount;
    @ApiModelProperty("单品列表")
    private List<PromotionGoodsPo> goodsList;

}
