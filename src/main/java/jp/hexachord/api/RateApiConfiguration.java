package jp.hexachord.api;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "rate.api")
public class RateApiConfiguration {

	private boolean downloadAtStartup;

	public boolean isDownloadAtStartup() {
		return downloadAtStartup;
	}

	public void setDownloadAtStartup(boolean downloadAtStartup) {
		this.downloadAtStartup = downloadAtStartup;
	}
}
