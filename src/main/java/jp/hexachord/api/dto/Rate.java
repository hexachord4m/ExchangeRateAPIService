package jp.hexachord.api.dto;

import java.math.BigDecimal;

import com.fasterxml.jackson.annotation.JsonIgnore;

import lombok.Data;

@Data
public class Rate {
	private String date;
	private BigDecimal usd;

	@JsonIgnore
	private int dateNum;
	@JsonIgnore
	private int year;
	@JsonIgnore
	private int month;
	@JsonIgnore
	private int day;

	public void setDateOrg(String date) {
		this.date = date;
		String[] dateAry = date.split("/");
		this.year = Integer.valueOf(dateAry[0]);
		this.month = Integer.valueOf(dateAry[1]);
		this.day = Integer.valueOf(dateAry[2]);
		this.dateNum = Integer.parseInt(String.format("%d%02d%02d", this.year, this.month, this.day));
	}

	//	@JsonIgnore
	//	public String getYearMonthStr() {
	//		return String.format("%d%02d", year, month);
	//	}
}
