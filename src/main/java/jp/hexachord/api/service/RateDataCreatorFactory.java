package jp.hexachord.api.service;

import java.util.List;
import java.util.Map;

import jp.hexachord.api.def.CalcType;

public class RateDataCreatorFactory {
	public static AbstractRateDataCreator GetRateDataCreator(List<String[]> csvdata,
			Map<String, String> queryParameters) {
		CalcType calcType = CalcType.getCalcType(queryParameters.get("calc"));

		switch (calcType) {
		case SUM:
			return null; //TODO
		case AVG:
			return new AverageRateDataCreator(csvdata, queryParameters);
		case MAX:
			return new MaxRateDataCreator(csvdata, queryParameters);
		case MIN:
			return null; //TODO
		default:
			return new DetailRateDataCreator(csvdata, queryParameters);
		}
	}
}
