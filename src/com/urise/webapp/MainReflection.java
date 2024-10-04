package com.urise.webapp;

import com.urise.webapp.model.Resume;
import com.urise.webapp.reflection.ReflectionChecker;

import java.lang.reflect.Field;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;

public class MainReflection {
    public static void main(String[] args) throws IllegalAccessException {
        Resume r = new Resume();
        Field field = r.getClass().getDeclaredFields()[0];
        field.setAccessible(true);
        System.out.println(field.getName());
        System.out.println(field.get(r));
        field.set(r, "new_uuid");
        field.setAccessible(false);
        // TODO : invoke r.toString via reflection
// вызов доступного метода
        try {
            Method toString = r.getClass().getDeclaredMethod("toString");
            System.out.println(toString.invoke(r));
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }
// вызов недоступного метода

        try {
            Method toPrint = r.getClass().getDeclaredMethod("toPrint", boolean.class);
            toPrint.setAccessible(true);
            System.out.println(toPrint.invoke(r, true));
            toPrint.setAccessible(false);
        } catch (NoSuchMethodException | IllegalAccessException | InvocationTargetException e) {
            e.printStackTrace();
        }

        System.out.println(r);

        // additional examples
        ReflectionChecker checker = new ReflectionChecker();
        System.out.println("Полное имя класса");
        checker.showClassName(r);
        System.out.println("\nИмена переменных:");
        checker.showClassFields(r);
        System.out.println("\nИмена методов:");
        checker.showClassMethods(r);
        System.out.println("\nИмена аннотаций перед полями:");
        checker.showFieldsAnnotations(r);

        System.out.println("\nПриватный идентификатор объекта класса :");
        System.out.println(r.getUuid());
        System.out.println("Изменяем с помощью Reflection");
        System.out.println("(если поле помечено аннотацией @ResumeAnnotation):");
        try {
            checker.fillPrivateFields(r);
        } catch (IllegalAccessException | IllegalArgumentException e) {
            System.out.println(e.getMessage());
        }
        System.out.println(r.getUuid());

        System.out.println("\nСоздаю клон объекта через Reflection:");

        try {
            Object clone = checker.createNewObject(r);
            checker.showClassName(clone);
        } catch (IllegalAccessException | InstantiationException e) {
            System.out.println(e.getMessage());
        }
    }
}
