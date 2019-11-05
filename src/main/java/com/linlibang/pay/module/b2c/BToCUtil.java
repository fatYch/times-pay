package com.linlibang.pay.module.b2c;

import com.alibaba.fastjson.JSON;
import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;
import com.linlibang.common.lang.StringUtils;
import com.linlibang.pay.module.b2c.entity.base.BaseBToCRequestPo;
import com.linlibang.pay.module.b2c.entity.form.PayForm;
import com.linlibang.pay.module.b2c.entity.po.*;
import com.linlibang.pay.module.union.UnionPayDict;
import com.linlibang.pay.module.unionPay.UnionPayUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@Log4j
public class BToCUtil extends UnionPayUtil {


    /**
     * 激活终端api
     */
    @Value("${unionPay.api.b2c.activeTerminal}")
    private String activeTerminalApi;

    /**
     * 支付api
     */
    @Value("${unionPay.api.b2c.pay}")
    private String payApi;

    /**
     * 订单查询api
     */
    @Value("${unionPay.api.b2c.query}")
    private String queryApi;

    /**
     * 支付撤销api（当天）
     */
    @Value("${unionPay.api.b2c.voidPayment}")
    private String voidPaymentApi;

    /**
     * 交易退款api（隔天，30天内）
     */
    @Value("${unionPay.api.b2c.refund}")
    private String refundApi;

    /**
     * 激活终端
     */
    public void activeTerminal(){
        ActiveTerminalRequestPo activeTerminalRequestPo = new ActiveTerminalRequestPo();
        activeTerminalRequestPo.setMerchantCode(getMid());
        activeTerminalRequestPo.setTerminalCode(getTid());
        String result = sendPost(activeTerminalApi, JSON.toJSONString(activeTerminalRequestPo));
        System.out.println(result);
    }

    /**
     * 支付
     */
    public BaseResponse pay(PayForm payForm){
        PayRequestPo payRequestPo = new PayRequestPo();
        payRequestPo.setMerchantCode(getMid());
        payRequestPo.setTerminalCode(getTid());
        payRequestPo.setTransactionCurrencyCode(UnionPayDict.CURRENCY_CODE_RMB);
        payRequestPo.setPayMode(UnionPayDict.PAY_MODE_CODESCAN);
        //补充表单数据
        payRequestPo.setMerchantOrderId(payForm.getSourceOrderId());
        payRequestPo.setTransactionAmount(payForm.getTransactionAmount());
        payRequestPo.setPayCode(payForm.getPayCode());
        payRequestPo.setMerchantRemark(payForm.getRemark());
        //TODO:业务系统的订单号保存在哪
        //请求会阻塞一段时间等待用户支付然后返回结果，若长时间未支付则会返回空，需主动查询
        String result = sendPost(payApi, JSON.toJSONString(payRequestPo));
        if (StringUtils.isBlank(result)){
            return new BaseResponse(ApiConstant.FAIL, "等待用户支付中!", null);
        }
        PayResponsePo payResponsePo = JSON.parseObject(result, PayResponsePo.class);
        log.info("支付返回:" + JSON.toJSONString(payResponsePo));
        if (UnionPayDict.ERR_CODE_SUCCESS.equals(payResponsePo.getErrCode())
                && StringUtils.isNotBlank(payResponsePo.getOrderId())){
            return new BaseResponse(ApiConstant.SUCCESS, "交易成功!", null);
        }
        return new BaseResponse(ApiConstant.FAIL, payResponsePo.getErrInfo(), null);
    }

    /**
     * 订单查询
     */
    public BaseResponse query(String sourceOrderId){
        QueryRequestPo queryRequestPo = new QueryRequestPo();
        initParam(queryRequestPo);
        queryRequestPo.setMerchantOrderId(sourceOrderId);
        String result = sendPost(queryApi, JSON.toJSONString(queryRequestPo));
        QueryResponsePo queryResponsePo = JSON.parseObject(result, QueryResponsePo.class);
        log.info("订单查询结果:" + JSON.toJSONString(queryResponsePo));
        if (UnionPayDict.QUERY_RES_SUCCESS.equals(queryResponsePo.getQueryResCode())){
            //TODO:交易成功
        }
        return new BaseResponse(queryResponsePo);
    }

    /**
     * 支付撤销
     * @param sourceOrderId
     * @return
     */
    public BaseResponse voidPayment(String sourceOrderId){
        VoidPaymentRequestPo voidPaymentRequestPo = new VoidPaymentRequestPo();
        initParam(voidPaymentRequestPo);
        voidPaymentRequestPo.setMerchantOrderId(sourceOrderId);
        String result = sendPost(voidPaymentApi, JSON.toJSONString(voidPaymentRequestPo));
        VoidPaymentResponsePo voidPaymentResponsePo = JSON.parseObject(result, VoidPaymentResponsePo.class);
        log.info("支付撤销返回:" + JSON.toJSONString(voidPaymentResponsePo));
        if (UnionPayDict.ERR_CODE_SUCCESS.equals(voidPaymentResponsePo.getErrCode())){
            return new BaseResponse(ApiConstant.SUCCESS, "您的支付撤销请求已收到，请稍后！", null);
        }
        return new BaseResponse(ApiConstant.FAIL, voidPaymentResponsePo.getErrInfo(), null);
    }

    /**
     * 交易退款
     */
    public BaseResponse refund(String sourceOrderId){
        RefundRequestPo refundRequestPo = new RefundRequestPo();
        initParam(refundRequestPo);
        refundRequestPo.setOriginalOrderId(sourceOrderId);
        //refundRequestPo.setMerchantOrderId("");
        //TODO:查询退款金额
        refundRequestPo.setRefundRequestId("1");
        refundRequestPo.setTransactionAmount(1);
        String result = sendPost(refundApi, JSON.toJSONString(refundRequestPo));
        RefundResponsePo refundResponsePo = JSON.parseObject(result, RefundResponsePo.class);
        log.info("交易退款返回:" + JSON.toJSONString(refundResponsePo));
        return new BaseResponse(refundResponsePo);
    }


    /**
     * 初始化请求参数
     * @param baseBToCRequestPo
     */
    private void initParam(BaseBToCRequestPo baseBToCRequestPo){
        baseBToCRequestPo.setMerchantCode(getMid());
        baseBToCRequestPo.setTerminalCode(getTid());
    }





}
