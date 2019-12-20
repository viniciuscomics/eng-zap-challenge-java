package br.com.zap.config;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("viva")
@Getter
@Setter
public class VivaRealConfig {
	private BigDecimal paramMaxRentalTotalPrice;
	private BigDecimal paramMaxSalePrice;
	private BigDecimal BoundingBoxFee;
}
