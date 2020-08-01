package com.cqjtu.smartorder.util;

import org.springframework.cglib.beans.BeanCopier;
import org.springframework.cglib.core.Converter;

import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

/**
 * @author mumu
 * @date 2020/7/24
 */
public class BeanUtils {

    private BeanUtils() {}

    public static Object hasMethodNoCatch(Object source, String methodName) throws InvocationTargetException, IllegalAccessException {
        Method[] methods = source.getClass().getMethods();
        for (Method method : methods) {
            if (method.getName().equals(methodName)) {
                return method.invoke(source);
            }
        }
        return null;
    }

    public static Object hasMethod(Object source, String methodName) {
        try {
            return hasMethodNoCatch(source, methodName);
        } catch (InvocationTargetException | IllegalAccessException e) {
            return null;
        }
    }

    public static Object hasGetIdMethod(Object source) {
        return hasMethod(source, "getId");
    }
}
