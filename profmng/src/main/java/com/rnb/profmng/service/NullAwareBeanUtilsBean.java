package com.rnb.profmng.service;

import java.lang.reflect.InvocationTargetException;

import org.apache.commons.beanutils.BeanUtilsBean;

public class NullAwareBeanUtilsBean extends BeanUtilsBean {
	
    @Override
    public void copyProperty(Object dest, String name, Object value)
            throws IllegalAccessException, InvocationTargetException {
        if (value != null) {
            if (value instanceof String && ((String) value).trim().isEmpty()) {
                // 빈 문자열이면 복사하지 않음
                return;
            }
            super.copyProperty(dest, name, value);
        }
    }
}
