package jp.hexachord.api.service;

import java.math.BigDecimal;
import java.util.Arrays;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.stereotype.Component;

import jp.hexachord.api.dto.Rate;

@Component
public class RateDataService {

	private static List<Rate> data;

	@PostConstruct
	public void initAfterStartup() {
		// TODO: CSVファイル読み込み
		data = Arrays.asList(
				new Rate() {
					{
						setDateStr("2018/2/5");
						setUsd(new BigDecimal("109.9"));
					}
				},
				new Rate() {
					{
						setDateStr("2018/2/6");
						setUsd(new BigDecimal("109.08"));
					}
				});
	}

	public List<Rate> getRateData(Map<String, String> queryParameters) {
		return data;
	}
}
