package com.datejob.datejob.configuration;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.batch.core.repository.JobRepository;

import java.util.Date;

@Configuration
public class DateJobConfiguration {

    @Bean
    Step dateStep(JobRepository jobRepository, PlatformTransactionManager transactionManager) {
        return new StepBuilder("dateStep", jobRepository)
                .tasklet((contribution, chunkContext) -> {
                    Date myDate = (Date) chunkContext
                            .getStepContext()
                            .getJobParameters()
                            .get("myDate");

                    System.out.println(">>> Date received: " + myDate);
                    return RepeatStatus.FINISHED;
                }, transactionManager)
                .build();
    }

    @Bean
    Job dateParamJob(JobRepository jobRepository, Step dateStep) {
        return new JobBuilder("dateParamJob", jobRepository)
                .start(dateStep)
                .build();
    }
}

