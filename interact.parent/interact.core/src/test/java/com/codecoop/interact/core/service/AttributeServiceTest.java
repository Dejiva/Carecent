package com.codecoop.interact.core.service;

import java.util.HashMap;
import java.util.Map;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dto.AdmissionAttributesValuesDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app-context.xml"})
@Transactional
public class AttributeServiceTest {
	
	@Autowired
	AttributesService attributesService;
	
	@Test
	public void testSaveAttributeValues(){
		AdmissionAttributesValuesDto dto = new AdmissionAttributesValuesDto();
		
		dto.setpEpisodeId(6L);
		Map<String, String>  valuesMap = new HashMap<String, String>();
		valuesMap.put("KCIVS_TIME_TAKEN", "10");
		valuesMap.put("KCIVS_PAIN_RATING", "20");
		valuesMap.put("KCIVS_N/A", "TRUE");
		dto.setAttributeValuesMap(valuesMap);
		
		attributesService.saveAdmissionAttributesValues(dto);
	}
	
	@Test
	public void testGetAttributeValues(){
		attributesService.getPatientAdmissionAttributeValuesAsMap(6L);
		
	}
}
