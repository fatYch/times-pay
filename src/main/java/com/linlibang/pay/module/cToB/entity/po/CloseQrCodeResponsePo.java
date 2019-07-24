package com.linlibang.pay.module.cToB.entity.po;

import com.linlibang.pay.module.base.UnionBaseResponse;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("关闭付款二维码请求返回体")
@Data
public class CloseQrCodeResponsePo extends UnionBaseResponse {

    @ApiModelProperty("二维码id")
    private String qrCodeId;

}
