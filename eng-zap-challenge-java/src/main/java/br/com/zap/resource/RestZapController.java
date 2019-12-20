package br.com.zap.resource;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.web.PageableDefault;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.zap.service.impl.ZapService;
import br.com.zap.wrapper.RealEstateAdWrapper;

@RestController
@RequestMapping("/zap")
public class RestZapController {

	@Autowired
	private ZapService zapService;	
	
	@GetMapping
	public ResponseEntity<RealEstateAdWrapper> getRealEstateAdZap(@PageableDefault(page = 0, size = 50)Pageable pageable){		
		
		RealEstateAdWrapper realEstateAdWrapper = null;
		try {
			realEstateAdWrapper = zapService.getPaginatorRealEstateAd(pageable);
		} catch (Exception e) {
			return ResponseEntity.status(HttpStatus.NOT_FOUND).body(null);
		}
		
		return ResponseEntity.status(HttpStatus.OK).body(realEstateAdWrapper);
	}
}
