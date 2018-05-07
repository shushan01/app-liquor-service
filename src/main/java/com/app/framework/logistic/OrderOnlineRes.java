package com.app.framework.logistic;

import lombok.Data;

@Data
public class OrderOnlineRes {
    private String EBusinessID;//商户ID，请在我的服务页面查看。
    private String Success;//	成功与否
    private String ResultCode;//错误编码
    private String Reason;//失败原因
    private String UniquerRequestNumber;//唯一标识
    private Order Order;

    @Data
    public static class Order {
        private String OrderCode;//订单编号
        private String ShipperCode;//快递公司编码
        private String LogisticCode;//快递单号
    }
}
