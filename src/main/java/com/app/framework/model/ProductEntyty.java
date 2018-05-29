package com.app.framework.model;

import com.app.framework.auth.model.BaseEntity;

import java.math.BigDecimal;
import java.util.List;

public class ProductEntyty extends BaseEntity {
    private static final long serialVersionUID = 4280616485203103083L;
    private Long id;
    private String productName;
    private BigDecimal price;
    private List<String> imgUrls;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long id) {
        this.id = id;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public List<String> getImgUrls() {
        return imgUrls;
    }

    public void setImgUrls(List<String> imgUrls) {
        this.imgUrls = imgUrls;
    }
}
