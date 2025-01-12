package org.example;

import java.lang.reflect.Constructor;
import java.lang.reflect.Parameter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class Args {
    public static <T> T parse(Class<T> singleOptionClass, String... args) {
        try {
            Constructor<?> constructor = singleOptionClass.getDeclaredConstructors()[0];
            Parameter[] parameters = constructor.getParameters();
            List<String> arguments = Arrays.asList(args);

            Object[] argsForConstructor = Arrays.stream(parameters)
                    .map(parameter -> value(parameter, arguments))
                    .toArray();
            return (T) constructor.newInstance(argsForConstructor);
        } catch (Exception e) {
            throw new RuntimeException(e);
        }

    }

    private static Object value(Parameter parameter, List<String> arguments) {
        Option option = parameter.getAnnotation(Option.class);
        Class<?> type = parameter.getType();
        Object o;
        if (type.equals(int.class)) {
            int idx = arguments.indexOf("-" + option.value());
            String s = arguments.get(idx + 1);
            o = Integer.valueOf(s);
        } else if (type.equals(String.class)){
            int idx = arguments.indexOf("-" + option.value());
            String s = arguments.get(idx + 1);
            o = String.valueOf(s);
        } else {
            boolean contains = arguments.contains("-" + option.value());
             o = contains;
        }
        return o;
    }
}
