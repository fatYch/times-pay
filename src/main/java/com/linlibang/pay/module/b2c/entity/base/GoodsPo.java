package com.linlibang.pay.module.b2c.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("商品类")
@Data
public class GoodsPo {
    @ApiModelProperty("商品id")
    private String goodsId;
    @ApiModelProperty("商品名称")
    private String goodsName;
    @ApiModelProperty("商品数量")
    private String quantity;
    @ApiModelProperty("商品单价（分）")
    private String price;
    @ApiModelProperty("商品分类")
    private String goodsCategory;
    @ApiModelProperty("商品说明")
    private String body;
    @ApiModelProperty("商品折扣")
    private int discount;


}
