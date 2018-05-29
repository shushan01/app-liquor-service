package com.app.framework.controller.index;

import com.app.framework.model.ProductEntyty;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.*;

@RestController
@RequestMapping("/cms")
public class IndexController {
    @PostMapping("/GetIndexCmsData")
    public Map<String, Object> getIndexCmsData() {
        Map<String, Object> map = new HashMap<>(4);
        String baseUrl = "http://yangs2.tunnel.qydev.com/src/assets/test/img";
        //轮播图
        map.put("cmsBanner", Arrays.asList(baseUrl + "/home04.jpg", baseUrl + "/home01.jpg", baseUrl + "/home03.jpg"));
        //中间图片
        map.put("centerBanner", baseUrl + "/floot.png");
        //酒花
        map.put("jiuhuas", Arrays.asList("【酒花】 5ml 体验贵州 53°酱香型", "【酒花】 5ml 经典海之蓝 53°酱香型", "【酒花】 10ml 体验国标酱香 53°酱香型"));
        //推荐
        List<List<ProductEntyty>> list = getProductMock(baseUrl);
        map.put("recommend", list);
        map.put("cartCount", 2);
        return map;
    }

    private List<List<ProductEntyty>> getProductMock(String baseUrl) {
        List<ProductEntyty> list = new ArrayList<>();
        List<List<ProductEntyty>> result = new ArrayList<>();
        for (int i = 0; i < 2; i++) {
            ProductEntyty pro = new ProductEntyty();
            pro.setId(1l);
            pro.setProductName("飞天茅台 53°");
            pro.setPrice(new BigDecimal("1599"));
            pro.setImgUrls(Arrays.asList(baseUrl + "/tuijian01.jpg"));
            list.add(pro);
            ProductEntyty pro2 = new ProductEntyty();
            pro2.setId(2l);
            pro2.setProductName("洋河蓝色·梦之蓝°");
            pro2.setPrice(new BigDecimal("668"));
            pro2.setImgUrls(Arrays.asList(baseUrl + "/tuijian02.jpg"));
            list.add(pro2);
            ProductEntyty pro3 = new ProductEntyty();
            pro3.setId(3l);
            pro3.setProductName("唐小姐 梅子酒°");
            pro3.setPrice(new BigDecimal("198"));
            pro3.setImgUrls(Arrays.asList(baseUrl + "/tuijian03.jpg"));
            list.add(pro3);
        }
        for (int i = 0; i < Math.ceil(list.size() / 3.0); i++) {
            int end = (i + 1) * 3;
            end = (end > list.size() ? list.size() : end);
            result.add(list.subList(i * 3, end));
        }
        return result;
    }
}
