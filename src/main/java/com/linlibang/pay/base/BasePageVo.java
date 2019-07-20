package com.linlibang.pay.base;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

/**
 * Created by huangzihao1 on 2018/9/17.
 */
@Data
public class BasePageVo {

    @ApiModelProperty(value = "第几页,默认第一页")
    protected int pageNum=1;//第几页,默认第一页

    @ApiModelProperty(value = "每页数量,默认一页10条")
    protected int pageSize=10;//每页数量,默认一页10条


}
