package com.linlibang.pay.module.c2b.entity.po;

import com.linlibang.pay.module.c2b.entity.base.UnionBaseRequest;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("关闭二维码请求体")
@Data
public class CloseQrCodeRequestPo extends UnionBaseRequest {

    @ApiModelProperty("二维码id")
    private String qrCodeId;
    @ApiModelProperty("是否关闭二维码的同时发起退货")
    private boolean attachRefund;

}
