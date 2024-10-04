package com.urise.webapp.reflection;

import com.urise.webapp.model.ResumeAnnotation;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.UUID;

public class ReflectionChecker {
    public void showClassName(Object object) {
        Class<?> clazz = object.getClass();
        System.out.println(clazz.getName());
    }
    public void showClassFields(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields =  clazz.getDeclaredFields();
        for(Field field : fields) {
            System.out.println(field.getName());
        }
    }

    public void showClassMethods(Object object) {
        Class<?> clazz = object.getClass();
        Method[] methods =  clazz.getDeclaredMethods();
        for(Method method : methods) {
            System.out.println(method.getName());
        }
    }
    public void showFieldsAnnotations(Object object) {
        Class<?> clazz = object.getClass();
        Field[] fields =  clazz.getDeclaredFields();
        for(Field field : fields) {
            Annotation[] annotations = field.getAnnotations();
            for (Annotation annotation : annotations) {
                System.out.println(annotation.toString());
            }
        }
    }
    public  void fillPrivateFields(Object object) throws IllegalAccessException, IllegalArgumentException {
        Class<?> clazz = object.getClass();
        Field[] fields =  clazz.getDeclaredFields();
        for(Field field : fields) {
            Annotation annotation = field.getAnnotation(ResumeAnnotation.class);
            if (annotation != null) {
                field.setAccessible(true);
                field.set(object, UUID.randomUUID().toString());
                field.setAccessible(false);
            }
        }
    }
    public Object createNewObject(Object object) throws InstantiationException, IllegalAccessException {
        Class<?> clazz = object.getClass();
        return clazz.newInstance();
    }
}

