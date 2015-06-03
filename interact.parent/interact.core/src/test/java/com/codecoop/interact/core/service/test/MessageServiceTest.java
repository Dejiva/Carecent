/*package com.codecoop.interact.core.service.test;

import java.util.List;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.transaction.TransactionConfiguration;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.MessageAlertDaoImpl;
import com.codecoop.interact.core.domain.MessageAlert;
import com.codecoop.interact.core.domain.MessageEligibilityStaff;
import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.service.MessageService;

@ContextConfiguration(locations = { "classpath:test-app-context.xml" })
@RunWith(SpringJUnit4ClassRunner.class)
@TransactionConfiguration(transactionManager = "transactionManager")
public class MessageServiceTest {

	@Autowired
	private MessageService messageSystemService;
	@Autowired
	 private MessageAlertDaoImpl messageAlertDaoImpl;
	@Autowired
	private MessageEligibilityStaffDaoImpl messageEligibilityStaffDao;
	@Test
	@Transactional
	public void testGetInboxMessages() {

		List<MessageDto> messageDtoList = messageSystemService
				.getInboxMessages(11L);
		System.out.println("Inbox messages Size......." + messageDtoList.size());
		}

	@Test
	@Transactional
	public void testGetTrashMessages() {

		List<MessageDto> messageDtoList1 = messageSystemService
				.getTrashMessages(11L);
		System.out.println("inboxTrashMessages Size......."
				+ messageDtoList1.size());

	}

	@Test
	@Transactional
	public void testGetSentMessages() {

		List<MessageDto> messageDtoList1 = messageSystemService
				.getSentMessages(11L);
		System.out.println("sentMessages Size......." + messageDtoList1.size());

	}

	@Test
	@Transactional
	@Rollback(false)
	public void testDeleteMessage() {

		messageSystemService.deleteMessage(20L);
		System.out.println("message is delted from inbox");
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testReadInboxeMessage() {

		messageSystemService.readInboxMessage(18L);
		System.out.println("message is read from inbox");
	}

	@Test
	@Transactional
	@Rollback(false)
	public void testSendMessage() {

		messageSystemService.sendMessagae();
		System.out.println("message is sended");
	}
	
	
	@Test
	@Transactional
	@Rollback(false)
	public void testSaveMessage() {
     
     List<MessageEligibilityStaff> messageEligibiltyStaffList = messageEligibilityStaffDao.getEligibiltyStaff();
      for(MessageEligibilityStaff messageEligibilityStaff:messageEligibiltyStaffList){
    	    List<Long> staffList= messageEligibilityStaffDao.getStaffList(messageEligibilityStaff.getResponsibilitiesId());
    	    MessageDto messageDto=new MessageDto();
    	    messageDto.setStaffList(staffList);
    	    messageDto.setMsgBody(messageEligibilityStaff.getMessageTemplate());
    	    
        }
      
    	 }
	  //
		
	
 }

*/