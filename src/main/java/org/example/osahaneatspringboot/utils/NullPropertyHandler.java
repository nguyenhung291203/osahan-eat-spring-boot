package org.example.osahaneatspringboot.utils;

import java.beans.PropertyDescriptor;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class NullPropertyHandler {
    public static void copyNullProperties(Object source, Object target) {
        BeanUtils.copyProperties(source, target, getNullPropertyNames(source));
    }

    private static String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        PropertyDescriptor[] pds = src.getPropertyDescriptors();
        List<String> nullProperty = new ArrayList<>();
        for (PropertyDescriptor pd : pds) {
            Object value = src.getPropertyValue(pd.getName());
            if (value != null) {
                nullProperty.add(pd.getName());
            }
        }
        return nullProperty.toArray(new String[0]);
    }
}
