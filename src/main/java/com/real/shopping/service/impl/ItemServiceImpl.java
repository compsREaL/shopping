package com.real.shopping.service.impl;

import com.real.shopping.dao.ItemDOMapper;
import com.real.shopping.dao.ItemStockDOMapper;
import com.real.shopping.dao.PromoDOMapper;
import com.real.shopping.dataobject.ItemDO;
import com.real.shopping.dataobject.ItemStockDO;
import com.real.shopping.error.BussinessException;
import com.real.shopping.error.EmBussinessError;
import com.real.shopping.service.ItemService;
import com.real.shopping.service.PromoService;
import com.real.shopping.service.model.ItemModel;
import com.real.shopping.service.model.PromoModel;
import com.real.shopping.validator.ValidatorImpl;
import com.real.shopping.validator.ValidatorResult;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.prefs.BackingStoreException;
import java.util.stream.Collectors;

/**
 * @author: mabin
 * @create: 2019/4/5 20:44
 */
@Service
public class ItemServiceImpl implements ItemService {

    @Autowired
    private ItemDOMapper itemDOMapper;

    @Autowired
    private ItemStockDOMapper itemStockDOMapper;

    @Autowired
    private PromoService promoService;

    @Autowired
    private ValidatorImpl validator;

    @Override
    public ItemModel createItem(ItemModel itemModel) throws BussinessException {
        if (itemModel == null) {
            return null;
        }
        ValidatorResult validatorResult = validator.validate(itemModel);
        if (validatorResult.isHasErrors()){
            throw new BussinessException(EmBussinessError.PARAM_VALIDATION_ERRPR,validatorResult.getErrMsg());
        }

        ItemDO itemDO = convertDOFromModel(itemModel);
        itemDOMapper.insertSelective(itemDO);
        itemModel.setId(itemDO.getId());

        ItemStockDO itemStockDO = convertItemStockFromModel(itemModel);
        itemStockDOMapper.insertSelective(itemStockDO);
        return itemModel;
    }

    private ItemStockDO convertItemStockFromModel(ItemModel itemModel) {
        if (itemModel == null){
            return null;
        }
        ItemStockDO itemStockDO = new ItemStockDO();
        itemStockDO.setStock(itemModel.getStock());
        itemStockDO.setItemId(itemModel.getId());
        return itemStockDO;
    }

    private ItemDO convertDOFromModel(ItemModel itemModel) {
        if (itemModel == null){
            return null;
        }
        ItemDO itemDO = new ItemDO();
        BeanUtils.copyProperties(itemModel,itemDO);
        itemDO.setPrice(itemModel.getPrice().doubleValue());
        return itemDO;
    }

    @Override
    public List<ItemModel> listItem() {
        List<ItemDO> itemDOList = itemDOMapper.listItem();
        List<ItemModel> itemModelList = itemDOList.stream().map(itemDO -> {
            ItemStockDO itemStockDO = itemStockDOMapper.selectByItem(itemDO.getId());
            ItemModel itemModel = convertModelFromDO(itemDO,itemStockDO);
            return itemModel;
        }).collect(Collectors.toList());
        return itemModelList;
    }

    private ItemModel convertModelFromDO(ItemDO itemDO, ItemStockDO itemStockDO) {
        ItemModel itemModel = new ItemModel();
        BeanUtils.copyProperties(itemDO,itemModel);
        itemModel.setPrice(new BigDecimal(itemDO.getPrice()));
        itemModel.setStock(itemStockDO.getStock());
        return itemModel;
    }

    @Override
    public ItemModel getItemById(Integer id) {
        ItemDO itemDO = itemDOMapper.selectByPrimaryKey(id);
        if (itemDO == null){
            return null;
        }

        ItemStockDO itemStockDO = itemStockDOMapper.selectByItem(id);
        ItemModel itemModel = convertModelFromDO(itemDO,itemStockDO);

        PromoModel promoModel = promoService.getByItemId(id);
        if (promoModel!=null && promoModel.getStatus()!=3){
            itemModel.setPromoModel(promoModel);
        }
        return itemModel;
    }

    @Override
    public boolean decreaseStock(Integer itemId, Integer amount) {
        int row = itemStockDOMapper.decreaseStock(itemId,amount);
        if (row>0){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public void increaseSales(Integer itemId, Integer amount) {
        itemDOMapper.increaseSales(itemId,amount);
    }
}
