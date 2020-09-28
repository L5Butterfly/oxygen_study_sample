package com.oxygen.redislockstudy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;


 /**
  * Spring工具类
  * @author Administrator
  * @date 2020/9/28 17:32
  * @created by oxygen
  */
@Component
public class SpringUtil implements ApplicationContextAware {

     /**
      * Spring上下文
      */
    private static  ApplicationContext applicationContext;

    @Override
    public void setApplicationContext(ApplicationContext appContext) throws BeansException {
        if(applicationContext == null) {
            applicationContext= appContext;
        }
    }

    /**
     * 获取applicationContext
     * @return
     */
    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    /**
     * 通过name获取Bean.
     * @param beanName
     * @return
     */
    public  static Object getBean(String beanName){
        return getApplicationContext().getBean(beanName);
    }

    /**
     * 通过Class获取Bean
     * @param clazz
     * @param <T>
     * @return
     */
    public static  <T> T getBean(Class<T> clazz){
        return getApplicationContext().getBean(clazz);
    }

    /**
     * 通过name以及Clazz返回指定的Bean
     * @param name
     * @param clazz
     * @param <T>
     * @return
     */
    public  static <T> T getBean(String name, Class<T> clazz){
        return getApplicationContext().getBean(name, clazz);
    }
}
