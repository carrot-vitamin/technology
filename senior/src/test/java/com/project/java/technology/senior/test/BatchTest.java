package com.project.java.technology.senior.test;

import org.junit.jupiter.api.Test;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author za-yinshaobo
 * 2020/9/3 14:20
 */
@SpringBootTest
public class BatchTest {

    @Autowired
    private SimpleJobLauncher jobLauncher;

    @Autowired
    private Job importJob;

    @Test
    public void test() throws Exception {
        // 后置参数：使用JobParameters中绑定参数
        JobParameters jobParameters = new JobParametersBuilder().addLong("time", System.currentTimeMillis())
                .toJobParameters();
        jobLauncher.run(importJob, jobParameters);
    }
}
