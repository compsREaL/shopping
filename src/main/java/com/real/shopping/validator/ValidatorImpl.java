package com.real.shopping.validator;

import org.springframework.beans.factory.InitializingBean;
import org.springframework.stereotype.Component;

import javax.validation.Constraint;
import javax.validation.ConstraintViolation;
import javax.validation.Validation;
import javax.validation.Validator;
import java.util.Set;

/**
 * @author: mabin
 * @create: 2019/4/8 9:16
 */
@Component
public class ValidatorImpl implements InitializingBean {

    private Validator validator;

    //实现校验方法并返回校验结果
    public ValidatorResult validate(Object bean){
        ValidatorResult validatorResult = new ValidatorResult();

        Set<ConstraintViolation<Object>> set = validator.validate(bean);

        if (set.size()>0){
            validatorResult.setHasErrors(true);
            set.forEach(objectConstraintViolation -> {
                String errMsg = objectConstraintViolation.getMessage();
                String propertyName = objectConstraintViolation.getPropertyPath().toString();
                validatorResult.getErrMsgMap().put(propertyName,errMsg);
            });
        }
        return validatorResult;
    }

    @Override
    public void afterPropertiesSet() throws Exception {
        this.validator = Validation.buildDefaultValidatorFactory().getValidator();
    }
}
