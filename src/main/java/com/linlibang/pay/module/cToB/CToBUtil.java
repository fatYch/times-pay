package com.linlibang.pay.module.cToB;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.linlibang.common.api.ApiConstant;
import com.linlibang.common.api.BaseResponse;
import com.linlibang.common.codec.Md5Utils;
import com.linlibang.common.lang.DateUtils;
import com.linlibang.common.lang.StringUtils;
import com.linlibang.pay.module.base.UnionBaseRequest;
import com.linlibang.pay.module.cToB.entity.po.*;
import com.linlibang.pay.module.union.UnionPayDict;
import com.linlibang.pay.module.unionPay.UnionPayUtil;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;


/**
 * C扫B
 */
@Log4j
@Component
public class CToBUtil {

    /**
     * 业务类型
     */
    @Value("${unionPay.api.cToB.INST_MID}")
    private String instMid;
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
    /**
     * 查询订单状态
     */
    @Value("${unionPay.api.cToB.queryBill}")
    private String queryBillApi;
    /**
     * 退款
     */
    @Value("${unionPay.api.cToB.refundBill}")
    private String refundBillApi;

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
     * 查询订单状态
     * @param billNo
     * @return
     */
    public BaseResponse queryBill(String billNo,String billDate){
        QueryBillRequestPo queryBillRequestPo = new QueryBillRequestPo();
        initParam(queryBillRequestPo);
        queryBillRequestPo.setBillNo(billNo);
        queryBillRequestPo.setBillDate(billDate);
        String paramBody = JSON.toJSONString(queryBillRequestPo);
        String result = unionPayUtil.sendPost(queryBillApi,paramBody);
        if(StringUtils.isNotBlank(result)){
            log.error("查询订单【"+billNo+"】接口返回空");
            return new BaseResponse(ApiConstant.FAIL,"查询不到订单信息，请稍后再试!",null);
        }
        QueryBillResponsePo queryBillResponsePo = JSON.parseObject(result)
                .toJavaObject(QueryBillResponsePo.class);
        //TODO:根据返回结果进行校验
        return new BaseResponse(queryBillResponsePo);
    }

    /**
     * 退款
     * @param billNo
     */
    public void refundBill(String billNo){
        RefundBillRequestPo refundBillRequestPo = new RefundBillRequestPo();
        initParam(refundBillRequestPo);
        String paramJson = JSON.toJSONString(refundBillRequestPo);
        String result = unionPayUtil.sendPost(refundBillApi,paramJson);
        if(StringUtils.isBlank(result)){
            log.error("调用退款接口返回空");
            return;
        }
        log.info("退款接口返回:"+result);
        RefundBillResponsePo refundBillResponsePo = JSON.parseObject(result)
                .toJavaObject(RefundBillResponsePo.class);
        //TODO:判断是否退款成功来执行业务逻辑
        if(UnionPayDict.ERR_CODE_SUCCESS.equals(refundBillResponsePo.getErrCode())
                && UnionPayDict.REFUND_STATUS_SUCCESS.equals(refundBillResponsePo.getRefundStatus())){

        }

    }


    /**
     * 异步流水通知
     * @param resultJsonStr
     */
    public void asyncNotify(String resultJsonStr){
        JSONObject resultJson = JSON.parseObject(resultJsonStr);
        String sign = resultJson.getString("sign");
        resultJson.remove("sign");
        StringBuffer paramStr = new StringBuffer();
        for(String key : resultJson.keySet()){
            paramStr.append(key).append("=").append(resultJson.getJSONObject(key).toJSONString());
        }
        paramStr.append(unionPayUtil.getAppKey());
        log.info("待加密参数:"+paramStr);
        //比较md5后与sign比较
        String paramMd5 = Md5Utils.md5(paramStr.toString()).toUpperCase();
        log.info("参数MD5:"+paramMd5);
        log.info("待比较sign:"+sign);
        if(paramMd5.equals(sign)){
            //TODO：确认签名无误，执行业务逻辑
        }
    }

    /**
     * 请求参数初始化
     * @param unionBaseRequest
     */
    private void initParam(UnionBaseRequest unionBaseRequest){
        unionBaseRequest.setTid(unionPayUtil.getTid());
        unionBaseRequest.setMid(unionPayUtil.getMid());
        unionBaseRequest.setRequestTimestamp(DateUtils.getDate("yyyy-MM-dd HH:mm:ss"));
        unionBaseRequest.setInstMid(instMid);
    }


}
