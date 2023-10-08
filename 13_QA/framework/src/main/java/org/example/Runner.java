package org.example;

import org.example.annotation.After;
import org.example.annotation.Before;
import org.example.annotation.RunWithCustomTestFrameworkRunner;
import org.example.annotation.Test;
import org.reflections.Reflections;
import org.reflections.scanners.Scanners;

import java.lang.annotation.Annotation;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Set;

public class Runner {
    private static int testsPassed = 0;
    private static int testsFailed = 0;

    public static void run(String packageName) {
        Reflections reflections = new Reflections(packageName, Scanners.TypesAnnotated);
        Set<Class<?>> classes = reflections.getTypesAnnotatedWith(RunWithCustomTestFrameworkRunner.class);
        for (Class<?> clazz : classes) {
            Reflections classReflections = new Reflections(clazz, Scanners.MethodsAnnotated);
            extracted(clazz, classReflections, Before.class);
            extracted(clazz, classReflections, Test.class);
            extracted(clazz, classReflections, After.class);
        }
        System.out.println("\nTests passed: "+ testsPassed +", Tests failed: "+ testsFailed);
    }

    private static void extracted(Class<?> clazz, Reflections reflections, Class<? extends Annotation> annotationClass) {
        Set<Method> methods = reflections.getMethodsAnnotatedWith(annotationClass);
        methods.forEach(m -> {
            try {
                m.invoke(clazz.getConstructor().newInstance());
                System.out.println("Test " + m.getName() + " passed\n");
                testsPassed++;
            } catch (InvocationTargetException e){
                if (e.getCause() instanceof AssertionException) {
                    System.out.println("Test " + m.getName() + " failed: " + e.getCause().getMessage() + "\n");
                    testsFailed++;
                }
            } catch(Exception e) {
                e.printStackTrace();
            }
        });
    }


}
