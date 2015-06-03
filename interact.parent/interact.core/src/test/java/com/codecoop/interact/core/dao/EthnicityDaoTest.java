package com.codecoop.interact.core.dao;
//
//
//import static org.junit.Assert.assertNotNull;
//import static org.junit.Assert.assertTrue;
//
//import java.util.Date;
//import java.util.Iterator;
//import java.util.List;
//
//import org.junit.Ignore;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.test.annotation.Rollback;
//import org.springframework.test.context.ContextConfiguration;
//import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
//import org.springframework.test.context.transaction.TransactionConfiguration;
//import org.springframework.transaction.annotation.Transactional;
//
//import com.codecoop.interact.core.dao.EthnicityDaoImpl;
//import com.codecoop.interact.core.domain.Ethnicity;
//
//
//
//@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
//@RunWith(SpringJUnit4ClassRunner.class)
//@TransactionConfiguration(transactionManager = "transactionManager")
//
//public class EthnicityDaoTest {
//	
//	@Autowired
//	private EthnicityDaoImpl ethnicityDaoImpl;
//	
//	@Test
//	@Transactional
//	public void testFindByID(){
//		Ethnicity product= ethnicityDaoImpl.findById(1L);
//		assertNotNull(product);
//	}
//
//	@Ignore
//	@Test
//	@Transactional
//	public void testFindAll(){
//		List<Ethnicity> ethnicityList = ethnicityDaoImpl.findAll();
//		assertTrue(ethnicityList.size() >0);
//		
//	}
//
//	@Test
//	@Transactional
//	@Rollback(false)
//	public void testSaveOrUpdate(){
//		System.out.println("hello");
//		Ethnicity ethnicity = new Ethnicity();
//		ethnicity.setEthnicity_type("Black");
//		ethnicity.setUserCreated("chandra");;
//		ethnicity.setDateCreated(new Date());
//        ethnicityDaoImpl.saveOrUpdate(ethnicity);
//    	assertTrue(ethnicity.getId()>0);
//	}
//	@Test
//	@Transactional
//	public void testListOfEthnicitys(){
//		List<Ethnicity> ethnicityList = ethnicityDaoImpl.findAll();
//		Iterator<Ethnicity> iterator = ethnicityList.iterator();
//		while (iterator.hasNext()) {
//			System.out.println(iterator.next().getEthnicity_type());
//		}
//		
//	}
//	@Test
//	@Transactional
//	public void testDeleteAll()
//	{
//		List<Ethnicity> ethnicityList = ethnicityDaoImpl.findAll();
//		Iterator<Ethnicity> iterator = ethnicityList.iterator();
//		while (iterator.hasNext()) {
//			ethnicityDaoImpl.delete(iterator.next());
//	    }
//	}
//}
//
