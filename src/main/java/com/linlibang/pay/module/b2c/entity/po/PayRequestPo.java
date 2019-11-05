package com.linlibang.pay.module.b2c.entity.po;

import com.linlibang.pay.module.b2c.entity.base.BaseBToCRequestPo;
import com.linlibang.pay.module.b2c.entity.base.GoodsPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("支付请求")
@Data
public class PayRequestPo extends BaseBToCRequestPo {

    @ApiModelProperty("交易金额")
    private int transactionAmount;
    @ApiModelProperty("交易币种")
    private String transactionCurrencyCode;
    @ApiModelProperty("商户订单号")
    private String merchantOrderId;
    @ApiModelProperty("商户备注")
    private String merchantRemark;
    @ApiModelProperty("支付方式（见字典PAY_MODE_**）")
    private String payMode;
    @ApiModelProperty("支付码")
    private String payCode;
    @ApiModelProperty("商品信息")
    private List<GoodsPo> goods;
    @ApiModelProperty("门店号")
    private String storeId;
    @ApiModelProperty("是否限制使用信用卡")
    private boolean limitCreditCard;
    @ApiModelProperty("操作员编号")
    private String operatorId;
    @ApiModelProperty("业务标识")
    private String bizIdentifier;
    @ApiModelProperty("商品标识")
    private String goodsTag;





}
