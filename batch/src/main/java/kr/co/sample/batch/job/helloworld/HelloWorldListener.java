package kr.co.sample.batch.job.helloworld;

import lombok.extern.slf4j.Slf4j;
import org.springframework.batch.core.ExitStatus;
import org.springframework.batch.core.StepExecution;
import org.springframework.batch.core.StepExecutionListener;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class HelloWorldListener implements StepExecutionListener {

    @Override
    public void beforeStep(StepExecution stepExecution) {
        log.debug("ExamMigrateJob Step Started");
    }

    @Override
    public ExitStatus afterStep(StepExecution stepExecution) {
        log.info("ExamMigrateJob Step Finished");

        return ExitStatus.COMPLETED;
    }
}
