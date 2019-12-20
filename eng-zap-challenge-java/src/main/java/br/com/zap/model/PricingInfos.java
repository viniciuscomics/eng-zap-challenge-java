package br.com.zap.model;

import java.io.Serializable;
import java.math.BigDecimal;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PricingInfos implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String yearlyIptu;
	private BigDecimal price;
	private String businessType;
	private String monthlyCondoFee;
	private BigDecimal rentalTotalPrice;
}
