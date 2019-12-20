package br.com.zap.wrapper;

import java.util.List;

import org.springframework.hateoas.RepresentationModel;

import br.com.zap.model.RealEstateAd;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealEstateAdWrapper extends RepresentationModel<RealEstateAdWrapper>{

	private List<RealEstateAd> listImovel;
	private Integer pageNumber;
    private Integer pageSize;
    private Integer totalCount;
    private Integer totalPage;
}
