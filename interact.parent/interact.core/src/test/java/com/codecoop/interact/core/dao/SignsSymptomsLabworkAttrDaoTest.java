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

import com.codecoop.interact.core.domain.SignsSymptomsLabworkAttr;


@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
public class SignsSymptomsLabworkAttrDaoTest {

	@Autowired
	private SignsSymptomsLabworkAttrDao signsSymptomsLabworkAttrDao;
	
	@Test
	@Transactional
	public void testfindById(){
		
		SignsSymptomsLabworkAttr signsSymptomsLabworkAttr=	signsSymptomsLabworkAttrDao.findById(1L);
		assertNotNull(signsSymptomsLabworkAttr);
				
	}
	
	@Test
	@Transactional
	public void testFindByParentId(){
		
	List<SignsSymptomsLabworkAttr> attrList =signsSymptomsLabworkAttrDao.findByParentId(6L);
	
	assertTrue(attrList.size()>0);
	
	}
	
}
