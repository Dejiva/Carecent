package com.codecoop.interact.core.dao;
import static org.junit.Assert.assertNotNull;
import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.domain.SignsSymptomsLabwork;
import com.codecoop.interact.core.enumpack.ChangeInConditionType;


@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
public class SignsSymptomsLabworkDaoTest {

	@Autowired
	private SignsSymptomsLabworkDao signsSymptomsLabworkDao;
	
	@Test
	@Transactional
	public void findById(){
		
	SignsSymptomsLabwork signsSymptomsLabwork = signsSymptomsLabworkDao.findById(1L);
	assertNotNull(signsSymptomsLabwork);
	}
	
	@Test
	@Transactional
	public void findSignSymbolsBySignFlag(){
	
	List<SignsSymptomsLabwork> signsList =	 signsSymptomsLabworkDao.getSignSymptomsBySignFlag();
	
	assertTrue(signsList.size()>0);
	
	}
	
	@Test
	@Transactional
	public void findSignSymbolsBySymptomFlag(){
		
		List<SignsSymptomsLabwork> symptomsList = signsSymptomsLabworkDao.getSignSymptomsBySymptomFalg();
		assertTrue(symptomsList.size()>0);
	}
	
	@Test
	@Transactional
	public void findSignSymbolByLabWrokFlag(){
		
		List<SignsSymptomsLabwork> labWorkList = signsSymptomsLabworkDao.getSignSymptomsByLabWorkFalg();
		assertTrue(labWorkList.size()>0);
	}
	
	@Test
	@Transactional
	public void findByFlag(){
		
		List<SignsSymptomsLabwork> flagList = signsSymptomsLabworkDao.getSignSymptomsByFalgType(ChangeInConditionType.SIGN_FLAG);
		
		assertTrue(flagList.size()>0);
		
	}
}
