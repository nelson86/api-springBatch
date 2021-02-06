package com.narabel.batch.batch.simpleDataList.job;

import com.narabel.batch.dao.MockDataPersistDao;
import com.narabel.batch.entity.MockData;
import com.narabel.batch.entity.MockDataPersist;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.*;
import org.springframework.batch.item.file.FlatFileFooterCallback;
import org.springframework.beans.factory.annotation.Autowired;

import java.io.IOException;
import java.io.Writer;
import java.math.BigDecimal;
import java.util.List;

@Log4j2
public class DataSDLWriter implements ItemWriter<MockData>, StepExecutionListener {

    @Autowired
    private MockDataPersistDao dao;

    private BigDecimal totalAmount = BigDecimal.ZERO;

    @Override
    public void beforeStep(StepExecution stepExecution) {

    }

    @Override
    public void write(List<? extends MockData> items) throws Exception {

        BigDecimal chunkTotal = BigDecimal.ZERO;

        for ( MockData data : items ) {

            MockDataPersist mockDataPersist = new MockDataPersist(
                    data.getFirstName().concat(" ").concat(data.getLastName()),
                    data.getAmount()
            );

            dao.save(mockDataPersist);

            chunkTotal = chunkTotal.add(data.getAmount());
        }

        // After successfully writing all items
        totalAmount = totalAmount.add(chunkTotal);
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {

        log.info("procesado: "+totalAmount.toString());

        return ExitStatus.COMPLETED;
    }
}
