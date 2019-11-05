package com.linlibang.pay.module.b2c.entity.form;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.hibernate.validator.constraints.NotBlank;

import javax.validation.constraints.Min;

/**
 * @author 肥美的洪洪哥
 * @version 2019-11-04
 */
@ApiModel("支付请求表单")
@Data
public class PayForm {

    @ApiModelProperty("交易金额(分)")
    @Min(value = 1 , message = "请输入大于0的金额！")
    private int transactionAmount;
    @ApiModelProperty("支付码")
    @NotBlank
    private String payCode;
    @ApiModelProperty("原系统订单号")
    @NotBlank
    private String sourceOrderId;
    @ApiModelProperty("备注")
    private String remark;

}
