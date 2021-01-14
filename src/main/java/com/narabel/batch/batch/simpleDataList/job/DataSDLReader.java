package com.narabel.batch.batch.simpleDataList.job;

import com.narabel.batch.dao.MockDataDao;
import com.narabel.batch.entity.MockData;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.NonTransientResourceException;
import org.springframework.batch.item.ParseException;
import org.springframework.batch.item.UnexpectedInputException;
import org.springframework.batch.item.support.IteratorItemReader;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.List;

@Log4j2
public class DataSDLReader implements ItemReader<MockData>, StepExecutionListener {

    @Autowired
    private MockDataDao dao;

    private ItemReader<MockData> delegate;

    private List<MockData> datosMock;

    @Override
    public void beforeStep(StepExecution stepExecution) {
        this.datosMock = new ArrayList<>();

        Iterable<MockData> dataDB = dao.findAll();
        dataDB.iterator().forEachRemaining(this.datosMock::add);

        log.info("Registros encontrados: "+this.datosMock.size());

        stepExecution.getJobExecution().getExecutionContext().put("cant_registros", this.datosMock.size());
    }

    @Override
    public MockData read() throws Exception, UnexpectedInputException, ParseException, NonTransientResourceException {

        // Alternativa al uso de IteratorItemReader seria JdbcCursorItemReader
        if (delegate == null) {
            delegate = new IteratorItemReader<>( this.datosMock );
        }

        return delegate.read();
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        if( this.datosMock == null || this.datosMock.isEmpty() ) {
            return ExitStatus.STOPPED;
        }
        return ExitStatus.COMPLETED;
    }
}
