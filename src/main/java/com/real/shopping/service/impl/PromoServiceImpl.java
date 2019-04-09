package com.real.shopping.service.impl;

import com.real.shopping.dao.PromoDOMapper;
import com.real.shopping.dataobject.PromoDO;
import com.real.shopping.service.PromoService;
import com.real.shopping.service.model.PromoModel;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

/**
 * @author: mabin
 * @create: 2019/4/6 22:44
 */
@Service
public class PromoServiceImpl implements PromoService {

    @Autowired
    private PromoDOMapper promoDOMapper;

    @Override
    public PromoModel getByItemId(Integer id) {
        PromoDO promoDO = promoDOMapper.getByItemId(id);
        PromoModel promoModel = convertModelFromDO(promoDO);
        if (promoModel == null) {
            return null;
        }

        //判断秒杀活动时间
        if (promoModel.getStartDate().isAfterNow()){
            promoModel.setStatus(1);
        }else if (promoModel.getEndDate().isBeforeNow()){
            promoModel.setStatus(3);
        }else {
            promoModel.setStatus(2);
        }

        return promoModel;
    }

    private PromoModel convertModelFromDO(PromoDO promoDO) {
        if (promoDO == null){
            return null;
        }
        PromoModel promoModel = new PromoModel();
        BeanUtils.copyProperties(promoDO,promoModel);
        promoModel.setPromoItemPrice(new BigDecimal(promoDO.getPromoItemPrice()));
        promoModel.setStartDate(new DateTime(promoDO.getStartTime()));
        promoModel.setEndDate(new DateTime(promoDO.getEndTime()));
        return promoModel;
    }
}
