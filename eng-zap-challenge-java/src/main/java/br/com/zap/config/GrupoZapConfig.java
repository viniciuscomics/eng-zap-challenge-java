package br.com.zap.config;

import java.math.BigDecimal;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;

import lombok.Getter;
import lombok.Setter;

@Configuration
@ConfigurationProperties("grupozap")
@Getter
@Setter
public class GrupoZapConfig {

	private String urlBusca;
	private String originAllow;
	private BigDecimal minlon;
	private BigDecimal minlat;
	private BigDecimal maxlon;
	private BigDecimal maxlat;	
	
}
