package com.linlibang.pay.module.c2b.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取请求二维码返回体")
public class GetQrCodeResponsePo {

    @ApiModelProperty("账单号")
    private String billNo;
    @ApiModelProperty("账单日期")
    private String billDate;
    @ApiModelProperty("账单二维码")
    private String billQRCode;

}
