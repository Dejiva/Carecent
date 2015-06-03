package com.codecoop.interact.core.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.StopAndWatchDaoImpl;
import com.codecoop.interact.core.dto.StopAndWatchHistoryDto;
import com.codecoop.interact.core.service.StopAndWatchService;

@ContextConfiguration(locations = { "classpath:test-app-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
public class StopAndWachServiceTest {

	@Autowired
	StopAndWatchDaoImpl stopAndWatchDaoImpl;
	@Autowired
	StopAndWatchService stopAndWatchService;
	@Test
	@Transactional
	
	public void showStopAndWatchHistory() {
		List<StopAndWatchHistoryDto> stopAndWatchHistoryDtoList = stopAndWatchService.showStopAndwatchHistoryOfPatient(2L,1L);
		for (StopAndWatchHistoryDto stopAndWatchHistoryDto : stopAndWatchHistoryDtoList)
		{	System.out.println("------------------------------------------------------");
		    System.out.println("--"+stopAndWatchHistoryDto.getRepotedBy()+"\t"+stopAndWatchHistoryDto.getRepotedDate()+"-----");
		    System.out.println("--SymtemsList-");
		    for(String symtom:stopAndWatchHistoryDto.getSymtomsList())
		    {
		    	System.out.println("--"+symtom+"\t------------");
		    }
			
		}
	}

}
