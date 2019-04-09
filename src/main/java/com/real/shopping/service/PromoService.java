package com.real.shopping.service;

import com.real.shopping.service.model.PromoModel;

/**
 * @author: mabin
 * @create: 2019/4/6 22:43
 */
public interface PromoService {
    PromoModel getByItemId(Integer id);
}
