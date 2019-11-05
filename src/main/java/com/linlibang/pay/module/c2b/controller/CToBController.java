package com.linlibang.pay.module.c2b.controller;

import com.alibaba.fastjson.JSON;
import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;
import com.linlibang.pay.module.c2b.CToBUtil;
import com.linlibang.pay.module.c2b.entity.form.GetQrCodeForm;
import com.linlibang.pay.module.c2b.entity.po.NotifyRequestPo;
import com.linlibang.pay.module.union.UnionPayDict;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiModelProperty;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;


@Api(tags = "C扫B", description = "获取付款二维码、关闭付款二维码")
@RestController
@Log4j
@RequestMapping("/cToB")
public class CToBController {

    @Autowired
    private CToBUtil cToBUtil;

    @ApiOperation("获取付款二维码")
    @PostMapping("/getQrCode")
    public BaseResponse getQrCode(@RequestBody GetQrCodeForm getQrCodeForm) {
        return cToBUtil.getQrCode(getQrCodeForm);
    }

    @ApiOperation("关闭付款二维码")
    @PostMapping("/closeQrCode/{billNo}")
    public BaseResponse close(@PathVariable("billNo") String billNo) {
        boolean result = cToBUtil.closeQrCode(billNo);
        return result ? new BaseResponse() : new BaseResponse(ApiConstant.FAIL, "关闭二维码失败!", null);
    }

    @ApiOperation("查询订单状态")
    @GetMapping("/queryBill/{billNo}")
    public BaseResponse queryBill(@PathVariable("billNo") String billNo) {
        return cToBUtil.queryBill(billNo, "2019-07-22");
    }

    @ApiModelProperty("退款")
    @PostMapping("/refundBill/{billNo}")
    public BaseResponse refundBill(@PathVariable("billNo") String billNo) {
        cToBUtil.refundBill(billNo);
        return new BaseResponse();
    }

    @ApiModelProperty("支付结果通知回调")
    @PostMapping("/notify")
    public String notify(@ApiParam("消息通知参数2") NotifyRequestPo notifyRequestPo, @ApiParam(hidden = true) HttpServletRequest request) {
        log.info("支付结果通知参数:" + JSON.toJSONString(notifyRequestPo));
        if (! cToBUtil.checkRequestSign(request)){
            //TODO:主动查询验证
            return "请求签名验证失败!";
        }
        if (UnionPayDict.BILL_STATUS_PAID.equals(notifyRequestPo.getBillStatus())){
            //TODO:订单已经支付成功
        }
        return "SUCCESS";
    }




}
