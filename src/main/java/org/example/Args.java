package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;

public class Args {
    public static <T> T parse(Class<T> singleOptionClass, String... args) {
        try {
            Constructor<?> constructor = singleOptionClass.getDeclaredConstructors()[0];
            return (T) constructor.newInstance(true);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
