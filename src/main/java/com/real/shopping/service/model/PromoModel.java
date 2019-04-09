package com.real.shopping.service.model;

import org.joda.time.DateTime;

import java.math.BigDecimal;

/**
 * @author: mabin
 * @create: 2019/4/6 20:58
 */
public class PromoModel {

    private Integer id;

    //代表秒杀活动的状态。1表示活动还未开始；2表示活动正在进行中；3表示活动已经结束
    private Integer status;

    private String promoName;

    private DateTime startDate;

    private DateTime endDate;

    private Integer itemId;

    private BigDecimal promoItemPrice;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getPromoName() {
        return promoName;
    }

    public void setPromoName(String promoName) {
        this.promoName = promoName;
    }

    public DateTime getStartDate() {
        return startDate;
    }

    public void setStartDate(DateTime startDate) {
        this.startDate = startDate;
    }

    public DateTime getEndDate() {
        return endDate;
    }

    public void setEndDate(DateTime endDate) {
        this.endDate = endDate;
    }

    public Integer getItemId() {
        return itemId;
    }

    public void setItemId(Integer itemId) {
        this.itemId = itemId;
    }

    public BigDecimal getPromoItemPrice() {
        return promoItemPrice;
    }

    public void setPromoItemPrice(BigDecimal promoItemPrice) {
        this.promoItemPrice = promoItemPrice;
    }
}
