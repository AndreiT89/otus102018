package ru.annotations;

import java.lang.reflect.Method;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.lang.reflect.InvocationTargetException;

public class Main {
    public static void main(String[] args) {
        List<Object> list = new ArrayList<>();
        FirstTest ft = new FirstTest();
        SecondTest st = new SecondTest();
        ThirdTest tt = new ThirdTest();
        list.add(ft);
        list.add(st);
        list.add(tt);
        launchBefore(list);
    }

    public static void launchBefore(List<?> objs) {
        Iterator<?> itr = objs.listIterator();
        while (itr.hasNext()) {
            Object obj = itr.next();
            Method[] clsMeth = obj.getClass().getDeclaredMethods();
            for (Method meth : clsMeth) {
                if (meth.getAnnotation(Before.class) != null) {
                    try {
                        meth.invoke(obj);
                    } catch (IllegalAccessException ilEx) {
                    } catch (InvocationTargetException invExc) {
                    }

                } else if (meth.getAnnotation(Test.class) != null) {
                    try {
                        meth.invoke(obj);
                    } catch (IllegalAccessException ilEx) {
                    } catch (InvocationTargetException invExc) {
                    }
                } else if (meth.getAnnotation(After.class) != null) {
                    try {
                        meth.invoke(obj);
                    } catch (IllegalAccessException ilEx) {
                    } catch (InvocationTargetException invExc) {
                    }
                }
            }

        }
    }
}