package jp.hexachord.api.service;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import jp.hexachord.api.dto.Rate;

@Component
public class RateDataService {

	@Autowired
	private ResourceLoader resourceLoader;

	private static final String csvFilePath = "data/market_quote.csv";
	private static List<String[]> csvdata = new ArrayList<>();;

	@PostConstruct
	public void initAfterStartup() {
		// CSVファイル読み込み
		Resource resource = resourceLoader.getResource("classpath:" + csvFilePath);
		try (BufferedReader reader = new BufferedReader(
				new InputStreamReader(new FileInputStream(resource.getFile()), "Shift-JIS"))) {
			String line;
			while ((line = reader.readLine()) != null) {
				csvdata.add(line.split(","));
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public List<Rate> getRateData(Map<String, String> queryParameters) {
		return csvdata.stream()
				.skip(3) // 先頭3行のヘッダーをスキップ
				.map(r -> new Rate() {
					{
						setDateStr(r[0]);
						setUsd(new BigDecimal(r[1]));
					}
				})
				.filter(r -> r.getDateStr().equals("2018/2/7")) //TODO:
				.collect(Collectors.toList());
	}
}
