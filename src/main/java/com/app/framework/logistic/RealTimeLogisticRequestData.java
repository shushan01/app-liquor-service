package com.app.framework.logistic;

import lombok.Data;

@Data
public class RealTimeLogisticRequestData {
    private String shipperCode;//	快递公司编码	R
    private String logisticCode;//	物流单号	R
}
