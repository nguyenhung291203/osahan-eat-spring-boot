package org.example.osahaneatspringboot.validator;

import java.lang.reflect.Field;

import org.aspectj.lang.JoinPoint;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Before;
import org.example.osahaneatspringboot.annotation.Trimmed;
import org.springframework.stereotype.Component;

@Aspect
@Component
public class TrimProcessor {

    @Before("execution(* org.example.osahaneatspringboot.controller..*.*(..))")
    public void trimStringsInController(JoinPoint joinPoint) {
        for (Object arg : joinPoint.getArgs()) {
            if (arg != null && arg.getClass().isAnnotationPresent(Trimmed.class)) {
                trimFields(arg);
            }
        }
    }

    private void trimFields(Object obj) {
        Field[] fields = obj.getClass().getDeclaredFields();
        for (Field field : fields) {
            if (field.getType().equals(String.class)) {
                field.setAccessible(true);
                try {
                    String value = (String) field.get(obj);
                    if (value != null) {
                        field.set(obj, value.trim());
                    }
                } catch (IllegalAccessException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
