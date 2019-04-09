package com.real.shopping.controller;

import com.real.shopping.controller.vo.ItemVO;
import com.real.shopping.error.BussinessException;
import com.real.shopping.error.EmBussinessError;
import com.real.shopping.response.CommonReturnType;
import com.real.shopping.service.ItemService;
import com.real.shopping.service.model.ItemModel;
import com.real.shopping.service.model.UserModel;
import com.real.shopping.service.model.UserRoleModel;
import org.joda.time.format.DateTimeFormat;
import org.slf4j.Logger;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author: mabin
 * @create: 2019/4/5 21:50
 */
@Controller
@RequestMapping("/item")
@CrossOrigin(allowCredentials = "true",origins={"*"})
public class ItemController extends BaseController{

    protected final Logger logger = org.slf4j.LoggerFactory.getLogger(getClass());

    @Autowired
    private ItemService itemService;

    @Autowired
    private HttpServletRequest request;

    
    //创建商品
    @RequestMapping(value = "/create",method = RequestMethod.POST,consumes = CONTENT_TYPE)
    @ResponseBody
    public CommonReturnType create(@RequestParam(name = "title") String title,@RequestParam(name = "price") Double price,
                                   @RequestParam(name = "description") String description, @RequestParam(name = "stock") Integer stock,
                                   @RequestParam(name = "imgUrl") String imgUrl) throws BussinessException {
        Boolean isLogin = (Boolean) request.getSession().getAttribute("IS_LOGIN");
        if (isLogin == null || !isLogin.booleanValue()){
            throw new BussinessException(EmBussinessError.USER_NOT_LOGIN);
        }
        UserModel userModel = (UserModel) request.getSession().getAttribute("LOGIN_USER");
        List<UserRoleModel> userRoleModelList = userModel.getRoleModelList();
        if (userRoleModelList == null){
            throw new BussinessException(EmBussinessError.UNKNOW_ERROR);
        }
        List<String> rolesList = new ArrayList<>();
        for (UserRoleModel userRoleModel : userRoleModelList){
            rolesList.add(userRoleModel.getRoleName());
        }
        if (!rolesList.contains("admin")){
            throw new BussinessException(EmBussinessError.USER_NOT_AUTHORIZED);
        }
        ItemModel itemModel = new ItemModel();
        itemModel.setTitle(title);
        itemModel.setPrice(new BigDecimal(price));
        itemModel.setStock(stock);
        itemModel.setDescription(description);
        itemModel.setImgUrl(imgUrl);

        ItemModel itemModelForReturn = itemService.createItem(itemModel);
        ItemVO itemVO = convertVOFromModel(itemModelForReturn);
        return CommonReturnType.create(itemVO);
    }

    //商品列表页面浏览
    @RequestMapping(value = "/list",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType listItem(){
        List<ItemModel> itemModelList = itemService.listItem();
        List<ItemVO> itemVOList = itemModelList.stream().map(itemModel -> {
            ItemVO itemVO = this.convertVOFromModel(itemModel);
            return itemVO;
        }).collect(Collectors.toList());
        return CommonReturnType.create(itemVOList);
    }

    private ItemVO convertVOFromModel(ItemModel itemModel) {
        if (itemModel == null){
            return null;
        }
        ItemVO itemVO = new ItemVO();
        BeanUtils.copyProperties(itemModel,itemVO);
        if (itemModel.getPromoModel()!=null){
            itemVO.setPromoStatus(itemModel.getPromoModel().getStatus());
            itemVO.setPromoId(itemModel.getPromoModel().getItemId());
            itemVO.setPromoPrice(itemModel.getPromoModel().getPromoItemPrice());
            itemVO.setStartDate(itemModel.getPromoModel().getStartDate().toString(DateTimeFormat.forPattern("yyyy-MM-dd HH:mm:ss")));
        }
        return itemVO;
    }

//    商品详情页面浏览
    @RequestMapping(value = "/get",method = RequestMethod.GET)
    @ResponseBody
    public CommonReturnType getItem(@RequestParam(name = "id") Integer id){
        ItemModel itemModel = itemService.getItemById(id);
        if (itemModel == null) {
            return null;
        }
        ItemVO itemVO = convertVOFromModel(itemModel);

        return CommonReturnType.create(itemVO);
    }

}
