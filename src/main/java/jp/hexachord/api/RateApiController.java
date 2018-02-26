package jp.hexachord.api;

import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import jp.hexachord.api.dto.Rate;
import jp.hexachord.api.service.RateDataService;


@RestController
@RequestMapping("/api")
public class RateApiController {

	@Autowired
	RateDataService rateDataService;

	@RequestMapping(value="/rate", method=RequestMethod.GET)
	public List<Rate> getRateData(@RequestParam Map<String, String> queryParameters) {
		return rateDataService.getRateData(queryParameters);
	}

	@RequestMapping(value="/rate/update")
	public void refreshUpdateData() {
		rateDataService.downloadCsvFile();
	}
}
