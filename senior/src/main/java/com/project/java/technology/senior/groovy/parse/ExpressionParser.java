package com.project.java.technology.senior.groovy.parse;

import groovy.lang.Binding;
import groovy.lang.GroovyShell;

/**
 * @author yinshaobo at 2021/3/20 16:36
 * 表达式解析
 */
public class ExpressionParser {

    public static void main(String[] args) {
        String str1 = "code==\"A\"||code==3";
        String str2 = "if(str1){1}else{\"哈哈\"}";

        Binding binding1 = new Binding();
        binding1.setVariable("code", 3);
        GroovyShell groovyShell1 = new GroovyShell(binding1);
        Object value1 = groovyShell1.evaluate(str1);
        System.out.println("第一步计算结果：" + value1);

        Binding binding2 = new Binding();
        binding2.setVariable("str1", value1);
        GroovyShell groovyShell2 = new GroovyShell(binding2);
        Object value2 = groovyShell2.evaluate(str2);
        System.out.println("第二步计算结果：" + value2);
    }
}
