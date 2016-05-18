package com.kyu.batch;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;

import org.junit.Test;
import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;

/**
 * @FileName : App.java
 * @Project : spring_batch
 * @Date : 2013. 10. 1.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class App {

	@Test
	public void 배치테스트() throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		String[] springConfig = { "classpath:spring/batch/jobs/job-hello-world.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		Job job = context.getBean("helloWorldJob", Job.class);
		JobExecution execution = jobLauncher.run(job, new JobParameters());

		assertThat(BatchStatus.COMPLETED, is(execution.getStatus()));
	}
}
