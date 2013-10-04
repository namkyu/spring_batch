package com.kyu.batch.tasklet;

import java.io.File;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.UnexpectedJobExecutionException;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.core.io.Resource;
import org.springframework.util.Assert;

/**
 * @FileName : FileDeletinigTasklet.java
 * @Project : spring_batch
 * @Date : 2013. 10. 4.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class FileDeletingTasklet implements Tasklet, InitializingBean {

	private Resource directory;

	/**
	 * <pre>
	 * afterPropertiesSet
	 *
	 * <pre>
	 * @throws Exception
	 */
	@Override
	public void afterPropertiesSet() throws Exception {
		Assert.notNull(directory, "directory must be set");
	}

	/**
	 * <pre>
	 * execute
	 *
	 * <pre>
	 * @param contribution
	 * @param chunkContext
	 * @return
	 * @throws Exception
	 */
	@Override
	public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
		File dir = directory.getFile();
		Assert.state(dir.isDirectory());

		File[] files = dir.listFiles();
		for (int i = 0; i < files.length; i++) {
			boolean deleted = files[i].delete();
			if (deleted == false) {
				throw new UnexpectedJobExecutionException("Could not delete file " + files[i].getPath());
			} else {
				System.out.println(files[i].getPath() + " is deleted!");
			}
		}

		return RepeatStatus.FINISHED;
	}

	/**
	 * @return the directory
	 */
	public Resource getDirectory() {
		return directory;
	}

	/**
	 * @param directory the directory to set
	 */
	public void setDirectory(Resource directory) {
		this.directory = directory;
	}


}
