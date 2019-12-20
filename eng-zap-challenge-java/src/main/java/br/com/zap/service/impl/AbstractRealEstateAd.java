package br.com.zap.service.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.stream.Collectors;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.hateoas.Link;

import br.com.zap.config.GrupoZapConfig;
import br.com.zap.model.RealEstateAd;
import br.com.zap.wrapper.RealEstateAdWrapper;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public abstract class AbstractRealEstateAd {

	@Autowired 
	private HttpServletRequest httpServletRequest;

	
	private GrupoZapConfig grupoZapConfig;
	protected final String RENTAL = "RENTAL";
	protected final String SALE = "SALE";
	
	@Autowired
	public AbstractRealEstateAd(GrupoZapConfig grupoZapConfig) {
		this.grupoZapConfig = grupoZapConfig;
	}

	public abstract RealEstateAdWrapper getPaginatorRealEstateAd(Pageable page) throws Exception;	
	public abstract boolean applyRule(RealEstateAd realEstateAd);

	public List<RealEstateAd> getListRealEstateAd(List<RealEstateAd> listRealEstate) {
		log.info("Lista de registros recebida do WS com o total de "+listRealEstate.size());

		List<RealEstateAd> listFiltrada = 
				listRealEstate.stream().filter(e-> applyRule(e)==true).collect(Collectors.toList());		

		log.info("Lista filtrada com total de "+listFiltrada.size());
		
		return listFiltrada;
	}
	
	protected RealEstateAdWrapper getPaginator(Pageable pageable, List<RealEstateAd> listRealStateAd) {

		RealEstateAdWrapper realStateAdWrapper = new RealEstateAdWrapper();
		List<RealEstateAd> subListRealStateAd = null;

		if(pageable == null) {
			pageable = PageRequest.of(0, 10);
		}
		
		int offSet = (int) pageable.getOffset();

		if (offSet > 0) {
			offSet = (int) pageable.getOffset() - pageable.getPageSize();
		}

		int totalPerPage = pageable.getPageSize();

		if (totalPerPage > listRealStateAd.size()) {
			totalPerPage = listRealStateAd.size();
		}

		if (listRealStateAd.size() < offSet) {

			if ((listRealStateAd.size() - offSet) > totalPerPage) {
				subListRealStateAd = listRealStateAd.subList(offSet, totalPerPage);
			} else {
				subListRealStateAd = listRealStateAd.subList(offSet, listRealStateAd.size() - offSet);
			}
		} else {
			subListRealStateAd = listRealStateAd.subList(0, totalPerPage);
		}

		int numPage = pageable.getPageNumber() == 0 ? 1 : pageable.getPageNumber();
		int totalPage = listRealStateAd.size() / totalPerPage;
		totalPage += (listRealStateAd.size() % totalPerPage > 0 ? 1 : 0);

		realStateAdWrapper.setListImovel(subListRealStateAd);

		if (numPage > 1) {
			realStateAdWrapper.add(
					new Link(httpServletRequest.getRequestURL() + "?page=" + (numPage - 1) + "&size=" + totalPerPage,
							"previous"));
		}
		realStateAdWrapper
				.add(new Link(httpServletRequest.getRequestURL() + "?page=" + (numPage) + "&size=" + totalPerPage));

		if ((numPage + 1) < totalPage) {
			realStateAdWrapper.add(new Link(
					httpServletRequest.getRequestURL() + "?page=" + (numPage + 1) + "&size=" + totalPerPage, "next"));
		}

		realStateAdWrapper.add(new Link(
				httpServletRequest.getRequestURL() + "?page=" + (totalPage) + "&size=" + totalPerPage, "last"));
		realStateAdWrapper.setPageNumber(numPage);
		realStateAdWrapper.setPageSize(totalPerPage);
		realStateAdWrapper.setTotalPage(totalPage);
		realStateAdWrapper.setTotalCount(listRealStateAd.size());

		return realStateAdWrapper;
	}

	protected BigDecimal applyBoundingBoxRule(BigDecimal lat, BigDecimal lon, BigDecimal ValorMetroQuadrado,
			BigDecimal fee) {

		return verifyBoundingBox(lat, lon) ? ValorMetroQuadrado.multiply(fee) : ValorMetroQuadrado;
	}

	protected boolean verifyBoundingBox(BigDecimal lat, BigDecimal lon) {
		BigDecimal minlon = grupoZapConfig.getMinlon();
		BigDecimal minlat = grupoZapConfig.getMinlat();
		BigDecimal maxlon = grupoZapConfig.getMaxlon();
		BigDecimal maxlat = grupoZapConfig.getMaxlat();

		return lat.compareTo(minlat) >= 0 && lat.compareTo(maxlat) <= 0 && lon.compareTo(minlon) >= 0
				&& lon.compareTo(maxlon) <= 0;
	}

}
