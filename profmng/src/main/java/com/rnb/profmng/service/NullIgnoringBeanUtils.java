package com.rnb.profmng.service;

import java.beans.PropertyDescriptor;

import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;

public class NullIgnoringBeanUtils {

    public static void copyNonNullProperties(Object source, Object target) {
        BeanWrapper src = new BeanWrapperImpl(source);
        BeanWrapper tgt = new BeanWrapperImpl(target);

        for (PropertyDescriptor pd : src.getPropertyDescriptors()) {
            String name = pd.getName();
            Object value = src.getPropertyValue(name);

            // id, class 등의 특수 필드는 건너뜀
            if ("class".equals(name) || "id".equals(name)) continue;

            if (value != null && tgt.isWritableProperty(name)) {
                tgt.setPropertyValue(name, value);
            }
        }
    }
}
