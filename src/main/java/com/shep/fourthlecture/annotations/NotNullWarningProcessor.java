package com.shep.fourthlecture.annotations;

import java.lang.reflect.Field;

public class NotNullWarningProcessor {
    public static void processAnnotations(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.isAnnotationPresent(NotNull.class)) {
                field.setAccessible(true);
                try {
                    if (field.get(obj) == null) {
                        System.out.println("Variable [" + field.getName() + "] is null in [" + obj + "]!");
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
