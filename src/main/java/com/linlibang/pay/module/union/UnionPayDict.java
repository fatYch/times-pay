package com.linlibang.pay.module.union;

/**
 * 银联字典表
 * @author 肥美的洪洪哥
 * @version 2019-07-22
 */
public class UnionPayDict {
    /**
     * 请求状态
     */
    public final static String ERR_CODE_SUCCESS = "SUCCESS";


    /**
     * 退款结果
     * SUCCESS 成功 FAIL 失败 PROCESSING 处理中 UNKNOWN 异常
     */
    public final static String REFUND_STATUS_SUCCESS = "SUCCESS";
    public final static String RFFUND_STATUS_FAIL = "FAIL";
    public final static String REFUND_STATUS_PROCESSING = "PROCESSING";
    public final static String REFUND_STATUS_UNKNOWN = "UNKNOWN";



}
