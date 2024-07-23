package tmoney.szr.step.listener;

import java.util.Map;

import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.batch.core.JobParameter;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;

@Component
@Slf4j
public class SZRBatchJobListener implements JobExecutionListener {

	@Override
	public void beforeJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
		log.info("Before Job Execution");
		log.info("JOB NAME : " + jobExecution.getJobInstance().getJobName());
		
		Map<String, JobParameter> jobParameters = jobExecution.getJobParameters().getParameters();
		
		for (String key : jobParameters.keySet()) {
            log.info("JOB PARAMETER : " + key + " = " + jobParameters.get(key).getValue().toString());
        }
		
	}

	@Override
	public void afterJob(JobExecution jobExecution) {
		// TODO Auto-generated method stub
		
	}
}
