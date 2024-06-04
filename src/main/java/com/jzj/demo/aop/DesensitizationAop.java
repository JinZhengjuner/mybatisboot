package com.jzj.demo.aop;

import cn.hutool.core.util.DesensitizedUtil;
import com.jzj.demo.tool.DesensitizationType;
import com.jzj.demo.tool.TypeEnum;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.springframework.stereotype.Component;

import java.lang.reflect.Field;

@Component
@Aspect
public class DesensitizationAop {

    @Around(value = "@annotation(com.jzj.demo.tool.Desensitization)")
    public Object aroundAdvice(ProceedingJoinPoint joinPoint) throws Throwable {
        // 获取方法参数
        Object proceed = joinPoint.proceed();
        Class<?> aClass = proceed.getClass();
        Field[] declaredFields = aClass.getDeclaredFields();
        for (Field declaredField : declaredFields) {
            declaredField.setAccessible(true);
            if (declaredField.isAnnotationPresent(DesensitizationType.class)){
                DesensitizationType desensitizationType = declaredField.getAnnotation(DesensitizationType.class);
                if (desensitizationType != null){
                    declaredField.setAccessible(true);
                    TypeEnum type = desensitizationType.type();
                    if (type == TypeEnum.CART){
                        declaredField.set(proceed, DesensitizedUtil.desensitized(declaredField.get(proceed).toString(), DesensitizedUtil.DesensitizedType.ID_CARD));
                    }else if (type == TypeEnum.MAIL){
                        declaredField.set(proceed, DesensitizedUtil.desensitized(declaredField.get(proceed).toString(), DesensitizedUtil.DesensitizedType.EMAIL));
                    }else if (type == TypeEnum.PHONE){
                        declaredField.set(proceed, DesensitizedUtil.desensitized(declaredField.get(proceed).toString(), DesensitizedUtil.DesensitizedType.MOBILE_PHONE));
                    }
                }
            }
        }
        return proceed;
    }
    /**
     * // 脱敏处理
     *             String phoneNum = DesensitizedUtil.desensitized(typeResult.getPhoneNum(), DesensitizedUtil.DesensitizedType.MOBILE_PHONE);
     *             typeResult.setPhoneNum(phoneNum);
     *
     *             String idCard = DesensitizedUtil.desensitized(typeResult.getCard(), DesensitizedUtil.DesensitizedType.ID_CARD);
     *             typeResult.setCard(idCard);
     *
     *             String email = DesensitizedUtil.desensitized(typeResult.getEmail(), DesensitizedUtil.DesensitizedType.EMAIL);
     *             typeResult.setEmail(email);
     */

}
