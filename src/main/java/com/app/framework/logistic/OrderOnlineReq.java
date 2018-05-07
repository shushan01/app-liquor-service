package com.app.framework.logistic;

import lombok.Data;

@Data
public class OrderOnlineReq {
    private RequestData requestData;//请求内容需进行URL(utf-8)编码。请求内容JSON格式，须和DataType一致。
//    private String eBusinessID;//商户ID，请在我的服务页面查看。
    private String requestType;//请求指令类型：1.电子面单接口(1007);2.订单取消接口(1147)
//    private String dataSign;//数据内容签名：把(请求内容(未编码)+AppKey)进行MD5加密，然后Base64编码，最后 进行URL(utf-8)编码。详细过程请查看Demo。
//    private String dataType;//请求、返回数据类型：只支持JSON格式

    @Data
    public static class RequestData {
        private String shipperCode;//快递公司编码
        private String orderCode;//订单编号
        private Integer payType;//邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付
        private String expType;//快递类型：1-标准快件
        private Sender sender;//请求、返回数据类型：只支持JSON格式
        private Receiver receiver;//请求、返回数据类型：只支持JSON格式
        private Commodity[] commodity;//请求、返回数据类型：只支持JSON格式
    }

    @Data
    public static class Sender {
        private String name;//发件人
        private String tel;//Tel和Mobile电话与手机，必填一个
        private String mobile;//Tel和Mobile电话与手机，必填一个
        private String provinceName;//发件省（如广东省，不要缺少“省”）
        private String cityName;//发件市（如深圳市，不要缺少“市”）
        private String expAreaName;//发件区（如福田区，不要缺少“区”或“县”）
        private String address;//发件人详细地址
    }

    @Data
    public static class Receiver {
        private String name;//收件人
        private String tel;//Tel和Mobile电话与手机，必填一个
        private String mobile;//Tel和Mobile电话与手机，必填一个
        private String provinceName;//收件省（如广东省，不要缺少“省”）
        private String cityName;//收件市（如深圳市，不要缺少“市”）
        private String expAreaName;//收件区（如福田区，不要缺少“区”或“县”）
        private String address;//收件人详细地址
    }

    @Data
    public static class Commodity {
        private String goodsName;//商品名称
    }
}
