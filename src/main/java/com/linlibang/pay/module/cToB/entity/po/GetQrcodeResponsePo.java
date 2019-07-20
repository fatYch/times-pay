package com.linlibang.pay.module.cToB.entity.po;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@Data
@ApiModel("获取请求二维码返回体")
public class GetQrcodeResponsePo {
    @ApiModelProperty("平台错误码")
    private String errCode;
    @ApiModelProperty("平台错误信息")
    private String errMsg;
    @ApiModelProperty("消息id")
    private String msgId;
    @ApiModelProperty("报文应答时间(yyyy-MM-dd HH:mm:ss)")
    private String responseTimeStamp;
    @ApiModelProperty("请求系统预留字段")
    private String srcReserve;
    @ApiModelProperty("商户号")
    private String mid;
    @ApiModelProperty("终端号")
    private String tid;
    @ApiModelProperty("业务类型")
    private String instMid;
    @ApiModelProperty("账单号")
    private String billNo;
    @ApiModelProperty("账单日期")
    private String billDate;
    @ApiModelProperty("账单二维码")
    private String billQRCode;
    @ApiModelProperty("系统id")
    private String systemId;
}
