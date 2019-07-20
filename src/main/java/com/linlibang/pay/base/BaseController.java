package com.linlibang.pay.base;

import com.linlibang.pay.utils.BeanValidators;
import org.apache.commons.collections.CollectionUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintViolation;
import javax.validation.ConstraintViolationException;
import javax.validation.Validator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 描述:
 * 日期: 2018/7/20--10:58
 *
 * @author yanpeicai
 */
public class BaseController {
    private Logger logger = LoggerFactory.getLogger(this.getClass());
    private static final String SEPATATOR = "----->";

    @Autowired
    protected Validator validator;

    /**
     * 参数校验，返回String类型（推荐使用）
     */
    protected String validator(Object object, Class... groups) {
        try {
            BeanValidators.validateWithException(this.validator, object, groups);
            return null;
        } catch (ConstraintViolationException e) {
            List<ConstraintViolation> list = BeanValidators.getErrorMessage(e.getConstraintViolations());
            for (ConstraintViolation exception : list) {
                logger.error(exception.getRootBeanClass().getSimpleName() + SEPATATOR + exception.getPropertyPath() + SEPATATOR + exception.getMessage());
            }
            if (CollectionUtils.isNotEmpty(list)) {
                return list.get(0).getMessage();
            }
            return null;
        }
    }

    /**
     * 参数校验
     */
    protected boolean beanValidator(Object object, Class... groups) {
        try {
            BeanValidators.validateWithException(this.validator, object, groups);
            return true;
        } catch (ConstraintViolationException e) {
            List<ConstraintViolation> list = BeanValidators.getErrorMessage(e.getConstraintViolations());
            for (ConstraintViolation exception : list) {
                logger.error(exception.getRootBeanClass().getSimpleName() + SEPATATOR + exception.getPropertyPath() + SEPATATOR + exception.getMessage());
            }
            return false;
        }
    }


    /**
     * 参数校验，返回错误数据 MAP类型
     */
    protected Map<Object, Object> validatorForMap(Object object, Class... groups) {
        try {
            BeanValidators.validateWithException(this.validator, object, groups);
            return null;
        } catch (ConstraintViolationException e) {
            List<ConstraintViolation> list = BeanValidators.getErrorMessage(e.getConstraintViolations());
            Map<Object, Object> errorMap = new HashMap<>();
            for (ConstraintViolation exception : list) {
                errorMap.put(exception.getPropertyPath(), exception.getMessage());
                logger.error(exception.getRootBeanClass().getSimpleName() + SEPATATOR + exception.getPropertyPath() + SEPATATOR + exception.getMessage());
            }
            return errorMap;
        }
    }

}
