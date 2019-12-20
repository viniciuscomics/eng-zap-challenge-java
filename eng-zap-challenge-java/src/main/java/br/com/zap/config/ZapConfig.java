package br.com.zap.config;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("zap")
@Getter
@Setter
public class ZapConfig {

	private BigDecimal paramMinRentalTotalPrice;
	private BigDecimal paramMinSalePrice;
	private BigDecimal BoundingBoxFee;
}
