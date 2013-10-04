package com.kyu.batch;

import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.launch.JobLauncher;
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

	public static void main(String[] args) {
		String[] springConfig = { "classpath:spring/batch/jobs/job-hello-world.xml" };
		ApplicationContext context = new ClassPathXmlApplicationContext(springConfig);

		JobLauncher jobLauncher = context.getBean("jobLauncher", JobLauncher.class);
		Job job = context.getBean("helloWorldJob", Job.class);

		try {
			JobExecution execution = jobLauncher.run(job, new JobParameters());
			System.out.println("Exit Status : " + execution.getStatus());
		} catch (Exception e) {
			e.printStackTrace();
		}

		System.out.println("Done");
	}
}
