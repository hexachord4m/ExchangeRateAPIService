package jp.hexachord.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Rate {
	private String dateStr;
	private BigDecimal usd;

	@JsonIgnore
	private int year;
	@JsonIgnore
	private int month;
	@JsonIgnore
	private int day;

	public void setDateStr(String dateStr) {
		this.dateStr = dateStr;
		String[] date = dateStr.split("/");
		this.year = Integer.valueOf(date[0]);
		this.month = Integer.valueOf(date[1]);
		this.day = Integer.valueOf(date[2]);
	}
}
