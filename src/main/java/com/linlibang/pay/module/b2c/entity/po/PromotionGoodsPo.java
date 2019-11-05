package com.linlibang.pay.module.b2c.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("优惠活动的商品")
@Data
public class PromotionGoodsPo {

    @ApiModelProperty("商品编号")
    private String goodsId;
    @ApiModelProperty("单品优惠金额")
    private int discountAmount;
    @ApiModelProperty("商品数量")
    private int goodsNumber;
    @ApiModelProperty("商品价格")
    private int goodsPrice;
}
