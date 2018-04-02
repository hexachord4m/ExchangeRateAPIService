package jp.hexachord.api.service;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Comparator;
import java.util.List;
import java.util.Map;
import java.util.stream.Stream;

import jp.hexachord.api.dto.Rate;

public class MaxRateDataCreator extends AbstractRateDataCreator {

	public MaxRateDataCreator(List<String[]> csvdata, Map<String, String> queryParameters) {
		super(csvdata, queryParameters);
	}

	@Override
	public List<Rate> getRateData() {
		Stream<Rate> stream = filterdStream();
		Rate max = stream.max(Comparator.comparing(r -> ((Rate) r).getUsd())).get();
		return new ArrayList<Rate>(Arrays.asList(max));
	}

}
