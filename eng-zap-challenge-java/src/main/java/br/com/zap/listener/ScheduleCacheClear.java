package br.com.zap.listener;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;

import br.com.zap.service.impl.RealEstateAdService;

@Component
public class ScheduleCacheClear {

	
	@Autowired
	private RealEstateAdService service;
	
	
	@Scheduled(fixedDelay = 1000 * 60 * 30)
    public void evictCaches() {		
        service.clearCache();
    }
	
}
