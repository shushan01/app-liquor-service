package com.app.framework.logistic;

import lombok.Data;

/**
 * 生成电子订单请求数据
 */
@Data
public class GenerateElectronicOrderRequestData {
    private String customerName;//电子面单客户账号（与快递网点申请）	O
    private String customerPwd;//电子面单密码
    private String shipperCode;//快递公司编码
    private String orderCode;//订单编号
    private Integer payType;//邮费支付方式:1-现付，2-到付，3-月结，4-第三方支付
    private String expType;//快递类型：1-标准快件
    private Sender sender;//请求、返回数据类型：只支持JSON格式
    private Receiver receiver;//请求、返回数据类型：只支持JSON格式
    private Commodity[] commodity;//请求、返回数据类型：只支持JSON格式

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
