package com.narabel.batch.batch.simpleDataList.conf;

import com.narabel.batch.batch.BaseBatchConfiguration;
import com.narabel.batch.batch.simpleDataList.job.DataSDLReader;
import com.narabel.batch.batch.simpleDataList.job.DataSDLWriter;
import com.narabel.batch.batch.simpleDataList.listener.ChunkCountListener;
import com.narabel.batch.entity.MockData;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class JobSimpleDataListBatchConfiguration extends BaseBatchConfiguration {

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
                .listener(new ChunkCountListener())
                //.allowStartIfComplete(true) // el paso siempre se ejecutara nuevamente
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
