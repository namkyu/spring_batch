package com.kyu.batch;

import org.springframework.batch.item.ItemProcessor;

import com.kyu.batch.model.Report;

/**
 * @FileName : CustomItemProcessor.java
 * @Project : spring_batch
 * @Date : 2013. 10. 1.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class CustomItemProcessor implements ItemProcessor<Report, Report> {

	/**
	 * <pre>
	 * process
	 *
	 * <pre>
	 * @param item
	 * @return
	 * @throws Exception
	 */
	@Override
	public Report process(Report item) throws Exception {
		System.out.println("Processing..." + item);
		return item;
	}

}
