package com.jzj.demo.entity;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.config.BeanDefinition;
import org.springframework.beans.factory.support.RootBeanDefinition;

import java.lang.reflect.Field;
import java.util.*;
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

    //三级缓存:为了解耦，代码更清晰
    private static Map<String, ObjectFactory> singletonFactories = new ConcurrentHashMap<>();

    //循环依赖的标识
    private static Set<String> singletonCurrentlyCreating = new HashSet<>();

    //假设A 使用了Aop，要给A创建动态代理
    public static Object getBean(String beanName) throws Exception{
        Object singleton = getSingleton(beanName);
        if (singleton != null){
            return singleton;
        }

        if (!singletonCurrentlyCreating.contains(beanName)){
            singletonCurrentlyCreating.add(beanName);
        }
        //实例化
        RootBeanDefinition beanDefinition = (RootBeanDefinition) beanDefinitionMap.get(beanName);
        Class<?> beanClass = beanDefinition.getBeanClass();
        Object instanceBean = beanClass.newInstance();

//        singletonFactories.put(beanName,() -> new JdkProxyBeanPostProcessor().getEarlyBeanReference(instanceBean, beanName));

        //这里二级缓存就不存了，万一对象还不是代理对象，到时候取到的就不是代理对象
//        earlySingletonObject.put(beanName, instanceBean);


        //创建动态代理 （不是耦合的方式， beanPostProcessor）
        //添加到二级缓存，spring在初始化之后也调用了后置处理器生成动态代理
        //spring在哪里创建动态代理？   1.正常情况是在初始化之后 2.出现了循环依赖，在实例化之后调用
        //spring还是希望在初始化后再创建，包括循环依赖 需要判断当前是不是循环依赖
        //getBean这里使用后置处理器，不太符合我们spring的生命周期规则，后置处理器都是在create()的时候调用的


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
        //添加到一级缓存  若在这里创建aop，已经晚了，A里面的B就不是动态代理
        if (earlySingletonObject.containsKey(beanName)){
            instanceBean = earlySingletonObject.get(beanName);
        }
        singletonObjects.put(beanName, instanceBean);
        return instanceBean;
    }

    public static Object getSingleton(String beanName){
        Object bean = singletonObjects.get(beanName);
        //一级缓存没有，正在创建，就是循环依赖
        if (bean ==null && singletonCurrentlyCreating.contains(beanName)){

            //从二级缓存中去拿
            if (earlySingletonObject.containsKey(beanName)){
                return earlySingletonObject.get(beanName);
            }
            //从三级缓存拿
            ObjectFactory factory = singletonFactories.get(beanName);
            if (factory != null){
                bean = factory.getObject();
                earlySingletonObject.put(beanName, bean);
            }
        }
        return bean;
    }
}
