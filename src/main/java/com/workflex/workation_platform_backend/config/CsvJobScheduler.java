package com.workflex.workation_platform_backend.config;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersBuilder;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.scheduling.annotation.EnableScheduling;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

@Component
@EnableScheduling
public class CsvJobScheduler {

    private static final Logger LOGGER = LoggerFactory.getLogger(CsvJobScheduler.class);
    private final JobLauncher jobLauncher;
    private final Job importWorkationsJob;

    public CsvJobScheduler(JobLauncher jobLauncher, Job importWorkationsJob) {
        this.jobLauncher = jobLauncher;
        this.importWorkationsJob = importWorkationsJob;
    }

    @Scheduled(cron = "0 0 1 * * ?") // every day at 1 AM
    public void runJob() {
        JobParameters params = new JobParametersBuilder()
                .addLong("timestamp", System.currentTimeMillis())
                .toJobParameters();
        try {
            jobLauncher.run(importWorkationsJob, params);
        } catch (Exception e) {
            LOGGER.debug("CSV Import failed:", e);
        }
    }
}
