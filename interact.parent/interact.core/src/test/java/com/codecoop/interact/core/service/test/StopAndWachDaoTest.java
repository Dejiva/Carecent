package com.codecoop.interact.core.service.test;

import static org.junit.Assert.assertTrue;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.StopAndWatchDaoImpl;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.StopAndWatch;

@ContextConfiguration(locations = { "classpath:test-app-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
public class StopAndWachDaoTest {

	@Autowired
	StopAndWatchDaoImpl stopAndWatchDaoImpl;
	@Autowired
	FacilityStaffDaoImpl facilityStaffDaoImpl;
	@Test
	@Transactional
	//@Rollback(false)
	public void testDeleteAll() {
		FacilityStaff fStaff= facilityStaffDaoImpl.findFacilityStaff(9L, 1L);
		
		System.out.println("chandra............"+fStaff);
	//	assertTrue(fStaff !=null);
	}
	
	
	

}
