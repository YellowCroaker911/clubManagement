package com.example.backend.utils;

import lombok.extern.slf4j.Slf4j;

import java.lang.reflect.Field;

@Slf4j
public class CommonUtil {
    /**
     * 检查类是由含有字段的值为null
     * @param obj 要检查的对象
     * @return 有值为null则返回true，否则返回false
     * @param <T> 对象的类型
     */
    public static <T> boolean checkAnyNullField(T obj) {
        if(obj == null)return true;
        Class<?> clazz = obj.getClass();
        Field[] fields = clazz.getDeclaredFields();
        for (Field field : fields) {
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) return true;
            } catch (Exception e){
                log.error(e.toString());
            }
        }
        return false;
    }

    /**
     * 检查类的fields的字段是否含有null
     * @param obj 要检查的对象
     * @param fields 要检查的字段名称
     * @return 有值为null则返回true，否则返回false
     * @param <T> 对象的类型
     * @throws NoSuchFieldException fields中有obj不包含的字段名称
     */
    public static <T> boolean checkAnyNullField(T obj, String... fields) throws NoSuchFieldException {
        if(obj == null) return true;
        Class<?> clazz = obj.getClass();
        for(String fieldName : fields) {
            Field field = clazz.getDeclaredField(fieldName);
            field.setAccessible(true);
            try {
                if (field.get(obj) == null) return true;
            } catch (Exception e){
                log.error(e.toString());
            }
        }
        return false;
    }
}
