package br.com.zap.service.impl;

import java.util.Arrays;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.client.RestTemplate;

import br.com.zap.config.GrupoZapConfig;
import br.com.zap.model.RealEstateAd;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Service
@Transactional
public class RealEstateAdService {

	@Autowired
	private GrupoZapConfig gZapConfig;

	private List<RealEstateAd> listRealEstateAd = null;

	public RealEstateAdService() {
	}

	public RealEstateAdService(List<RealEstateAd> listRealEstateAd) {

		this.listRealEstateAd = listRealEstateAd;
	}

	@Cacheable("realStateAd")
	public List<RealEstateAd> getRealEstateAd() throws Exception {

		if (listRealEstateAd == null || listRealEstateAd.size() <= 0) {
			log.info("Iniciando a busca de dados...");

			ResponseEntity<RealEstateAd[]> response = new RestTemplate().getForEntity(gZapConfig.getUrlBusca(),
					RealEstateAd[].class);

			log.info("Busca finalizada...");
			log.info("Status code retornado = " + response.getStatusCodeValue());

			if (response.getStatusCode().is2xxSuccessful()) {
				return Arrays.asList(response.getBody());
			}
			throw new Exception("Erro ao buscar anuncios.\n Código de erro = " + response.getStatusCodeValue());

		}

		return listRealEstateAd;

	}
	
	@CacheEvict(value = {"realStateAd"}, allEntries = true)
	public void clearCache() {		
		log.info("Limpando e atualizando cache...");
		
		try {
			getRealEstateAd();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
