package com.project.java.technology.senior.batch.v2;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.repository.support.MapJobRepositoryFactoryBean;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.batch.support.transaction.ResourcelessTransactionManager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.FileSystemResource;

/**
 * @author ShaoBo Yin at 2020/11/30 16:30
 */
@EnableBatchProcessing
@Configuration
public class JobConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public ItemReader<User> messageItemReader() {
        DefaultLineMapper<User> lineMapper = new DefaultLineMapper<>();
        // 完成文件行拆分，并封装为一个属性结果集，因为我们使用“,”分隔用户属性，
        // 所以需要将 lineTokenizer 设置为 DelimitedLineTokenizer
        lineMapper.setLineTokenizer(new DelimitedLineTokenizer());
        // fieldSetMapper 完成将结果集封装为一个 POJO 对象
        lineMapper.setFieldSetMapper(new UserMapper());

        // 封装ItemReader
        FlatFileItemReader<User> itemReader = new FlatFileItemReader<>();
        itemReader.setLineMapper(lineMapper);
        itemReader.setResource(new FileSystemResource("file/user.txt"));
        return itemReader;
    }

    @Bean
    public Step step() {
        return stepBuilderFactory
                .get("messageStep")
                //每当处理完毕读入的 5 条数据时，提交一次事务
                .<User, Message>chunk(2)
                .reader(messageItemReader())
                .processor(new MessagesItemProcessor())
                .writer(new MessagesItemWriter())
                .build();
    }

    @Bean
    public Job messageJob() {
        return jobBuilderFactory
                .get("messageJob")
                .start(step())
                .build();
    }

    /**
     * 因为不需要数据库操作，所以选择了使用 MapJobRepositoryFactoryBean 和 ResourcelessTransactionManager，
     * 如果为数据库操作则对应使用数据库Spring batch提供的相应插件，
     * 如JobRepositoryFactoryBean和DataSourceTransactionManager
     * @return bean
     */
    @Bean
    public MapJobRepositoryFactoryBean factoryBean() {
        MapJobRepositoryFactoryBean factoryBean = new MapJobRepositoryFactoryBean();
        factoryBean.setTransactionManager(new ResourcelessTransactionManager());
        return factoryBean;
    }
}
