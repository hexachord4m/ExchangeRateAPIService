package jp.hexachord.api.service;

import java.math.BigDecimal;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import jp.hexachord.api.dto.Rate;

public class DetailRateDataCreator implements IRateDataCreator {

	@Override
	public List<Rate> getRateData(List<String[]> csvdata, Map<String, String> queryParameters) {
		Stream<Rate> stream = csvdata.stream()
				.skip(3) // 先頭3行のヘッダーをスキップ
				.map(r -> new Rate() {
					{
						setDateOrg(r[0]);
						setUsd(new BigDecimal(r[1]));
					}
				});

		if (queryParameters.containsKey("date")) {
			int date = queryParameters.get("date").toLowerCase().equals("now")
					? Integer.valueOf(new SimpleDateFormat("yyyyMMdd").format(new Date()))
					: Integer.valueOf(queryParameters.get("date"));
			stream = stream.filter(r -> r.getDateNum() == date);
		} else {
			if (queryParameters.containsKey("startdate")) {
				stream = stream.filter(r -> r.getDateNum() >= Integer.valueOf(queryParameters.get("startdate")));
			}
			if (queryParameters.containsKey("enddate")) {
				stream = stream.filter(r -> r.getDateNum() <= Integer.valueOf(queryParameters.get("enddate")));
			}
		}

		return stream.collect(Collectors.toList());

	}

}
