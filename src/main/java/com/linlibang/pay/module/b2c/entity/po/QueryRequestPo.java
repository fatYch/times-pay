package com.linlibang.pay.module.b2c.entity.po;

import com.linlibang.pay.module.b2c.entity.base.BaseBToCRequestPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("订单查询请求")
@Data
public class QueryRequestPo extends BaseBToCRequestPo {

    @ApiModelProperty("商户订单号")
    private String merchantOrderId;
    @ApiModelProperty("银商订单号")
    private String originalOrderId;

}
