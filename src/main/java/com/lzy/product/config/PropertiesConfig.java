package com.lzy.product.config;

import org.springframework.beans.factory.config.PropertiesFactoryBean;
import org.springframework.boot.context.event.ApplicationReadyEvent;
import org.springframework.context.ApplicationEvent;
import org.springframework.context.ApplicationListener;
import org.springframework.core.io.Resource;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Properties;

/**
 * @author ：lzy
 * @date ：Created in 2019/3/14 13:37
 * @description：
 * @modified By：
 */
@Component
public class PropertiesConfig implements ApplicationListener{

    private static Map<String,String> aliPropertiesMap = new HashMap<>();

    //获取配置参数值方法
    public static String getKey(String key){
        return   aliPropertiesMap.get(key);
    }

    @Override
    public void onApplicationEvent(ApplicationEvent applicationEvent) {
        if ( applicationEvent instanceof ApplicationReadyEvent){
            init(aliPropertiesMap);
        }
    }

    public void  init(Map<String,String> map){
        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();
        try {
            Resource resource = resolver.getResource("classpath:config/alipay.properties");
            PropertiesFactoryBean config = new PropertiesFactoryBean();
            config.setLocation(resource);
            config.afterPropertiesSet();
            Properties props = config.getObject();
            System.out.println(props);

           props.stringPropertyNames().stream().forEach(key ->{
               map.put(key, String.valueOf(props.get(key)));
           });

        }catch (Exception e){
            new Exception("配置文件加载失败");
        }
    }





}
