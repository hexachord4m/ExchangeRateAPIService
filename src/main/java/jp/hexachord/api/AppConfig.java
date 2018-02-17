package jp.hexachord.api;

import org.springframework.context.annotation.Bean;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurerAdapter;

import jp.hexachord.api.service.RateDataService;

public class AppConfig extends WebMvcConfigurerAdapter {

	@Bean
	RateDataService rateDataService() {
		return new RateDataService();
	}
}
