package jp.hexachord.api.service;

import jp.hexachord.api.def.CalcType;

public class RateDataCreatorFactory {
	public static IRateDataCreator GetRateDataCreator(CalcType calcType) {
		switch (calcType) {
		case Sum:
			return null; //TODO
		case Avg:
			return new AverageRateDataCreator();
		case Max:
		case Min:
			return null; //TODO
		default:
			return new DetailRateDataCreator();
		}
	}
}
