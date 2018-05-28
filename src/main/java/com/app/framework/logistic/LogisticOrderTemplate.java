package com.app.framework.logistic;

import com.app.framework.core.utils.GsonUtils;
import com.sun.org.apache.xerces.internal.impl.dv.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.security.MessageDigest;
import java.util.HashMap;
import java.util.Map;

@Component
public class LogisticOrderTemplate {
    private static final String CHARSET = "UTF-8";
    //电商加密私钥，快递鸟提供，注意保管，不要泄漏
    @Value(value = "${logistic.eBusinessID}")
    private String eBusinessID;
    //电商ID
    @Value(value = "${logistic.appKey}")
    private String appKey;
    //请求url, 正式环境地址：http://api.kdniao.cc/api/Eorderservice
    // 测试环境地址：http://testapi.kdniao.cc:8081/api/EOrderService
    @Value(value = "${logistic.reqURL}")
    private String reqURL;
    @Autowired
    private RestTemplate restTemplate;

    /**
     * 生成电子面单
     * （1）电子面单接口是快递鸟提供给独立电商、仓储管理系统、物流供应链等物流系统平台使用的下单接口。
     * （2）为客户解决在线发货需求，商户通过网络选择快递公司发送请求通知快递公司有快递要发货。
     * （3） 客户把数据通过此接口转发到快递鸟，由快递鸟为您安排快递员上门取件的服务。
     * （4）订单编号（OrderCode）不可重复提交，重复提交系统会返回具体错误代码。
     * （5）接口支持的消息接收方式为HTTP POST，请求方法的编码格式(utf-8)："application/x-www-form-urlencoded;charset=utf-8"。
     * （6）接口地址：
     * API测试地址：http://sandboxapi.kdniao.cc:8080/kdniaosandbox/gateway/exterfaceInvoke.json
     * API正式地址：http://api.kdniao.cc/api/EOrderService
     *
     * @param requestData
     * @return
     * @throws Exception
     */
    public Map<String, Object> generate(GenerateElectronicOrderRequestData requestData) throws Exception {
        return sender(requestData, "1007");
    }

    /**
     * 订单取消接口
     * （1）只支持有成功下单记录的订单进行取消。
     * （2）只支持对未揽件的订单进行取消。
     * （3）订单取消后，订单号仍不可重复使用。
     * （4）订单取消后快递单号的回收规则以快递公司为准。
     * （5）接口地址：
     * API测试地址：http://sandboxapi.kdniao.cc:8080/kdniaosandbox/gateway/exterfaceInvoke.json
     * API正式地址：http://api.kdniao.cc/api/EOrderService
     *
     * @param requestData
     * @return
     * @throws Exception
     */
    public Map<String, Object> cancel(CancelElectronicOrderRequestData requestData) throws Exception {
        return sender(requestData, "1147");
    }

    /**
     * 即时查询物流
     * <p>
     * 一、接口描述/说明
     * （1）查询接口支持按照运单号查询(单个查询)。
     * （2）接口需要指定快递单号的快递公司编码，格式不对或则编码错误都会返失败的信息。
     * 如：EMS物流单号应选择快递公司编码（EMS）查看快递公司编码
     * （3）返回的物流跟踪信息按照发生的时间升序排列。
     * （4）接口指令1002。
     * （5）接口支持的消息接收方式为HTTP POST，请求方法的编码格式(utf-8)："application/x-www-form-urlencoded;charset=utf-8"。
     * （6）测试地址：http://sandboxapi.kdniao.cc:8080/kdniaosandbox/gateway/exterfaceInvoke.json
     * （7）正式地址：http://api.kdniao.cc/Ebusiness/EbusinessOrderHandle.aspx
     *
     * @param requestData
     * @return
     * @throws Exception
     */
    public Map<String, Object> realTimeFind(RealTimeLogisticRequestData requestData) throws Exception {
        return sender(requestData, "1002");
    }

    private Map<String, Object> sender(Object requestData, String requestType) throws Exception {
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON_UTF8);
        headers.set("Connection", "close");

        String reqData = GsonUtils.toJson(requestData);
        String requestDataEn = urlEncoder(reqData, CHARSET);

        Map<String, Object> param = new HashMap<String, Object>();
        param.put("requestData", requestDataEn);
        String dataSign = encrypt(reqData, appKey, CHARSET);
        param.put("dataSign", urlEncoder(dataSign, CHARSET));
        param.put("eBusinessID", eBusinessID);
        param.put("requestType", requestType);
        param.put("dataType", "2");

        HttpEntity<Object> requestEntity = new HttpEntity<Object>(GsonUtils.toJson(param), headers);
        ResponseEntity re = restTemplate.postForEntity(reqURL, requestEntity, String.class);
        return GsonUtils.fromJson(re.getBody().toString(), HashMap.class);
    }

    /**
     * MD5加密
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws Exception
     */
    @SuppressWarnings("unused")
    private String MD5(String str, String charset) throws Exception {
        MessageDigest md = MessageDigest.getInstance("MD5");
        md.update(str.getBytes(charset));
        byte[] result = md.digest();
        StringBuffer sb = new StringBuffer(32);
        for (int i = 0; i < result.length; i++) {
            int val = result[i] & 0xff;
            if (val <= 0xf) {
                sb.append("0");
            }
            sb.append(Integer.toHexString(val));
        }
        return sb.toString().toLowerCase();
    }

    /**
     * base64编码
     *
     * @param str     内容
     * @param charset 编码方式
     * @throws UnsupportedEncodingException
     */
    private String base64(String str, String charset) throws UnsupportedEncodingException {
        return Base64.encode(str.getBytes(charset));
    }

    @SuppressWarnings("unused")
    private String urlEncoder(String str, String charset) throws UnsupportedEncodingException {
        return URLEncoder.encode(str, charset);
    }

    /**
     * 电商Sign签名生成
     *
     * @param content  内容
     * @param keyValue Appkey
     * @param charset  编码方式
     * @return DataSign签名
     * @throws UnsupportedEncodingException ,Exception
     */
    @SuppressWarnings("unused")
    private String encrypt(String content, String keyValue, String charset) throws UnsupportedEncodingException, Exception {
        if (keyValue != null) {
            return base64(MD5(content + keyValue, charset), charset);
        }
        return base64(MD5(content, charset), charset);
    }
}
