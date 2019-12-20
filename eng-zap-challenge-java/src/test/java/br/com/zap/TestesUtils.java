package br.com.zap;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.databind.ObjectMapper;

import br.com.zap.model.RealEstateAd;

public class TestesUtils {	
	
	public static List<RealEstateAd> getListMockZap(Class<?> clazz){
		return getListMock(clazz,"/mocks/jsonZap.json");
	}
	
	public static List<RealEstateAd> getListMockViva(Class<?> clazz){
		return getListMock(clazz,"/mocks/jsonViva.json");
	}
	
	public static List<RealEstateAd> getListMockNaoElegivel(Class<?> clazz){
		return getListMock(clazz,"/mocks/jsonNaoElegivel.json");
	}
	
	private static List<RealEstateAd> getListMock(Class<?> clazz, String path){
		ObjectMapper conv = new ObjectMapper();
		try {
			return Arrays.asList(conv.readValue(getJsonMock(clazz, path), RealEstateAd[].class));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	private static String getJsonMock(Class<?> clazz, String path) throws IOException {

		InputStream in = clazz.getResourceAsStream(path);

		String json = "";

		if (in != null) {

			byte[] buffer = new byte[in.available()];
			in.read(buffer);
			json = new String(buffer);
		}

		return json;
	}

}
