package br.com.zap.service.impl;

import java.math.BigDecimal;
import java.math.MathContext;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import br.com.zap.config.GrupoZapConfig;
import br.com.zap.config.ZapConfig;
import br.com.zap.model.RealEstateAd;
import br.com.zap.wrapper.RealEstateAdWrapper;

@Service
public class ZapService extends AbstractRealEstateAd {

	private RealEstateAdService realEstateAdService;	
	private ZapConfig zapConfig;	

	@Autowired
	public ZapService(RealEstateAdService realEstateAdService,ZapConfig zapConfig, GrupoZapConfig grupoZapConfig) {
		super(grupoZapConfig);
		this.realEstateAdService = realEstateAdService;
		this.zapConfig = zapConfig;
	}

	@Override
	public RealEstateAdWrapper getPaginatorRealEstateAd(Pageable page) throws Exception {		

		List<RealEstateAd> listRealEstate = realEstateAdService.getRealEstateAd();		

		return getPaginator(page, getListRealEstateAd(listRealEstate));
	}	

	public boolean applyRule(RealEstateAd realEstateAd) {
		
		String businessType = realEstateAd.getPricingInfos().getBusinessType();
		BigDecimal rentalTotalPrice = realEstateAd.getPricingInfos().getRentalTotalPrice();
		BigDecimal paramMinRentalTotalPrice = zapConfig.getParamMinRentalTotalPrice();
		BigDecimal price = realEstateAd.getPricingInfos().getPrice();
		BigDecimal paramMinSalePrice = zapConfig.getParamMinSalePrice();
		int usableAreas = realEstateAd.getUsableAreas();
		BigDecimal lat = realEstateAd.getAddress().getGeoLocation().getLocation().getLat();
		BigDecimal lon = realEstateAd.getAddress().getGeoLocation().getLocation().getLon();
		BigDecimal fee = zapConfig.getBoundingBoxFee();
		
		return (businessType.equals(RENTAL) 
				&& rentalTotalPrice.compareTo(paramMinRentalTotalPrice)>=0 ||
						businessType.equals(SALE) 
								&& price.compareTo(paramMinSalePrice) >=0

                 && (usableAreas > 0                     
                     && (price.divide(new BigDecimal(usableAreas),MathContext.DECIMAL128))
                     .compareTo(applyBoundingBoxRule(lat,lon, paramMinRentalTotalPrice,fee))
                     <=0)
                 
             && (lat.compareTo(BigDecimal.ZERO) >=1
            		 && lon.compareTo(BigDecimal.ZERO) >=1));
	}	

}
