package com.codecoop.interact.core.dao;

import static org.junit.Assert.assertTrue;

import java.util.Calendar;
import java.util.Date;
import java.util.List;

import org.junit.Ignore;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.domain.ChangeInCondition;

@ContextConfiguration(locations = {"classpath:test-app-context.xml"})
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
public class ChangeInConditionDaoTest {

	@Autowired
	private ChangeInConditionDao changeInConditionDao;
	
	@Ignore
	@Test
	@Transactional
	public void testGetChangeInCondi(){
		
		List<ChangeInCondition> changeInConditionList =  changeInConditionDao.getChangeInCondByAttrId(1L);
		assertTrue(changeInConditionList.size()>0);
	}
	
	@Ignore
	@Test
	@Transactional
	public void testGetChangeInCondiByAttrIdAndStartedDate(){
		
		Date date = Calendar.getInstance().getTime();
		List<ChangeInCondition> changeInConditionList =  changeInConditionDao.getChangeInCondByAttrIdAndStartedDate(1L,date);
		assertTrue(changeInConditionList.size()>0);
	}
	
	@Ignore
	@Test
	@Transactional
	public void testgetChangeinCondByEpisodeId(){
		
		List<ChangeInCondition> changeInConditionList =  changeInConditionDao.getChangeinCondByEpisodeId(2L);
		assertTrue(changeInConditionList.size()>0);
		
	}
	
	
}
