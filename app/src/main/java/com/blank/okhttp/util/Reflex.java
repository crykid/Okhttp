package com.blank.okhttp.util;

import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by blank on 2017/4/9.
 */

public class Reflex {
    /**
     * 根据属性名获取属性值
     */
    private Object getFieldValueByName(String fieldName, Object o) {
        try {

            //获取名称第一个字符
            String firstLetter = fieldName.substring(0, 1).toUpperCase();

            //拼接该属性的get方法
            String getter = "get" + firstLetter + fieldName.substring(1);

            //得到改对象的get方法
            Method method = o.getClass().getMethod(getter, new Class[]{});

            //调用该方法并得到返回值
            Object value = method.invoke(o, new Object[]{});

            return value;
        } catch (Exception e) {
            System.out.print("no such character :"+fieldName);
            return null;
        }
    }


    /**
     * 获取属性名数组
     */
    private String[] getFiledName(Object o) {

        //得到当前对象下的所有属性
        Field[] fields = o.getClass().getDeclaredFields();

        //声明一个String数组保存当前对象下的所有属性名称
        String[] fieldNames = new String[fields.length];

        //遍历所有属性
        for (int i = 0; i < fields.length; i++) {
            //打印属性类型
            System.out.println(fields[i].getType());

            //获取属性名称
            fieldNames[i] = fields[i].getName();
        }

        return fieldNames;
    }


    /**
     * 获取属性类型(type)，属性名(name)，
     * 属性值(value)的map组成的list
     */
    private List getFiledsInfo(Object o) {
        //得到当前对下的所有属性
        Field[] fields = o.getClass().getDeclaredFields();

        //声明list集合保存所有属性信息,list中的元素是map
        List list = new ArrayList();

        //声明map保存单个属性信息
        Map infoMap = null;

        //遍历所有属性
        for (int i = 0; i < fields.length; i++) {

            //实例化map
            infoMap = new HashMap();

            //得到属性类型
            infoMap.put("type", fields[i].getType().toString());

            //得到属性名称
            infoMap.put("name", fields[i].getName());

            //调用上面的getFieldValueByName方法得到属性值
            infoMap.put("value", getFieldValueByName(fields[i].getName(), o));

            //保存到list集合中
            list.add(infoMap);
        }
        return list;
    }

    /**
     * 获取对象的所有属性值，返回一个对象数组
     **/
    public Object[] getFiledValues(Object o) {

        //调用上面getFiledName方法获取当前对象下的所有属性名
        String[] fieldNames = this.getFiledName(o);

        //声明一个Object数组保存所有属性值
        Object[] value = new Object[fieldNames.length];

        //遍历
        for (int i = 0; i < fieldNames.length; i++) {

            //调用上面getFieldValueByName方法获取当前属性的值
            value[i] = this.getFieldValueByName(fieldNames[i], o);

        }
        return value;
    }


}
