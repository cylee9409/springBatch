package tmoney.szr.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import tmoney.szr.step.listener.SZRBatchJobListener;
import tmoney.szr.step.tasklet.SampleTasklet;

@Configuration
@RequiredArgsConstructor
public class SeparateTaskletSampleJob {
	
	public static final String JOB_NAME = "separate_tasklet_sample_job";
	public static final String STEP_NAME = JOB_NAME + "_step";
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	private final SampleTasklet sampleTasklet;
	
	@Bean(name=JOB_NAME)
	public Job separateTaskletSampleJob() {
		return jobBuilderFactory.get(JOB_NAME)
				.incrementer(new RunIdIncrementer())
				.start(separateTaskletSampleJobStep())
				.listener(new SZRBatchJobListener())
				.build();
	}
	
	@Bean
	@JobScope
	public Step separateTaskletSampleJobStep() {
		return stepBuilderFactory.get(STEP_NAME)
				.tasklet(sampleTasklet)
				.build();
	}
	
	
}
