package com.linlibang.pay.module.cToB;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;
import com.linlibang.common.lang.DateUtils;
import com.linlibang.common.lang.StringUtils;
import com.linlibang.pay.module.cToB.entity.po.GetQrcodeRequestPo;
import com.linlibang.pay.module.cToB.entity.po.GetQrcodeResponsePo;
import com.linlibang.pay.module.unionPay.UnionPayUtil;
import com.linlibang.pay.utils.HttpUtil;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * C扫B
 */
@Log4j
@Component
@ConfigurationProperties(prefix = "unionPay.api.cToB")
public class CToBUtil {
    private static String getQrcodeApi;
    private static String domianName;

    @Value("${unionPay.api.cToB.getQrcode}")
    public void setGetQrcodeApi(String getQrcodeApi){
        CToBUtil.getQrcodeApi =  getQrcodeApi;
    }

    @Value("${unionPay.domianName}")
    public void setDomianName(String domianName){
        CToBUtil.domianName = domianName;
    }

    /**
     * 获取付款二维码
     *
     * @param getQrcodeRequestPo
     * @return
     */
    public static BaseResponse getQrcode(GetQrcodeRequestPo getQrcodeRequestPo) {
        getQrcodeRequestPo.setMid(UnionPayUtil.getMid());
        getQrcodeRequestPo.setTid(UnionPayUtil.getTid());
        //设置能有多少个手机扫码，SINGLE只能一个，MULTIPLE能多个
        getQrcodeRequestPo.setWalletOption("MULTIPLE");
        //本支付系统的订单编号，区分其他业务系统的订单编号
        getQrcodeRequestPo.setBillDate(DateUtils.getDate("yyyy-MM-dd"));
        getQrcodeRequestPo.setBillNo(UnionPayUtil.getBiilNo());
        getQrcodeRequestPo.setRequestTimestamp(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        String paramBody = JSON.toJSONString(getQrcodeRequestPo);
        log.info("获取二维码请求参数:" + paramBody);
        String autjorization = UnionPayUtil.getOpenBodySign(paramBody);
        HashMap headMap = Maps.newHashMap();
        headMap.put("Authorization", autjorization);
        //TODO:插入数据库
        String result;
        try {
            result = HttpUtil.post(domianName + getQrcodeApi, paramBody, headMap);
        } catch (IOException e) {
            log.error("获取二维码请求出现错误:", e);
            return new BaseResponse(ApiConstant.FAIL, "网络出现点问题，请稍后再试!", null);
        }
        log.info("获取二维码返回:" + result);
        if (StringUtils.isNotBlank(result)) {
            JSONObject jsonObject =  JSON.parseObject(result);
            GetQrcodeResponsePo getQrcodeResponsePo = jsonObject.toJavaObject(GetQrcodeResponsePo.class);
            //TODO:插入数据库
            String qrCodeUrl = getQrcodeResponsePo.getBillQRCode();
            if (StringUtils.isNotBlank(qrCodeUrl)) {
                return new BaseResponse(qrCodeUrl);
            }

        }
        return new BaseResponse(ApiConstant.FAIL, "获取二维码失败,请联系管理员", null);
    }
}
