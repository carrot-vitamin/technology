package com.project.java.technology.senior.groovy;

import groovy.lang.Binding;
import org.springframework.beans.BeansException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.Map;

/**
 * @author ShaoBo Yin at 2020/12/11 17:41
 * 需要注意的是：上面这种方法注册的到binding中beanMap是不包含groovyBinding这个对象本身的（先后顺序的原因），如果需要将binding对象本身（也是一个bean）注册，也很简单，
 * 只需要将Binding的bean生成放在GroovyBindingConfig之前，并且在实现ApplicationContextAware接口的setApplicationContext方法中进行variables的设置即可，
 * 但建议不这样做，因为这样就可以通过脚本对Binding对象本身造成破坏，不太优雅~
 */
@Configuration
public class GroovyBindingConfig implements ApplicationContextAware {

    private ApplicationContext context;

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        this.context = applicationContext;
    }

    @Bean("groovyBinding")
    public Binding groovyBinding() {
        Binding groovyBinding = new Binding();

        Map<String, Object> beanMap = this.context.getBeansOfType(Object.class);
        //遍历设置所有bean,可以根据需求在循环中对bean做过滤
        for (String beanName : beanMap.keySet()) {
            groovyBinding.setVariable(beanName, beanMap.get(beanName));
        }
        return groovyBinding;
    }

//    @Bean("groovyBinding1")
//    public Binding groovyBinding1() {
//        Map<String, Object> beanMap = this.context.getBeansOfType(Object.class);
//        //如果不需要对bean做过滤，直接用beanMap构造Binding对象即可
//        return new Binding(beanMap);
//    }
}
