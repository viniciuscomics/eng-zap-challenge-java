package br.com.zap.model;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GeoLocation implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private String precision;
	private Location location;	
}