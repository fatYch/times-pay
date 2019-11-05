package com.linlibang.pay.module.h5;

import com.alibaba.fastjson.JSON;
import com.linlibang.common.lang.DateUtils;
import com.linlibang.pay.module.c2b.entity.base.UnionBaseRequest;
import com.linlibang.pay.module.h5.entity.PayRequestPo;
import com.linlibang.pay.module.unionPay.UnionPayUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

/**
 * H5页面支付工具类
 *
 * @author 肥美的洪洪哥
 * @version 2019-08-11
 */
@Component
public class H5Util {
    /**
     * 域名
     */
    @Value("${unionPay.domainName}")
    private String domainName;
    /**
     * 业务编码
     */
    @Value("${unionPay.api.h5.INST_MID}")
    private String instMid;

    /**
     * 支付宝H5
     */
    @Value("${unionPay.api.h5.payByTrade}")
    private String payByTradeApi;
    /**
     * 银联在线无卡
     */
    @Value("${unionPay.api.h5.payByQmf}")
    private String payByQmfApi;
    /**
     * 银联云闪付
     */
    @Value("${unionPay.api.h5.payByUac}")
    private String payByUacApi;

    @Autowired
    private UnionPayUtil unionPayUtil;

    /**
     * 支付宝H5
     *
     * @param payRequestPo
     */
    public void pay(PayRequestPo payRequestPo) {
        initParam(payRequestPo);
        String result = unionPayUtil.sendGet(domainName + payByTradeApi, JSON.toJSONString(payRequestPo));
    }


    /**
     * 参数初始化
     *
     * @param unionBaseRequest
     */
    private void initParam(UnionBaseRequest unionBaseRequest) {
        unionBaseRequest.setMid(unionPayUtil.getMid());
        unionBaseRequest.setTid(unionPayUtil.getTid());
        unionBaseRequest.setInstMid(instMid);
        unionBaseRequest.setRequestTimestamp(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
    }
}
