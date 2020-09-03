package com.project.java.technology.senior.config;

import com.project.java.technology.senior.interceptor.TokenInterceptor;
import com.ulisesbocchio.jasyptspringboot.EncryptablePropertyDetector;
import org.jasypt.encryption.StringEncryptor;
import org.jasypt.encryption.pbe.PooledPBEStringEncryptor;
import org.jasypt.encryption.pbe.config.SimpleStringPBEConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.env.Environment;
import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurationSupport;

/**
 * @author yinshaobo
 * @date 2019/4/17 15:40
 * @description
 */
@Configuration
public class WebConfig extends WebMvcConfigurationSupport {

    private static final String KEY_STRING_ENCRYPT = "string.encrypt.salt";

    @Autowired
    private Environment environment;

    @Bean
    public HandlerInterceptor tokenInterceptor() {
        return new TokenInterceptor();
    }

    @Override
    protected void addInterceptors(InterceptorRegistry registry) {
        registry.addInterceptor(this.tokenInterceptor())
                .addPathPatterns("/token/**")
                .excludePathPatterns("/token");
        super.addInterceptors(registry);
    }

    /**
     * 配置文件加密前缀配置
     * @return
     */
    @Bean
    public EncryptablePropertyDetector encryptablePropertyDetector() {
        return new MyEncryptablePropertyDetector();
    }

    @Bean("jasyptStringEncryptor")
    public StringEncryptor stringEncryptor() {
        PooledPBEStringEncryptor encryptor = new PooledPBEStringEncryptor();
        SimpleStringPBEConfig config = new SimpleStringPBEConfig();
        config.setPassword(environment.getProperty(KEY_STRING_ENCRYPT));
        config.setAlgorithm("PBEWITHHMACSHA512ANDAES_256");
        config.setKeyObtentionIterations("1000");
        config.setPoolSize("1");
        config.setProviderName("SunJCE");
        config.setSaltGeneratorClassName("org.jasypt.salt.RandomSaltGenerator");
        config.setIvGeneratorClassName("org.jasypt.iv.RandomIvGenerator");
        config.setStringOutputType("base64");
        encryptor.setConfig(config);
        return encryptor;
    }

}
