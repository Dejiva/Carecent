package com.codecoop.interact.core.service;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dto.SignsSymptomsLabworkDto;
import com.codecoop.interact.core.enumpack.ChangeInConditionType;

@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
public class SignsSymptomsLabworkServiceTest {

	@Autowired
	private SignsSymptomsLabworkService signsSymptomsLabworkService;
	
	@Test
	@Transactional
	public void testGetSignsSymptomsLabworkBySignFlag(){
		List<SignsSymptomsLabworkDto> signsSymptomsDtoList = signsSymptomsLabworkService.getSignsSymptomsLabWorkByFlagType(ChangeInConditionType.SIGN_FLAG);
		for(SignsSymptomsLabworkDto signsSymptomsLabwork:signsSymptomsDtoList)
		    System.out.println(signsSymptomsLabwork.getId()+" "+signsSymptomsLabwork.getSignsSymptomsLabworkName());
		assertTrue(signsSymptomsDtoList.size()>0);
		
	}
	
}
