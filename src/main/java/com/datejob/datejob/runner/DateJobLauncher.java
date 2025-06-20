package com.datejob.datejob.runner;


import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.Date;

@Component
public class DateJobLauncher implements CommandLineRunner {

    @Autowired
    private JobLauncher jobLauncher;

    @Autowired
    private Job dateParamJob;

    @Override
    public void run(String... args) throws Exception {
        JobParameters jobParameters = new JobParametersBuilder()
                .addDate("myDate", new Date()) 
                .toJobParameters();

        JobExecution execution = jobLauncher.run(dateParamJob, jobParameters);
        System.out.println(">>> Job Status: " + execution.getStatus());
    }
}

