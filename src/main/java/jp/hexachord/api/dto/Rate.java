package jp.hexachord.api.dto;

import java.math.BigDecimal;

import lombok.Data;

@Data
public class Rate {
	private String dateStr;
	private BigDecimal usd;
}
