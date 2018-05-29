package com.app.framework.controller.product;

import com.app.framework.dto.GoodsDTO;
import com.app.framework.model.ProductEntyty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/shop")
public class ProductController {
    @PostMapping("/SearchGoods")
    public List<ProductEntyty> getGoods(@RequestBody GoodsDTO query) {
        List<ProductEntyty> list = getProductMock();
        int end = query.getPageIndex() * query.getPageSize();
        end = (end > list.size() ? list.size() : end);
        return list.subList((query.getPageIndex() - 1) * query.getPageSize(), end);
    }

    private List<ProductEntyty> getProductMock() {
        String baseUrl = "http://yangs2.tunnel.qydev.com/src/assets/test/img";
        List<ProductEntyty> list = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            ProductEntyty pro = new ProductEntyty();
            pro.setId(963652948422340l);
            pro.setProductName("飞天茅台 53° 普五浓香型高度白酒水送礼名酒 500ml");
            pro.setPrice(new BigDecimal("169"+i));
            pro.setImgUrls(Arrays.asList(baseUrl + "/tuijian01.jpg"));
            list.add(pro);
            ProductEntyty pro2 = new ProductEntyty();
            pro2.setId(963652948422340l);
            pro2.setProductName("洋河蓝色经典·梦之蓝 M3  52° 绵柔型 500ml");
            pro2.setPrice(new BigDecimal("169"+(i+1)));
            pro2.setImgUrls(Arrays.asList(baseUrl + "/tuijian02.jpg"));
            list.add(pro2);
            ProductEntyty pro3 = new ProductEntyty();
            pro3.setId(963652948422340l);
            pro3.setProductName("唐小姐 梅子酒 10° 甜型 375ml ");
            pro3.setPrice(new BigDecimal("169"+(i+2)));
            pro3.setImgUrls(Arrays.asList(baseUrl + "/tuijian03.jpg"));
            list.add(pro3);
        }
        return list;
    }
}
