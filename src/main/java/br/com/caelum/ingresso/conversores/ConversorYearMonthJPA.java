package br.com.caelum.ingresso.conversores;

import java.time.YearMonth;
import java.time.format.DateTimeFormatter;

import javax.persistence.AttributeConverter;
import javax.persistence.Converter;

@Converter(autoApply = true)
public class ConversorYearMonthJPA implements AttributeConverter<YearMonth, String> {

	@Override
	public String convertToDatabaseColumn(YearMonth valorDoAtributo) {
		return valorDoAtributo.format(DateTimeFormatter.ofPattern("yyyy-MM"));
	}

	@Override
	public YearMonth convertToEntityAttribute(String valorDoBanco) {
		return YearMonth.parse(valorDoBanco, DateTimeFormatter.ofPattern("yyyy-MM"));
	}

	

}
