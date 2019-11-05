package com.linlibang.pay.module.b2c.entity.po;

import com.linlibang.pay.module.b2c.entity.base.BaseBToCRequestPo;
import com.linlibang.pay.module.b2c.entity.base.GoodsPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

@ApiModel("交易退款请求")
@Data
public class RefundRequestPo extends BaseBToCRequestPo {

    @ApiModelProperty("商户订单号")
    private String merchantOrderId;
    @ApiModelProperty("银商订单号")
    private String originalOrderId;
    @ApiModelProperty("退款请求标识")
    private String refundRequestId;
    @ApiModelProperty("交易金额（退货金额）")
    private int transactionAmount;
    @ApiModelProperty("是否限制跨终端")
    private boolean limitCrossDevice;
    @ApiModelProperty("商品信息")
    private List<GoodsPo> goods;
    @ApiModelProperty("门店号")
    private String storeId;
    @ApiModelProperty("操作员编号")
    private String operatorId;
    @ApiModelProperty("业务标识")
    private String bizIdentifier;



}

