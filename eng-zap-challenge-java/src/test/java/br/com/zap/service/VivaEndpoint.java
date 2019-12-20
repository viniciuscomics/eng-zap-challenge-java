package br.com.zap.service;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.HttpClientBuilder;
import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import br.com.zap.wrapper.RealEstateAdWrapper;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class VivaEndpoint {
	
	@LocalServerPort
	private int port;	

	private final String url = "http://localhost:";
	
	@Before
	public void setUp() throws Exception {
	}

	@After
	public void tearDown() throws Exception {
	}

	@Test
	public void testReturn200() {

		ResponseEntity<RealEstateAdWrapper> resp = new RestTemplate().getForEntity(url + port + "/vivareal",
				RealEstateAdWrapper.class);

		Assert.assertEquals(200, resp.getStatusCodeValue());

	}

	@Test
	public void testReturn404() throws ClientProtocolException, IOException {

		ResponseEntity<RealEstateAdWrapper> resp = new RestTemplate().getForEntity(url + port + "/vivareal",
				RealEstateAdWrapper.class);

		Assert.assertEquals(200, resp.getStatusCodeValue());

		HttpUriRequest request = new HttpGet(url + port + "/vivareal?page=" + (resp.getBody().getTotalPage() + 1) + "&size="
				+ resp.getBody().getPageSize());
		
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(404, response.getStatusLine().getStatusCode());

	}
	
	@Test
	public void testReturnPaginator() throws ClientProtocolException, IOException {

		ResponseEntity<RealEstateAdWrapper> resp = new RestTemplate().getForEntity(url + port + "/vivareal",
				RealEstateAdWrapper.class);

		Assert.assertEquals(200, resp.getStatusCodeValue());

		HttpUriRequest request = new HttpGet(url + port + "/vivareal?page=" + (resp.getBody().getTotalPage()) + "&size="
				+ resp.getBody().getPageSize());
		
		HttpResponse response = HttpClientBuilder.create().build().execute(request);
		Assert.assertEquals(200, response.getStatusLine().getStatusCode());

	}

}
