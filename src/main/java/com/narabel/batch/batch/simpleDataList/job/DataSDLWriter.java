package com.narabel.batch.batch.simpleDataList.job;

import com.narabel.batch.entity.MockData;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.*;

import java.util.List;

@Log4j2
public class DataSDLWriter implements ItemWriter<MockData>, StepExecutionListener {


    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public void write(List<? extends MockData> items) throws Exception {

        items.forEach(mockData -> {
                log.info(mockData.toString());
            }
        );

    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        return null;
    }

}
