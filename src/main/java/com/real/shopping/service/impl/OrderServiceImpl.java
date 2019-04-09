package com.real.shopping.service.impl;

import com.real.shopping.dao.OrderDOMapper;
import com.real.shopping.dao.SequenceDOMapper;
import com.real.shopping.dataobject.OrderDO;
import com.real.shopping.dataobject.SequenceDO;
import com.real.shopping.error.BussinessException;
import com.real.shopping.error.EmBussinessError;
import com.real.shopping.service.ItemService;
import com.real.shopping.service.OrderService;
import com.real.shopping.service.UserService;
import com.real.shopping.service.model.ItemModel;
import com.real.shopping.service.model.OrderModel;
import com.real.shopping.service.model.UserModel;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

/**
 * @author: mabin
 * @create: 2019/4/6 15:20
 */
@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private OrderDOMapper orderDOMapper;
    @Autowired
    private UserService userService;
    @Autowired
    private ItemService itemService;
    @Autowired
    private SequenceDOMapper sequenceDOMapper;

    @Override
    @Transactional
    public OrderModel createOrder(Integer userId, Integer itemId,Integer promoId, Integer amount) throws BussinessException {
        UserModel userModel = userService.getUserById(userId);
        if (userModel == null){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,"用户不存在");
        }
        ItemModel itemModel = itemService.getItemById(itemId);
        if (itemModel == null){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,"商品不存在");
        }
        if (amount<=0){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,"购买数量不能小于等于0，请填写正确的购买数量");
        }
        //减库存操作
        if (!itemService.decreaseStock(itemId,amount)){
            throw new BussinessException(EmBussinessError.STOCK_NOT_ENOUGH);
        }

        if (promoId != null){
            //校验活动信息是否正确
            if (itemId != itemModel.getPromoModel().getItemId()){
                throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,"活动信息不正确");
            } else if (itemModel.getPromoModel().getStatus()!=2){
                throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,"秒杀活动尚未开始");
            }
        }

        //生成订单
        OrderModel orderModel = new OrderModel();
        orderModel.setUserId(userId);
        orderModel.setItemId(itemId);
        if (promoId != null) {
            orderModel.setPrice(itemModel.getPromoModel().getPromoItemPrice());
        }else {
            orderModel.setPrice(itemModel.getPrice());
        }
        orderModel.setPromoId(promoId);
        orderModel.setAmount(amount);
        BigDecimal orderPrice = itemModel.getPrice().multiply(new BigDecimal(amount));
        orderModel.setOrderPrice(orderPrice);
        //生成订单号
        orderModel.setId(generateOrderNum());

        OrderDO orderDO = convertDOFromModel(orderModel);
        orderDOMapper.insertSelective(orderDO);
        //增加销量
        itemService.increaseSales(itemId,amount);
        return orderModel;
    }

    private OrderDO convertDOFromModel(OrderModel orderModel) {
        if (orderModel == null){
            return null;
        }
        OrderDO orderDO = new OrderDO();
        BeanUtils.copyProperties(orderModel,orderDO);
        orderDO.setItemPrice(orderModel.getPrice().doubleValue());
        orderDO.setOrderPrice(orderModel.getOrderPrice().doubleValue());
        return orderDO;
    }

    //保证sequenceNum全局唯一性
    @Transactional
    private String generateOrderNum(){
        StringBuilder orderNum = new StringBuilder();
        //生成16位订单单号，前8位代表下单时间
        LocalDateTime now = LocalDateTime.now();
        String nowDate = now.format(DateTimeFormatter.ISO_DATE).replace("-","");
        orderNum.append(nowDate);
        //中间6位代表自增序列
        SequenceDO sequenceDO = sequenceDOMapper.selectByPrimaryKey("order_info");
        int sequence = sequenceDO.getCurrentValue();
        int step = sequenceDO.getStep();
        sequenceDOMapper.updateSequenceByName("order_info");

        String sequenceStr = String.valueOf(sequence);
        for (int i=6;i>sequenceStr.length();i--){
            orderNum.append(0);
        }
        orderNum.append(sequenceStr);
        //最后两位一般为分库分表位，使得订单可以被拆分至100个库对应的100个表中，以减轻数据库查询和落单压力
        orderNum.append("00");
        return orderNum.toString();
    }
}
