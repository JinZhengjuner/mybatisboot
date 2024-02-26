package com.jzj.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class MainStart {
    private static Map<String, BeanDefinition> beanDefinitionMap = new ConcurrentHashMap<>();

    public static void loadBeanDefinitions(){
        RootBeanDefinition aBeanDefinition = new RootBeanDefinition(InstanceA.class);
        RootBeanDefinition bBeanDefinition = new RootBeanDefinition(InstanceB.class);
        beanDefinitionMap.put("instanceA", aBeanDefinition);
        beanDefinitionMap.put("instanceB", bBeanDefinition);

    }
    public static void main(String[] args) throws Exception {
        loadBeanDefinitions();
        String beanName = "com.jzj.demo.entity.InstanceA";

        //循环创建Bean
        for (String key : beanDefinitionMap.keySet()) {
            //先创建A
            getBean(key);
        }
        InstanceA a = (InstanceA) getBean("instanceA");
        a.say();
    }


    // 一级缓存 单例池   成熟态Bean
    private static Map<String, Object> singletonObjects = new ConcurrentHashMap<>(256);

    //二级缓存：为了将成熟Bean，和纯净Bean分离开来，避免读取到不完整的bean
    private static Map<String, Object> earlySingletonObject = new ConcurrentHashMap<>();

    public static Object getBean(String beanName) throws Exception{
        Object singleton = getSingleton(beanName);
        if (singleton != null){
            return singleton;
        }
        //实例化
        RootBeanDefinition beanDefinition = (RootBeanDefinition) beanDefinitionMap.get(beanName);
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object instanceBean = beanClass.newInstance();

        //添加到一级缓存
        earlySingletonObject.put(beanName, instanceBean);

        //属性赋值
        for (Field declaredField : beanClass.getDeclaredFields()) {
            Autowired annotation = declaredField.getAnnotation(Autowired.class);
            if (annotation != null){
                declaredField.setAccessible(true);
                //byName byType ByConstract
                //instanceB
                String fieldName = declaredField.getName();
                Object fieldObject = getBean(fieldName);
                declaredField.set(instanceBean, fieldObject);
            }
        }
        //初始化  inil.... initMethod @postConstract
        //添加到一级缓存
        singletonObjects.put(beanName, instanceBean);
        return instanceBean;
    }

    public static Object getSingleton(String beanName){
        if (singletonObjects.containsKey(beanName)){
            return singletonObjects.get(beanName);
        }else if (earlySingletonObject.containsKey(beanName)){
            return earlySingletonObject.get(beanName);
        }
        return null;
    }
}
