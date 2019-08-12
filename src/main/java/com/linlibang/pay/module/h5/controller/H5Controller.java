package com.linlibang.pay.module.h5.controller;

import com.linlibang.common.api.BaseResponse;
import com.linlibang.pay.module.h5.H5Util;
import com.linlibang.pay.module.h5.entity.PayReuquestPo;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Base64;

@RestController
@RequestMapping("/h5")
public class H5Controller {

    @Autowired
    private H5Util h5Util;

    @ApiOperation("支付宝H5支付")
    @PostMapping("/payByTrade")
    public BaseResponse payByTrade(@RequestBody PayReuquestPo payReuquestPo){
        h5Util.pay(payReuquestPo);
        return new BaseResponse();
    }

}
