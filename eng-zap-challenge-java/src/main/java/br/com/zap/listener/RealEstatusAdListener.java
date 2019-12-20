package br.com.zap.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationListener;
import org.springframework.context.annotation.Profile;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.core.Ordered;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import br.com.zap.service.impl.RealEstateAdService;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Profile("!test")
@Component
@Order(Ordered.HIGHEST_PRECEDENCE)
public class RealEstatusAdListener implements ApplicationListener<ContextRefreshedEvent> {

	@Autowired
	private RealEstateAdService realEstateAdService;	
	
	@Override
	public void onApplicationEvent(ContextRefreshedEvent event) {
		
		try {			
			realEstateAdService.getRealEstateAd();
		} catch (Exception e) {
			log.error(e.getMessage(), e);
		}
		
	}

}
