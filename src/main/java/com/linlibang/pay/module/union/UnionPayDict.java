package com.linlibang.pay.module.union;

/**
 * 银联字典表
 *
 * @author 肥美的洪洪哥
 * @version 2019-07-22
 */
public class UnionPayDict {

    /**
     * 常见交易返回码
     * 00 交易成功
     */
    public final static String ERR_CODE_SUCCESS = "00";

    /**
     * 人民币标识
     */
    public final static String CURRENCY_CODE_RMB = "156";




    /** C扫B **/
    /**
     * 请求状态
     */
//    public final static String ERR_CODE_SUCCESS = "SUCCESS";

    /**
     * 退款结果
     * SUCCESS 成功 FAIL 失败 PROCESSING 处理中 UNKNOWN 异常
     */
    public final static String REFUND_STATUS_SUCCESS = "SUCCESS";
    public final static String RFFUND_STATUS_FAIL = "FAIL";
    public final static String REFUND_STATUS_PROCESSING = "PROCESSING";
    public final static String REFUND_STATUS_UNKNOWN = "UNKNOWN";

    /**
     * 订单状态
     */
    public final static String BILL_STATUS_PAID = "PAID";
    public final static String BILL_STATUS_UNPAID = "UNPAID";
    public final static String BILL_STATUS_REFUND = "REFUND";
    public final static String BILL_STATUS_CLOSED = "CLOSED";
    public final static String BILL_STATUS_UNKNOWN = "UNKNOWN";


    /** B扫C **/
    /**
     * 支付方式 （E_CASH电子现金、SOUNDWAVE声波、NFC、CODE_SCAN扫码、MANUAL手输）
     */
    public final static String PAY_MODE_ECASH = "E_CASH";
    public final static String PAY_MODE_SOUNDWAVE = "SOUNDWAVE";
    public final static String PAY_MODE_NFC = "NFC";
    public final static String PAY_MODE_CODESCAN = "CODE_SCAN";
    public final static String PAY_MODE_MANUAL = "MANUAL";

    /**
     * 订单查询结果
     * 0：成功
     * 1：超时
     * 2：已撤销
     * 3：已退货
     * 4：已冲正
     * 5：失败（失败情况，后面追加失败描述)
     * FF：交易状态未知
     */
    public final static String QUERY_RES_SUCCESS = "0";
    public final static String QUERY_RES_OVERTIME = "1";
    public final static String QUERT_RES_REVOKE = "2";
    public final static String QUERY_RES_REFUND = "3";
    public final static String QUERY_RES_CORRECT = "4";
    public final static String QUERT_RES_FAIL = "5";
    public final static String QUERY_RES_UNKNOW = "FF";



}
