package com.real.shopping.service;

import com.real.shopping.error.BussinessException;
import com.real.shopping.service.model.ItemModel;

import java.util.List;

/**
 * @author: mabin
 * @create: 2019/4/5 20:33
 */
public interface ItemService {


    //创建商品
    ItemModel createItem(ItemModel itemModel) throws BussinessException;

    //商品列表
    List<ItemModel> listItem();

    //查询单个商品详情
    ItemModel getItemById(Integer id);

    //商品库存量减少
    boolean decreaseStock(Integer itemId,Integer amount);

    //商品销量增加
    void increaseSales(Integer itemId,Integer amount);
}
