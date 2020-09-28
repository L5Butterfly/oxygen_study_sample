/**
 * 
 */
package com.oxygen.redislockstudy.utils;

import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

/**
 * @Component spring上下文工具,加载到spring容器，否则调用失败
 * @author empathy
 * @time 20190921
 */
@Component
public class AppContextUtil implements ApplicationContextAware {

	private static ApplicationContext appCtx; 
	
	@Override
	public void setApplicationContext(ApplicationContext arg0) throws BeansException {
		appCtx = arg0;
	}

	/**
	 *  
	 * @return
	 */
    public static ApplicationContext getApplicationContext(){  
        return appCtx;
    }  
      
    /**  
     * 这是一个便利的方法，帮助我们快速得到一个BEAN  
     * @param beanName bean的名字  
     * @return 返回一个bean对象  
     * @author xgf 
     */    
    public static Object getBean( String beanName ) {    
        return appCtx.getBean( beanName );    
    }    
    
    /**
     * 通过类型获得BEAN
     * @param cls
     * @return
     */
    public static <T> T getBean(Class<T> cls){
        return appCtx.getBean(cls); 
    }
}
