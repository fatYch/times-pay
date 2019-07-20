package com.linlibang.pay.module.cToB;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;
import com.linlibang.common.lang.DateUtils;
import com.linlibang.common.lang.StringUtils;
import com.linlibang.pay.module.base.UnionBaseRequest;
import com.linlibang.pay.module.cToB.entity.po.CloseQrCodeRequestPo;
import com.linlibang.pay.module.cToB.entity.po.CloseQrCodeResponsePo;
import com.linlibang.pay.module.cToB.entity.po.GetQrCodeRequestPo;
import com.linlibang.pay.module.cToB.entity.po.GetQrCodeResponsePo;
import com.linlibang.pay.module.unionPay.UnionPayUtil;
import com.linlibang.pay.utils.HttpUtil;
import io.swagger.annotations.Api;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.HashMap;

/**
 * C扫B
 */
@Log4j
@Component
@ConfigurationProperties(prefix = "unionPay.api.cToB")
public class CToBUtil {

    /**
     * 业务类型
     */
    private final String INST_MID = "QRPAYDEFAULT";
    /**
     * 银联域名
     */
    @Value("${unionPay.domainName}")
    private  String domainName;
    /**
     * 获取付款二维码API
     */
    @Value("${unionPay.api.cToB.getQrCode}")
    private  String getQrCodeApi;
    /**
     * 关闭付款二维码API
     */
    @Value("${unionPay.api.cToB.closeQrCode}")
    private  String closeQrCodeApi;

    @Autowired
    private UnionPayUtil unionPayUtil;



    /**
     * 获取付款二维码
     * @param getQrCodeRequestPo
     * @return
     */
    public  BaseResponse getQrCode(GetQrCodeRequestPo getQrCodeRequestPo) {
        initParam(getQrCodeRequestPo);
        //设置能有多少个手机扫码，SINGLE只能一个，MULTIPLE能多个
        getQrCodeRequestPo.setWalletOption("MULTIPLE");
        //本支付系统的订单编号，区分其他业务系统的订单编号
        getQrCodeRequestPo.setBillDate(DateUtils.getDate("yyyy-MM-dd"));
        getQrCodeRequestPo.setBillNo(unionPayUtil.getBillNo());
        //根据订单编号作为二维码id，如有需要可根据二维码id关闭付款二维码
        getQrCodeRequestPo.setQrCodeId(getQrCodeRequestPo.getBillNo());
        //TODO:插入数据库
        String paramBody = JSON.toJSONString(getQrCodeRequestPo);
        String result = unionPayUtil.sendPost(getQrCodeApi,paramBody);
        log.info("获取二维码返回:" + result);
        if (StringUtils.isNotBlank(result)) {
            JSONObject jsonObject = JSON.parseObject(result);
            GetQrCodeResponsePo getQrCodeResponsePo = jsonObject.toJavaObject(GetQrCodeResponsePo.class);
            //TODO:插入数据库
            //TODO:校验是否成功
            String qrCodeUrl = getQrCodeResponsePo.getBillQRCode();
            if (StringUtils.isNotBlank(qrCodeUrl)) {
                return new BaseResponse(qrCodeUrl);
            }
        }
        return new BaseResponse(ApiConstant.FAIL, "获取二维码失败,请稍后再试", null);
    }

    /**
     * 关闭付款二维码
     * @param qrCodeId
     * @return
     */
    public boolean closeQrCode(String qrCodeId){
        CloseQrCodeRequestPo closeQrCodeRequestPo = new CloseQrCodeRequestPo();
        initParam(closeQrCodeRequestPo);
        closeQrCodeRequestPo.setQrCodeId(qrCodeId);
        log.info(JSON.toJSONString(closeQrCodeRequestPo));
        String result = unionPayUtil.sendPost(closeQrCodeApi,JSON.toJSONString(closeQrCodeRequestPo));
        log.info("关闭二维码返回:" + result);
        if(StringUtils.isNotBlank(result)){
            CloseQrCodeResponsePo closeQrCodeResponsePo = JSON.parseObject(result)
                    .toJavaObject(CloseQrCodeResponsePo.class);
            //TODO:校验是否成功
            //TODO:业务逻辑
            return true;
        }
        return false;
    }

    /**
     * 请求参数初始化
     * @param unionBaseRequest
     */
    private void initParam(UnionBaseRequest unionBaseRequest){
        unionBaseRequest.setTid(unionPayUtil.getTid());
        unionBaseRequest.setMid(unionPayUtil.getMid());
        unionBaseRequest.setRequestTimestamp(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        unionBaseRequest.setInstMid(INST_MID);
    }
}
