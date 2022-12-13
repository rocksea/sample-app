package kr.co.sample.batch.job.helloworld;

import lombok.RequiredArgsConstructor;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.support.CompositeItemProcessor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
@RequiredArgsConstructor
public class HelloWorldJob {
    private static final String JOB_NAME = "helloWorldJob";
    private static final Integer CHUNK_SIZE = 1;
    private final JobBuilderFactory jobBuilderFactory;
    private final StepBuilderFactory stepBuilderFactory;
    private final HelloWorldParameterValidator validator;
    private final HelloWorldReader itemReader;
    private final HelloWorldWriter itemWriter;
    private final HelloWorldProcessor prepareProcessor;
    private final HelloWorldListener stepListener;

    @Bean
    public Job helloWorldJobs() {
        return jobBuilderFactory.get(JOB_NAME).validator(validator).start(helloWorldStep()).build();
    }

    @Bean
    public Step helloWorldStep() {
        return stepBuilderFactory
                .get("helloWorldStep")
                .listener(stepListener)
                .<String, String>chunk(CHUNK_SIZE)
                .reader(itemReader)
                .processor(compositeItemProcessor())
                .writer(itemWriter)
                .build();
    }

    @Bean
    public ItemProcessor<String, String> compositeItemProcessor() {
        var processor = new CompositeItemProcessor<String, String>();

        processor.setDelegates(
                List.of(
                        prepareProcessor
                ));

        return processor;
    }
}
