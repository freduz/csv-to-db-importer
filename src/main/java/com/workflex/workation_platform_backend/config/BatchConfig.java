package com.workflex.workation_platform_backend.config;

import com.workflex.workation_platform_backend.entity.Workation;
import jakarta.persistence.EntityManagerFactory;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.batch.item.database.JpaItemWriter;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.builder.FlatFileItemReaderBuilder;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.transaction.PlatformTransactionManager;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Configuration
@EnableBatchProcessing
public class BatchConfig {

    private static final String CSV_RESOURCE = "workations.csv";
    private static final String[] CSV_FIELDS = {
            "workationId", "employee", "origin", "destination",
            "start", "end", "workingDays", "risk"
    };
    private static final String JOB_NAME = "importWorkationsJob";
    private static final String STEP_NAME = "csvToDbStep";
    private static final int CHUNK_SIZE = 10;
    private static final DateTimeFormatter DATE_FORMAT = DateTimeFormatter.ofPattern("yyyy-MM-dd");

    @Bean
    public FlatFileItemReader<Workation> workationReader() {
        FieldSetMapper<Workation> fieldSetMapper = fieldSet -> {
            Workation workation = new Workation();
            workation.setWorkationId(fieldSet.readString("workationId"));
            workation.setEmployee(fieldSet.readString("employee"));
            workation.setOrigin(fieldSet.readString("origin"));
            workation.setDestination(fieldSet.readString("destination"));
            workation.setStart(LocalDate.parse(fieldSet.readString("start"), DATE_FORMAT));
            workation.setEnd(LocalDate.parse(fieldSet.readString("end"), DATE_FORMAT));
            workation.setWorkingDays(fieldSet.readInt("workingDays"));
            workation.setRisk(fieldSet.readString("risk"));
            return workation;
        };

        return new FlatFileItemReaderBuilder<Workation>()
                .name("workationItemReader")
                .resource(new ClassPathResource(CSV_RESOURCE))
                .linesToSkip(1)
                .delimited()
                .names(CSV_FIELDS)
                .fieldSetMapper(fieldSetMapper)
                .build();
    }

    @Bean
    public JpaItemWriter<Workation> workationWriter(EntityManagerFactory entityManagerFactory) {
        JpaItemWriter<Workation> writer = new JpaItemWriter<>();
        writer.setEntityManagerFactory(entityManagerFactory);
        return writer;
    }

    @Bean
    public Step csvToDbStep(JobRepository jobRepository,
                            PlatformTransactionManager transactionManager,
                            ItemReader<Workation> reader,
                            ItemWriter<Workation> writer) {
        return new StepBuilder(STEP_NAME, jobRepository)
                .<Workation, Workation>chunk(CHUNK_SIZE, transactionManager)
                .reader(reader)
                .writer(writer)
                .build();
    }

    @Bean
    public Job importWorkationsJob(JobRepository jobRepository, Step csvToDbStep) {
        return new JobBuilder(JOB_NAME, jobRepository)
                .flow(csvToDbStep)
                .end()
                .build();
    }
}