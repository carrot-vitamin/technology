package com.project.java.technology.senior.batch.v2;

import org.apache.commons.lang3.time.DateFormatUtils;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.support.SimpleJobLauncher;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.core.task.SimpleAsyncTaskExecutor;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.UUID;

/**
 * @author ShaoBo Yin at 2020/12/4 14:41
 */
@RestController(value = "JobControllerV2")
@RequestMapping("/job/v2")
public class JobController {

    @Autowired
    private JobRepository jobRepository;

    @Autowired
    private ApplicationContext applicationContext;

    @GetMapping("/run/{jobName}")
    public String run(@PathVariable String jobName) throws Exception {
        SimpleJobLauncher launcher = new SimpleJobLauncher();
        launcher.setJobRepository(jobRepository);
        launcher.setTaskExecutor(new SimpleAsyncTaskExecutor());
        //使用不同的参数可以保证同一个job能多次执行
        JobParameters jobParameters = new JobParametersBuilder().addString("UUID", UUID.randomUUID().toString()).toJobParameters();
        launcher.run((Job) applicationContext.getBean(jobName), jobParameters);
        return DateFormatUtils.format(new Date(), "yyyy-MM-dd HH:mm:sss");
    }
}
