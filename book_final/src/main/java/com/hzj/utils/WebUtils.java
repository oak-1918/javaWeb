package com.hzj.utils;

import org.apache.commons.beanutils.BeanUtils;

import javax.servlet.http.HttpServletRequest;
import java.lang.reflect.InvocationTargetException;
import java.util.Map;

/**
 * @author hzj
 * @create 2022-02-27 10:29
 */
public class WebUtils {
    public static <T> T copyParamToBean(Map value, T bean){
        try {
            BeanUtils.populate(bean,value);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return bean;
    }
    /**
     * 将字符串转换成为 int 类型的数据
     * @param strInt
     * @param defaultValue
     * @return
     */
    public static int parseInt(String strInt,int defaultValue){
//        if(strInt==null)
//            return defaultValue;
        try {
            return Integer.parseInt(strInt);
        } catch (Exception e) {
            //e.printStackTrace();
        }
        return defaultValue;

    }
}
