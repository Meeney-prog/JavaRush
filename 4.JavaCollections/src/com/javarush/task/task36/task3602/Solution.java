package com.javarush.task.task36.task3602;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.lang.reflect.Modifier;
import java.util.Collections;

/* 
Найти класс по описанию
*/
public class Solution {
    public static void main(String[] args) {
        System.out.println(getExpectedClass());
    }

    public static Class getExpectedClass() {
        Class<?>[] class_ = Collections.class.getDeclaredClasses();
        for (Class<?> class__ : class_) {
            if (Modifier.isPrivate(class__.getModifiers()) && Modifier.isStatic(class__.getModifiers())) {
                boolean isThereList = isThereList(class__.getInterfaces());
                Class<?> superClass = class__.getSuperclass();
                while (superClass != null && !isThereList) {
                    isThereList = isThereList(superClass.getInterfaces());
                    superClass = superClass.getSuperclass();
                }
                if (isThereList) {
                    try {
                        Method methodGet = class__.getDeclaredMethod("get", int.class);
                        methodGet.setAccessible(true);
                        Constructor<?> cont = class__.getDeclaredConstructor();
                        cont.setAccessible(true);
                        methodGet.invoke(cont.newInstance(), (int) 1);
                    } catch (NoSuchMethodException | InstantiationException | IllegalAccessException e) {}
                      catch (InvocationTargetException e) {
                        if (e.getCause().getClass().getName().contains("IndexOutOfBoundsException"))
                            return class__;
                    }
                }
            }
        }
        return null;
    }

    public static boolean isThereList(Class<?>[] interfaces) {
        for (Class<?> interf : interfaces)
            if (interf.getSimpleName().equals("List"))
                return true;
        return false;
    }
}
