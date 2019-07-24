package com.linlibang.pay.module.unionPay;


import com.alibaba.fastjson.JSON;
import com.google.common.collect.Maps;
import com.linlibang.common.lang.DateUtils;
import com.linlibang.common.lang.StringUtils;
import com.linlibang.pay.utils.HttpUtil;
import com.linlibang.pay.utils.SHA256Util;
import lombok.Data;
import lombok.extern.log4j.Log4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.util.HashMap;

/**
 * 银联支付工具类
 *
 * @author 肥美的洪洪哥
 * @version 2019-07-18
 */
@Log4j
@Component
@Data
public class UnionPayUtil {

    @Value("${unionPay.appId}")
    private  String appId;
    @Value("${unionPay.appKey}")
    private  String appKey;
    @Value("${unionPay.domainName}")
    private String domainName;
    /**
     * 来源编号
     */
    @Value("${unionPay.msgSrcId}")
    private  String msgSrcId;
    /**
     * 商户号
     */
    @Value("${unionPay.mid}")
    private  String mid;
    /**
     * 终端号
     */
    @Value("${unionPay.tid}")
    private  String tid;

    /**
     * 支付结果通知地址
     */
    @Value("${unionPay.notifyUrl}")
    private  String notifyUrl;


    /**
     * 获取银联请求头的校验字段Authorization
     * OPEN-BODY-SIG AppId="AppId", Timestamp="时间戳", Nonce="随机数", Signature="签名"
     * 签名算法
     * 1、取报文体，即正文全部内容获得字节数组A，进行SHA256算法取16进制小写字符串获得B，算法公式为B=SHA256_WITH_LOWER_HEXSTR(A)。
     * 例如：对字符串 "A"进行sha256,得B="559aead08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd"
     * 2、取AppId，Timestamp，Nonce，B进行字符串拼接，以UTF-8进行编码获得待签名串C，取AppKey作为签名密钥D。
     * 例如：
     * AppId=“12345678901234567890123456789012”，Timestamp=“20170101120000”，
     * Nonce=“09876543210987654321098765432109”，
     * 得C=”123456789012345678901234567890122017010112000009876543210987654321098765432109559aead
     * 08264d5795d3909718cdd05abd49572e84fe55590eef31a88a08fdffd”
     * D=”67890123456789012345678901234567”
     * 3、以C和D进行HMAC-SHA256算法获得签名字节数组E，算法公式为：
     * E=HmacSHA256(signingBytes: C, keyBytes: D)。
     * 例如：
     * E=[0x18,0x83,0x6c,0x09,0x3c,0x8d,0x29,0x3a,0x44,0x23,0xd2,0x97,0x3b,0x5e,0x8a,0xa9,0x92,0x7a,0xe1,
     * 0xf3,0xb2,0x03,0x2b,0x44,0x2a,0x5f,0x1a,0x69,0x1f,0xc3,0xcb,0x4f]
     * 4、对E进行Base64编码获得F，F即为签名内容，算法公式为F=Base64_Encode(E)。
     * 例如：
     * F=”GINsCTyNKTpEI9KXO16KqZJ64fOyAytEKl8aaR/Dy08=”
     */
    public  String getOpenBodySign(String body) {
        //将请求body进行sha256
        body = SHA256Util.getSHA256StrJava(body);
        //随机字符串
        String nonce = StringUtils.getRandomStr(16);
        //当前时间(yyyyMMddHHmmss)
        String timestamp = DateUtils.getDate("yyyyMMddHHmmss");
        //待签名字符串
        String signStr = appId + timestamp + nonce + body;
        //这个获取hmac256后的byte[]经过base64编码后跟预期不一样，用打印JSON方式打印byte[]却一样
        String signature = JSON.toJSONString(SHA256Util.getHmacSha256(signStr, appKey));
        log.info("待签名字符串:" + signStr);
        log.info("key:" + appKey);
        log.info(signature);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OPEN-BODY-SIG")
                .append(" AppId=").append(appId)
                .append(",Timestamp=").append(timestamp)
                .append(",Nonce=").append(nonce)
                .append(",Signature=").append(signStr);
        log.info("最终请求头参数:"+stringBuffer.toString());
        return stringBuffer.toString();
    }

    /**
     * 封装请求验证方法的POST
     * @param url
     * @param paramJson
     * @return
     */
    public String sendPost(String url,String paramJson){
        log.info("银联请求url:"+url);
        log.info("请求参数:"+paramJson);
        String authorization = getOpenBodySign(paramJson);
        HashMap headMap = Maps.newHashMap();
        headMap.put("Authorization", authorization);
        String result = null;
        try {
            result = HttpUtil.post(domainName + url, paramJson, headMap);
        } catch (IOException e) {
            log.error("银联请求出现错误:", e);
        }
        return result;
    }

    /**
     * 获取订单号（来源编号+时间+7位随机数）
     * @return
     */
    public  String getBillNo(){
        return  msgSrcId + DateUtils.getDate("yyyyMMddHHmmssSSS") + StringUtils.getRandomStr(7);
    }




}

