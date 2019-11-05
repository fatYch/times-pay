package com.linlibang.pay.module.b2c.controller;

import com.linlibang.common.api.BaseResponse;
import com.linlibang.pay.module.b2c.BToCUtil;
import com.linlibang.pay.module.b2c.entity.form.PayForm;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

@Api(tags = "B扫C")
@RestController
@RequestMapping("/BToC")
public class BToCController {

    @Autowired
    private BToCUtil bToCUtil;

    @ApiOperation("激活终端")
    @GetMapping("/activeTerminal")
    public BaseResponse activeTerminal(){
        bToCUtil.activeTerminal();
        return new BaseResponse();
    }

    @ApiOperation("支付")
    @PostMapping("/pay")
    public BaseResponse pay(@ApiParam("支付参数") @Validated PayForm payForm){
        return bToCUtil.pay(payForm);
    }

    @ApiOperation("订单查询")
    @PostMapping("/query/{sourceOrderId}")
    public BaseResponse query(@PathVariable("sourceOrderId")String sourceOrderId){
        return bToCUtil.query(sourceOrderId);
    }

    @ApiOperation("交易退款")
    @PostMapping("/refund")
    public BaseResponse refund(@RequestParam @ApiParam("业务系统订单号")String sourceOrderId){
        return bToCUtil.refund(sourceOrderId);
    }

    @ApiOperation("支付撤销（当天）")
    @PostMapping("/voidPayment")
    public BaseResponse voidPayment(@RequestParam @ApiParam("业务系统订单号")String sourceOrderId){
        return bToCUtil.voidPayment(sourceOrderId);
    }
}
