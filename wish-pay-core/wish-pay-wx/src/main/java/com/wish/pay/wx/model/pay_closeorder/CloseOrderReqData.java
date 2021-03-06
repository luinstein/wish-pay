package com.wish.pay.wx.model.pay_closeorder;

import com.beust.jcommander.internal.Maps;
import com.thoughtworks.xstream.annotations.XStreamAlias;
import com.wish.pay.wx.common.WxUtils;
import com.wish.pay.wx.model.common.BaseReqData;

import java.lang.reflect.Field;
import java.util.Map;

/**
 * 关闭订单API需要提交的数据
 * @author fqh
 * @email fanqinghui100@126.com
 * @date 2017/3/15 10:08
 */
@XStreamAlias(value = "xml")
public class CloseOrderReqData extends BaseReqData {

    //每个字段具体的意思请查看API文档
    private String out_trade_no = "";//商户订单号

    /**
     * @param appid
     * @param mchId
     * @param deviceInfo
     * @param key
     * @param outTradeNo
     */
    public CloseOrderReqData(String appid, String mchId, String deviceInfo, String key, String outTradeNo) {
        //微信分配的公众号ID（开通公众号之后可以获取到）
        setAppid(appid);
        //微信支付分配的商户号ID（开通公众号的微信支付功能之后可以获取到）
        setMch_id(mchId);
        //商户自己定义的扫码支付终端设备号，方便追溯这笔交易发生在哪台终端设备上
        setDevice_info(deviceInfo);
        //商户系统内部的订单号,32个字符内可包含字母, 确保在商户系统唯一
        setOut_trade_no(outTradeNo);
        //随机字符串，不长于32 位
        setNonce_str(WxUtils.getRandomStringByLength(32));
        //根据API给的签名规则进行签名
        String sign = WxUtils.getSign(toMap(), key);
        setSign(sign);//把签名数据设置到Sign这个属性中
    }


    public String getOut_trade_no() {
        return out_trade_no;
    }

    public void setOut_trade_no(String out_trade_no) {
        this.out_trade_no = out_trade_no;
    }
}
