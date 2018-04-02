package jp.hexachord.api.service;

import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.hexachord.api.dto.Rate;

public class DetailRateDataCreator extends AbstractRateDataCreator {

	public DetailRateDataCreator(List<String[]> csvdata, Map<String, String> queryParameters) {
		super(csvdata, queryParameters);
	}

	@Override
	public List<Rate> getRateData() {
		Stream<Rate> stream = filterdStream();
		return stream.collect(Collectors.toList());
	}

}
