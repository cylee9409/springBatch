package tmoney.szr.job;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.JobScope;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import tmoney.szr.step.listener.SZRBatchJobListener;

@Configuration
@RequiredArgsConstructor
@Slf4j
public class TaskletSampleJob {
	
	public static final String JOB_NAME = "tasklet_sample_job";
	public static final String STEP_NAME = JOB_NAME + "_step";
	
	private final JobBuilderFactory jobBuilderFactory;
	private final StepBuilderFactory stepBuilderFactory;
	
	@Bean(name=JOB_NAME)
	public Job taskletSampleJob() {
		return jobBuilderFactory.get(JOB_NAME)
				.incrementer(new RunIdIncrementer())
				.start(taskletSampleJobStep())
				.listener(new SZRBatchJobListener())
				.build();
	}
	
	@Bean
	@JobScope
	public Step taskletSampleJobStep() {
		return stepBuilderFactory.get(STEP_NAME)
				.tasklet((contribution, ChunkContext) -> {
					log.info(">>>>>>> This is Step1");
					return RepeatStatus.FINISHED;
				})
				.build();
	}
	
}
