package com.codecoop.interact.core.dao;
import static org.junit.Assert.assertNotNull;

import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.CarePathTracker;
import com.codecoop.interact.core.domain.PatientEpisode;
@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")

public class SampleDaoTest {
	
	@Autowired
	private MessageAlertDetailsDaoImpl messageBoxDaoImpl;
	
	
	@Autowired
	private CarePathTrackerDaoImpl carePathTrackerDao;
	
	@Autowired
	private PatientEpisodeDao patientEpisodeDao;

	@Autowired
	private CarePathDaoImpl carePathDao;
	
	@Ignore
	@Test
	@Transactional
	@Rollback(true)
	public void test()
	{
//		System.out.println("chandramohan........................................");
//		PatientEpisode patientEpisode=patientEpisodeDao.findById(66l);
//		CarePath carePath=carePathDao.findById(5l);
//		//CarePathTracker carePathTracker=carePathTrackerDao.findByStepId(patientEpisode,carePath,1);
//		System.out.println(carePathTracker);
//		assertNotNull(carePathTracker);
//		
	}


}

