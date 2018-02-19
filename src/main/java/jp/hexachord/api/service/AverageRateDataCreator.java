package jp.hexachord.api.service;

import java.util.List;
import java.util.Map;

import jp.hexachord.api.dto.Rate;

public class AverageRateDataCreator implements IRateDataCreator {

	@Override
	public List<Rate> getRateData(List<String[]> csvdata, Map<String, String> queryParameters) {
		//		return csvdata.stream()
		//				.skip(3) // 先頭3行のヘッダーをスキップ
		//				.map(r -> new Rate() {
		//					{
		//						setDateStr(r[0]);
		//						setUsd(new BigDecimal(r[1]));
		//					}
		//				})
		//				.filter(r ->  r.getYear() >= startYear)
		//				.filter(r ->  r.getYear() <= endYear)
		//				.collect(Collectors.groupingBy(r -> r.getYearMonthStr(),
		//						Collectors.averagingDouble(r -> r.getUsd().doubleValue()))) // doubleに変換して平均を算出
		//				.entrySet().stream() // グループ化の結果をソートするためにstreamを再取得
		//				.sorted(Map.Entry.comparingByKey()); // キーの昇順でソート
		//				.collect(Collectors.toList());
		return null; //TODO
	}

}
