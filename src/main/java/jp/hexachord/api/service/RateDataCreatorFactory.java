package jp.hexachord.api.service;

import jp.hexachord.api.def.CalcType;

public class RateDataCreatorFactory {
	public static IRateDataCreator GetRateDataCreator(CalcType calcType) {
		switch (calcType) {
		case SUM:
			return null; //TODO
		case AVG:
			return new AverageRateDataCreator();
		case MAX:
		case MIN:
			return null; //TODO
		default:
			return new DetailRateDataCreator();
		}
	}
}
