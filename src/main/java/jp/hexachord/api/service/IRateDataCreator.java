package jp.hexachord.api.service;

import java.util.List;
import java.util.Map;

import jp.hexachord.api.dto.Rate;

public interface IRateDataCreator {
	List<Rate> getRateData(List<String[]> csvdata, Map<String, String> queryParameters);
}
