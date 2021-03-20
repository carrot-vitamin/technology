package com.project.java.technology.senior.groovy.parse;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author za-yinshaobo at 2021/3/20 16:36
 * 表达式解析
 */
public class ExpressionParser {

    public static void main(String[] args) {
        String str1 = "1 + 2 * 3";
        GroovyShell groovyShell1 = new GroovyShell();
        Object value1 = groovyShell1.evaluate(str1);
        System.out.println(value1);


        String str2 = "A + B * C";
        Binding binding = new Binding();
        binding.setVariable("A", 1);
        binding.setVariable("B", 2);
        binding.setVariable("C", 3);
        GroovyShell groovyShell2 = new GroovyShell(binding);
        Object value2 = groovyShell2.evaluate(str2);
        System.out.println(value2);
    }
}
