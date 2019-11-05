package com.linlibang.pay.module.c2b.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("银联请求基本体")
@Data
public class UnionBaseRequest {

    @ApiModelProperty("消息id")
    private String msgId;
    @ApiModelProperty("报文请求时间")
    private String requestTimestamp;
    @ApiModelProperty("请求系统预留字段")
    private String srcReserve;
    @ApiModelProperty("商户号")
    private String mid;
    @ApiModelProperty("终端号")
    private String tid;
    @ApiModelProperty("业务类型")
    private String instMid;
    @ApiModelProperty("系统id")
    private String systemId;

}
