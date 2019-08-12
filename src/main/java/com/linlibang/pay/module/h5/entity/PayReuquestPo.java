package com.linlibang.pay.module.h5.entity;

import com.linlibang.pay.module.base.UnionBaseRequest;
import com.linlibang.pay.module.base.GoodsPo;
import com.linlibang.pay.module.base.SubOrdersPo;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import java.util.List;

@ApiModel("下单请求体")
@Data
public class PayReuquestPo extends UnionBaseRequest {

    @ApiModelProperty("商品信息")
    private List<GoodsPo> goods;
    @ApiModelProperty("商户附加数据")
    private String attachedData;
    @ApiModelProperty("订单描述")
    private String orderDesc;
    @ApiModelProperty("商品标记")
    private String goodsTag;
    @ApiModelProperty("订单原始金额")
    private Integer originalAmount;
    @ApiModelProperty("支付总额")
    private Integer totalAmount;
    @ApiModelProperty("订单过期时间")
    private String expireTime;
    @ApiModelProperty("担保交易标识")
    private String secureTransaction;
    @ApiModelProperty("支付结果通知")
    private String notifyUrl;
    @ApiModelProperty("网页跳转地址")
    private String returnUrl;
    @ApiModelProperty("系统id")
    private String systemId;
    @ApiModelProperty("系统应用类型: 微信H5支付必填,用于苹 app应用里值为IOS_SDK ；用于安卓app 应用里值为 AND_SDK；用于手机网 站，值为IOS_WAP 或 AND_WAP")
    private String sceneType;
    @ApiModelProperty("应用名称")
    private String merAppName;
    @ApiModelProperty("应用标识")
    private String merAppId;
    @ApiModelProperty("是否需要限制信用卡支付")
    private boolean limitCreditCard;
    @ApiModelProperty("平台商户分账金额")
    private Integer platformAmount;
    @ApiModelProperty("子订单信息")
    private List<SubOrdersPo> subOrders;
    @ApiModelProperty("实名认证姓名")
    private String name;
    @ApiModelProperty("实名认证手机号")
    private String mobile;
    @ApiModelProperty("实名认证证件类型")
    private String certType;
    @ApiModelProperty("实名认证证件号")
    private String certNo;
    @ApiModelProperty("卡号")
    private String bankCardNo;

}
