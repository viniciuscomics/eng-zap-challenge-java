package br.com.zap.service;

import java.util.List;

import org.junit.After;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import br.com.zap.EngZapChallengeJavaApplication;
import br.com.zap.TestesUtils;
import br.com.zap.config.GrupoZapConfig;
import br.com.zap.config.VivaRealConfig;
import br.com.zap.model.RealEstateAd;
import br.com.zap.service.impl.RealEstateAdService;
import br.com.zap.service.impl.VivaRealService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EngZapChallengeJavaApplication.class })
public class VivaRealServiceTest {
	
	private RealEstateAdService realEstateAdService = null;
	
	@Autowired
	private GrupoZapConfig grupoZapConfig;
	
	@Autowired
	private VivaRealConfig vivaRealConfig;
	
	private VivaRealService vivaService;

	private List<RealEstateAd> listViva = null;

	private List<RealEstateAd> listZap = null;

	private List<RealEstateAd> listNaoElegivel = null;
	
	@Before
	public void setUp() throws Exception {

		listZap = TestesUtils.getListMockZap(getClass());

		listViva = TestesUtils.getListMockViva(getClass());

		listNaoElegivel = TestesUtils.getListMockNaoElegivel(getClass());

	}

	@After
	public void tearDown() throws Exception {
	}
	
	@Test
	public void testReturnNotNull() {

		try {

			realEstateAdService = new RealEstateAdService(listViva);

			vivaService = new VivaRealService(realEstateAdService, vivaRealConfig,grupoZapConfig);

			List<RealEstateAd> list = vivaService.getListRealEstateAd(listViva);

			Assert.assertNotNull(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testReturnListViva() {

		try {

			realEstateAdService = new RealEstateAdService(listViva);

			vivaService = new VivaRealService(realEstateAdService, vivaRealConfig,grupoZapConfig);

			List<RealEstateAd> list = vivaService.getListRealEstateAd(listViva);

			Assert.assertEquals(listZap.size(), list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testValidacaoListaZap() throws Exception {

		try {

			realEstateAdService = new RealEstateAdService(listZap);

			vivaService = new VivaRealService(realEstateAdService, vivaRealConfig,grupoZapConfig);

			List<RealEstateAd> list = vivaService.getListRealEstateAd(listZap);

			Assert.assertEquals(0, list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testValidacaoListaNaoElegivel() throws Exception {

		try {

			realEstateAdService = new RealEstateAdService(listNaoElegivel);

			vivaService = new VivaRealService(realEstateAdService, vivaRealConfig,grupoZapConfig);

			List<RealEstateAd> list = vivaService.getListRealEstateAd(listNaoElegivel);

			Assert.assertEquals(0, list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
