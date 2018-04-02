package jp.hexachord.api.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.hexachord.api.dto.Rate;

public class AverageRateDataCreator extends AbstractRateDataCreator {

	public AverageRateDataCreator(List<String[]> csvdata, Map<String, String> queryParameters) {
		super(csvdata, queryParameters);
	}

	@Override
	public List<Rate> getRateData() {
		Stream<Rate> stream = filterdStream();
		return stream.collect(Collectors.groupingBy(r -> r.getYearMonthStr(),
				Collectors.averagingDouble(r -> r.getUsd().doubleValue()))) // doubleに変換して平均を算出
				.entrySet().stream() // グループ化の結果をソートするためにstreamを再取得
				.sorted(Map.Entry.comparingByKey()) // キーの昇順でソート
				.map(r -> new Rate() {
					{
						setDate(r.getKey());
						BigDecimal bd = new BigDecimal(r.getValue().toString());
						BigDecimal bd1 = bd.setScale(2, BigDecimal.ROUND_HALF_UP);
						setUsd(bd1);
					}
				})
				.collect(Collectors.toList());
	}
}
