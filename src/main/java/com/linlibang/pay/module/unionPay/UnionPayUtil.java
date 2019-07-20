package com.linlibang.pay.module.unionPay;


import com.alibaba.fastjson.JSON;
import com.linlibang.common.lang.DateUtils;
import com.linlibang.common.lang.StringUtils;
import com.linlibang.pay.utils.SHA256Util;
import lombok.extern.log4j.Log4j;

/**
 * 银联支付工具类
 *
 * @author 肥美的洪洪哥
 * @version 2019-07-18
 */
@Log4j

public class UnionPayUtil {

    private static String appId = "12345678901234567890123456789012";
    private static String appKey = "67890123456789012345678901234567";
    /**
     * 来源编号
     */
    private static String source = "";
    /**
     * 商户号
     */
    private static String mid = "";
    /**
     * 终端号
     */
    private static String tid = "";

    /**
     * 支付结果通知地址
     */
    private static String notifyUrl = "";

    public static String getMid(){
        return mid;
    }
    public static String getTid(){
        return tid;
    }

    public static String getAppId() {
        return appId;
    }

    public void setAppId(String appId) {
        UnionPayUtil.appId = appId;
    }

    public static String getAppKey() {
        return appKey;
    }

    public void setAppKey(String appKey) {
        UnionPayUtil.appKey = appKey;
    }

    public static String getSource() {
        return source;
    }

    public void setSource(String source) {
        UnionPayUtil.source = source;
    }

    public void setMid(String mid) {
        UnionPayUtil.mid = mid;
    }

    public void setTid(String tid) {
        UnionPayUtil.tid = tid;
    }

    public static String getNotifyUrl() {
        return notifyUrl;
    }

    public  void setNotifyUrl(String notifyUrl) {
        UnionPayUtil.notifyUrl = notifyUrl;
    }

    public static String getAppSource() {
        return appSource;
    }

    public void setAppSource(String appSource) {
        UnionPayUtil.appSource = appSource;
    }

    /**
     * {来源编号(4位)}{时间(yyyyMMddmmHHssSSS)(17位)}{7位随机数}
     */
    private static String appSource = "TODO";

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
    public static String getOpenBodySign(String body) {
        //将请求body进行sha256
        body = SHA256Util.getSHA256StrJava(body);
        //随机字符串
        String nonce = StringUtils.getRandomStr(16);
        //当前时间戳
        String timestamp = String.valueOf(System.currentTimeMillis());
        //待签名字符串
        String signStr = appId + timestamp + nonce + body;
        //这个获取hmac256后的byte[]经过base64编码后跟预期不一样，用打印JSON方式打印byte[]却一样
        String signature = JSON.toJSONString(SHA256Util.getHmacSha256(signStr, appKey));
        log.info("待签名字符串:" + signStr);
        log.info("key:" + appKey);
        log.info(signature);
        StringBuffer stringBuffer = new StringBuffer();
        stringBuffer.append("OPEN-BODY-SIG")
                .append(" APPId=").append(appId)
                .append(",Timestamp=").append(timestamp)
                .append(",Nonce=").append(nonce)
                .append(",Signature=").append(signStr);
        log.info("最终请求头参数:"+stringBuffer.toString());
        return stringBuffer.toString();
    }

    /**
     * 获取订单号（来源编号+时间+7位随机数）
     * @return
     */
    public static String getBiilNo(){
        return source + DateUtils.getDate("yyyyMMddHhmmssSSS") + StringUtils.getRandomStr(7);
    }


}

