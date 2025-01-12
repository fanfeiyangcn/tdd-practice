package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;

public class Args {
    public static <T> T parse(Class<T> singleOptionClass, String... args) {
        try {
            Constructor<?> constructor = singleOptionClass.getDeclaredConstructors()[0];
            Parameter parameter = constructor.getParameters()[0];
            Option option = parameter.getAnnotation(Option.class);
            List<String> arguments = Arrays.asList(args);

            Class<?> type = parameter.getType();
            if (type.equals(int.class)) {
                int idx = arguments.indexOf("-" + option.value());
                String s = arguments.get(idx + 1);
                return (T) constructor.newInstance(Integer.valueOf(s));
            } if (type.equals(String.class)){
                int idx = arguments.indexOf("-" + option.value());
                String s = arguments.get(idx + 1);
                return (T) constructor.newInstance(String.valueOf(s));

            }else {
                boolean contains = arguments.contains("-" + option.value());
                return (T) constructor.newInstance(contains);
            }
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }
}
