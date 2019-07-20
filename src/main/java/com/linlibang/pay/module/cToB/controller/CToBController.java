package com.linlibang.pay.module.cToB.controller;

import com.linlibang.common.api.BaseResponse;
import com.linlibang.pay.module.cToB.CToBUtil;
import com.linlibang.pay.module.cToB.entity.po.GetQrcodeRequestPo;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/cToB")
public class CToBController {

    @ApiOperation("获取付款二维码")
    @PostMapping("/getQrcode")
    public BaseResponse getQrcode(@RequestBody GetQrcodeRequestPo getQrcodeRequestPo){
        return CToBUtil.getQrcode(getQrcodeRequestPo);
    }
}
