package jp.hexachord.api.service;

import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.Resource;
import org.springframework.core.io.ResourceLoader;
import org.springframework.stereotype.Component;

import jp.hexachord.api.RateApiConfiguration;
import jp.hexachord.api.dto.Rate;

@Component
public class RateDataService {

	@Autowired
	private ResourceLoader resourceLoader;
	@Autowired
	private RateApiConfiguration rateApiConfiguration;

	private static final String csvFilePath = "data/market_quote.csv";
	private static final String csvFileUrl = "https://www.mizuhobank.co.jp/rate/market/csv/quote.csv";
	private static List<String[]> csvdata = new ArrayList<>();;

	@PostConstruct
	public void initAfterStartup() {
		// CSVファイルダウンロード
		if (rateApiConfiguration.isDownloadAtStartup()) {
			downloadCsvFile();
		}

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
		AbstractRateDataCreator rateDataCreator = RateDataCreatorFactory.GetRateDataCreator(csvdata, queryParameters);
		return rateDataCreator.getRateData();
	}

	public void downloadCsvFile() {
		HttpURLConnection conn = null;
		DataInputStream dataInStream = null;
		DataOutputStream dataOutStream = null;
		// HttpURLConnectionはAutoCloseableを実装していないので、try-with-resourcesが使えずレガシーな実装に
		try {
			URL url = new URL(csvFileUrl);
			conn = (HttpURLConnection) url.openConnection();
			conn.setRequestMethod("GET");
			conn.connect();

			int statusCode = conn.getResponseCode();
			if (statusCode != HttpURLConnection.HTTP_OK) {
				throw new Exception("statusCode is " + statusCode);
			}

			dataInStream = new DataInputStream(conn.getInputStream());
			Resource resource = resourceLoader.getResource("classpath:" + csvFilePath);
			dataOutStream = new DataOutputStream(new BufferedOutputStream(new FileOutputStream(resource.getFile())));

			byte[] b = new byte[4096];
			int readByte = 0;
			while (-1 != (readByte = dataInStream.read(b))) {
				dataOutStream.write(b, 0, readByte);
			}
		} catch (Exception e) {
			e.printStackTrace();
		} finally {
			try {
				if (dataInStream != null) {
					dataInStream.close();
				}
				if (dataOutStream != null) {
					dataOutStream.close();
				}
				if (conn != null) {
					conn.disconnect();
				}
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}
}
