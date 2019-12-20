package br.com.zap.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RealEstateAd implements Serializable{

	 
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private Integer usableAreas;
	private String listingType;
	private String createdAt;
	private String listingStatus;
	private String id;
	private Integer parkingSpaces;
	private String updatedAt;
	private boolean owner;
	private List<String> images = new ArrayList<>();
	private Address address;
	private Integer bathrooms;
	private Integer bedrooms;
	private PricingInfos pricingInfos;
	
}