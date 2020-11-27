package com.project.java.technology.senior.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author za-yinshaobo at 2020/11/27 10:48
 */
@RestController
@RequestMapping(value = "job")
public class JobController {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job jobLaunchDemoJob;

    @GetMapping("/{job1param}")
    public String runJob1(@PathVariable("job1param") String job1param) throws Exception {
        System.out.println("Request to run job1 with param: " + job1param);
        JobParameters jobParameters = new JobParametersBuilder()
                .addString("job1param",job1param)
                .toJobParameters();
        jobLauncher.run(jobLaunchDemoJob,jobParameters);
        return "Job1 success.";
    }
}
