package com.linlibang.pay.module.cToB.controller;

import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;
import com.linlibang.pay.module.cToB.CToBUtil;
import com.linlibang.pay.module.cToB.entity.po.GetQrCodeRequestPo;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@Api(tags = "C扫B",description = "获取付款二维码、关闭付款二维码")
@RestController
@Log4j
@RequestMapping("/cToB")
public class CToBController {

    @Autowired
    private CToBUtil cToBUtil;

    @ApiOperation("获取付款二维码")
    @PostMapping("/getQrCode")
    public BaseResponse getQrCode(@RequestBody GetQrCodeRequestPo getQrcodeRequestPo){
        return cToBUtil.getQrCode(getQrcodeRequestPo);
    }

    @ApiOperation("关闭付款二维码")
    @PostMapping("/closeQrCode/{orderNo}")
    public BaseResponse close(@PathVariable("orderNo")String orderNo){
        boolean result = cToBUtil.closeQrCode(orderNo);
        return result ? new BaseResponse():new BaseResponse(ApiConstant.FAIL,"关闭二维码失败!",null);
    }




}
