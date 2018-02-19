package jp.hexachord.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import jp.hexachord.api.dto.Rate;

public class DetailRateDataCreator implements IRateDataCreator {

	@Override
	public List<Rate> getRateData(List<String[]> csvdata, Map<String, String> queryParameters) {
		return csvdata.stream()
				.skip(3) // 先頭3行のヘッダーをスキップ
				.map(r -> new Rate() {
					{
						setDateStr(r[0]);
						setUsd(new BigDecimal(r[1]));
					}
				})
				//				.filter(r -> r.getDateStr().equals("2018/2/7")) //TODO:
				.collect(Collectors.toList());
	}

}
