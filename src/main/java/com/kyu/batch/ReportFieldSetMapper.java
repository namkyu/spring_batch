package com.kyu.batch;

import java.text.ParseException;
import java.text.SimpleDateFormat;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.validation.BindException;

import com.kyu.batch.model.Report;

/**
 * @FileName : ReportFieldSetMapper.java
 * @Project : spring_batch
 * @Date : 2013. 10. 1.
 * @작성자 : nklee
 * @프로그램설명 :
 */
public class ReportFieldSetMapper implements FieldSetMapper<Report> {

	private SimpleDateFormat dataFormat = new SimpleDateFormat("yyyy-MM-dd");

	/**
	 * <pre>
	 * mapFieldSet
	 *
	 * <pre>
	 * @param fieldSet
	 * @return
	 * @throws BindException
	 */
	@Override
	public Report mapFieldSet(FieldSet fieldSet) throws BindException {
		Report report = new Report();
		report.setId(fieldSet.readInt(0));
		report.setSales(fieldSet.readBigDecimal(1));
		report.setQty(fieldSet.readInt(2));
		report.setStaffName(fieldSet.readString(3));
		try {
			report.setDate(dataFormat.parse(fieldSet.readString(4)));
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return report;
	}
}
