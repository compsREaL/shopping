package com.real.shopping.service;

import com.real.shopping.error.BussinessException;
import com.real.shopping.service.model.OrderModel;

/**
 * @author: mabin
 * @create: 2019/4/6 15:18
 */
public interface OrderService {
    OrderModel createOrder(Integer userId, Integer itemId,Integer promoId, Integer amount) throws BussinessException;
}
