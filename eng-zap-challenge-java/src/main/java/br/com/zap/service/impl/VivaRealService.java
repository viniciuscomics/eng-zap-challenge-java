package br.com.zap.service.impl;

import java.math.BigDecimal;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zap.config.GrupoZapConfig;
import br.com.zap.config.VivaRealConfig;
import br.com.zap.model.RealEstateAd;
import br.com.zap.wrapper.RealEstateAdWrapper;

@Service
public class VivaRealService extends AbstractRealEstateAd {

	private RealEstateAdService realEstateAdService;
	private VivaRealConfig vivaRealConfig;	

	@Autowired
	public VivaRealService(RealEstateAdService realEstateAdService, VivaRealConfig vivaRealConfig, GrupoZapConfig grupoZapConfig) {
		super(grupoZapConfig);
		this.realEstateAdService = realEstateAdService;
		this.vivaRealConfig = vivaRealConfig;
	}

	@Override
	public RealEstateAdWrapper getPaginatorRealEstateAd(Pageable page) throws Exception {
		
		return getPaginator(page, getListRealEstateAd(realEstateAdService.getRealEstateAd()));
	}	

	public boolean applyRule(RealEstateAd realEstateAd) {

		String businessType = realEstateAd.getPricingInfos().getBusinessType();
		BigDecimal rentalTotalPrice = realEstateAd.getPricingInfos().getRentalTotalPrice();
		BigDecimal price = realEstateAd.getPricingInfos().getPrice();
		BigDecimal lat = realEstateAd.getAddress().getGeoLocation().getLocation().getLat();
		BigDecimal lon = realEstateAd.getAddress().getGeoLocation().getLocation().getLon();
		String monthlyCondoFee = realEstateAd.getPricingInfos().getMonthlyCondoFee();

		BigDecimal paramMaxRentalTotalPrice = vivaRealConfig.getParamMaxRentalTotalPrice();
		BigDecimal paramMaxSalePrice = vivaRealConfig.getParamMaxSalePrice();
		BigDecimal fee = vivaRealConfig.getBoundingBoxFee();

		return (businessType.equals(SALE)
				&& price.compareTo(applyBoundingBoxRule(lat, lon, paramMaxSalePrice, fee)) <= 0 ||

				(businessType.equals(RENTAL)
						&& rentalTotalPrice
								.compareTo(applyBoundingBoxRule(lat, lon, paramMaxRentalTotalPrice, fee)) <= 0

						&& isNumber(monthlyCondoFee)
						// monthlyCondoFee < 30% valor do aluguel (rentalTotalPrice)
						&& new BigDecimal(monthlyCondoFee)
								.compareTo(new BigDecimal(0.3).multiply(rentalTotalPrice)) < 0)

						&& (lat.compareTo(BigDecimal.ZERO) > 0 && lon.compareTo(BigDecimal.ZERO) > 0));
	}

	private boolean isNumber(String str) {

		if (str == null || str.isEmpty()) {
			return false;
		}

		return str.chars().allMatch(Character::isDigit);

	}
}
