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
import br.com.zap.config.ZapConfig;
import br.com.zap.model.RealEstateAd;
import br.com.zap.service.impl.RealEstateAdService;
import br.com.zap.service.impl.ZapService;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = { EngZapChallengeJavaApplication.class })
public class ZapServiceTest {

	@Autowired
	private ZapConfig zapConfig;
	
	@Autowired
	private GrupoZapConfig grupoZapConfig;

	private ZapService zapService = null;

	private RealEstateAdService realEstateAdService = null;

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

			realEstateAdService = new RealEstateAdService(listZap);

			zapService = new ZapService(realEstateAdService, zapConfig,grupoZapConfig);

			List<RealEstateAd> list = zapService.getListRealEstateAd(listZap);

			Assert.assertNotNull(list);

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testReturnListZap() {

		try {

			realEstateAdService = new RealEstateAdService(listZap);

			zapService = new ZapService(realEstateAdService, zapConfig,grupoZapConfig);

			List<RealEstateAd> list = zapService.getListRealEstateAd(listZap);

			Assert.assertEquals(listZap.size(), list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	@Test
	public void testValidacaoListaViva() throws Exception {

		try {

			realEstateAdService = new RealEstateAdService(listViva);

			zapService = new ZapService(realEstateAdService, zapConfig,grupoZapConfig);

			List<RealEstateAd> list = zapService.getListRealEstateAd(listViva);

			Assert.assertEquals(0, list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
	@Test
	public void testValidacaoListaNaoElegivel() throws Exception {

		try {

			realEstateAdService = new RealEstateAdService(listNaoElegivel);

			zapService = new ZapService(realEstateAdService, zapConfig, grupoZapConfig);

			List<RealEstateAd> list = zapService.getListRealEstateAd(listNaoElegivel);

			Assert.assertEquals(0, list.size());

		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}
