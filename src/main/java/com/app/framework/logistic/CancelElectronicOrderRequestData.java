package com.app.framework.logistic;

import lombok.Data;

/**
 * 取消电子订单请求数据
 */
@Data
public class CancelElectronicOrderRequestData {
    private String shipperCode;//快递公司编码	R
    private String orderCode;//订单编号	R
    private String expNo;//	快递单号	R
}
