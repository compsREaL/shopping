package com.real.shopping.service.model;

import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;

/**
 * @author: mabin
 * @create: 2019/4/5 20:22
 */
public class ItemModel {

    //商品id；
    private Integer id;
    //商品名称
    @NotBlank(message = "商品名称不能为空")
    private String title;
    //商品价格
    @NotNull(message = "商品价格不能为空")
    @Min(value = 0,message = "商品价格不能小于0.00元")
    private BigDecimal price;
    //商品描述
    @NotBlank(message = "商品描述不能为空")
    private String description;
    //商品库存数量
    @NotNull(message = "商品库存不能为空")
    @Min(value = 0,message = "商品库存不得小于0件")
    private Integer stock;
    //商品销量
    private Integer sales;
    //商品图片链接地址
    @NotBlank(message = "商品图片信息不能为空")
    private String imgUrl;

    //如果promoModel不为空，则表示其还有尚未结束的秒杀活动
    private PromoModel promoModel;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStock() {
        return stock;
    }

    public void setStock(Integer stock) {
        this.stock = stock;
    }

    public Integer getSales() {
        return sales;
    }

    public void setSales(Integer sales) {
        this.sales = sales;
    }

    public String getImgUrl() {
        return imgUrl;
    }

    public void setImgUrl(String imgUrl) {
        this.imgUrl = imgUrl;
    }

    public PromoModel getPromoModel() {
        return promoModel;
    }

    public void setPromoModel(PromoModel promoModel) {
        this.promoModel = promoModel;
    }
}
