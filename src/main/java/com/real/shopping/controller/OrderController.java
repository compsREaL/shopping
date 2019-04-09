package com.real.shopping.controller;

import com.real.shopping.error.BussinessException;
import com.real.shopping.error.EmBussinessError;
import com.real.shopping.response.CommonReturnType;
import com.real.shopping.service.OrderService;
import com.real.shopping.service.model.OrderModel;
import com.real.shopping.service.model.UserModel;
import org.slf4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: mabin
 * @create: 2019/4/6 14:46
 */
@Controller("order")
@RequestMapping("/order")
@CrossOrigin(allowCredentials = "true",origins={"*"})
public class OrderController extends BaseController {

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private OrderService orderService;
    @Autowired
    private HttpServletRequest request;


    @RequestMapping(value = "/createorder", method = RequestMethod.POST,consumes = CONTENT_TYPE)
    @ResponseBody
    public CommonReturnType createOrder(@RequestParam(name = "itemId") Integer itemId,@RequestParam(name = "amount") Integer amount,
                                        @RequestParam(name = "promoId",required = false) Integer promoId) throws BussinessException {

        Boolean isLogin = (Boolean) this.request.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()){
            throw new BussinessException(EmBussinessError.USER_NOT_LOGIN,"请先登录，再进行购买操作");
        }
        UserModel userModel = (UserModel) this.request.getSession().getAttribute("LOGIN_USER");

        OrderModel orderModel = orderService.createOrder(userModel.getId(),itemId,promoId,amount);
        return CommonReturnType.create(null);
    }
}
