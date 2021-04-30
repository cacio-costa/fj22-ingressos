package br.com.caelum.ingresso.conversores;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import org.springframework.core.convert.converter.Converter;

public class ConversorDeYearMonth implements Converter<String, YearMonth> {

	@Override
	public YearMonth convert(String valor) {
		return YearMonth.parse(valor, DateTimeFormatter.ofPattern("MM/yyyy"));
	}

}
