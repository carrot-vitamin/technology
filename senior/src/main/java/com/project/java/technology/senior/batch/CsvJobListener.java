package com.project.java.technology.senior.batch;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;

/**
 * @author za-yinshaobo
 * @date 2020/10/17 16:44
 * 监听Job执行情况，则定义一个类实现JobExecutorListener，并定义Job的Bean上绑定该监听器
 */
public class CsvJobListener implements JobExecutionListener {

    private Logger logger = LoggerFactory.getLogger(CsvJobListener.class);
    private long startTime;

    @Override
    public void beforeJob(JobExecution jobExecution) {
        startTime = System.currentTimeMillis();
        logger.info("job process start...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        long endTime = System.currentTimeMillis();
        logger.info("job process end...");
        logger.info("elapsed time: " + (endTime - startTime) + "ms");
    }
}
