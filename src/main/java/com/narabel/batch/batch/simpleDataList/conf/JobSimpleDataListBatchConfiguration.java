package com.narabel.batch.batch.simpleDataList.conf;

import com.narabel.batch.batch.simpleDataList.job.DataSDLReader;
import com.narabel.batch.batch.simpleDataList.job.DataSDLWriter;
import com.narabel.batch.entity.MockData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@EnableBatchProcessing
public class JobSimpleDataListBatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Bean
    public DataSDLReader dataSDLReader() {
        return new DataSDLReader();
    }

    @Bean
    public DataSDLWriter dataSDLWriter() {
        return new DataSDLWriter();
    }

    @Bean
    public Step processDataMockStep(DataSDLReader dataSDLReader, DataSDLWriter dataSDLWriter) {

        return stepBuilderFactory
                .get("processDataMockStep")
                .<MockData, MockData>chunk(10)
                .reader(dataSDLReader)
                .writer(dataSDLWriter)
                .build();
    }

    @Bean(name = "jobSimpleListData")
    public Job jobSimpleListData() {
        /*
        return this.jobBuilderFactory.get("jobSimpleListData")
                .flow( processDataMockStep( dataSDLReader(), dataSDLWriter() ) )
                .end()
                .build();
                */
        return  this.jobBuilderFactory.get("jobSimpleListData")
                .start(processDataMockStep( dataSDLReader(), dataSDLWriter() ))
                .build();


    }

}
