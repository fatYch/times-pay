package com.linlibang.pay.module.b2c.entity.base;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

@ApiModel("B扫C请求返回通用参数")
@Data
public class BaseBToCResponsePo {

    @ApiModelProperty("错误代码")
    private String errCode;
    @ApiModelProperty("错误说明")
    private String errInfo;

}
