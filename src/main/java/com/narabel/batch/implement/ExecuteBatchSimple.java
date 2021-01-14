package com.narabel.batch.implement;

import com.narabel.batch.service.ExecuteBath;
import lombok.extern.log4j.Log4j2;
import org.springframework.batch.core.*;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Log4j2
@Service
public class ExecuteBatchSimple implements ExecuteBath {

    @Autowired
    JobLauncher jobLauncher;

    @Autowired
    Job jobSimpleListData;

    @Override
    public void runBatch() {

        JobParametersBuilder jobParametersBuilder = new JobParametersBuilder();

        try {
            JobExecution execution = jobLauncher.run(jobSimpleListData, this.defaultJobParameters() );

            log.info("Exit Status of Job: " + execution.getStatus());

        } catch (JobExecutionAlreadyRunningException e) {
            e.printStackTrace();
        } catch (JobRestartException e) {
            e.printStackTrace();
        } catch (JobInstanceAlreadyCompleteException e) {
            e.printStackTrace();
        } catch (JobParametersInvalidException e) {
            e.printStackTrace();
        }
    }

    private JobParameters defaultJobParameters() {
        return new JobParametersBuilder()
                .addLong("uniqueness", System.nanoTime())
                .toJobParameters();
    }

}
