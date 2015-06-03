package com.codecoop.interact.web.controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.dto.MessageDto;
import com.codecoop.interact.core.dto.PaginationDto;
import com.codecoop.interact.core.service.FacilityStaffService;
import com.codecoop.interact.core.service.MessageService;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.web.model.SessionManagedLists;

@RequestMapping("/message")
@Controller
public class MessageController extends SessionManagedLists {
	private static final Logger LOG = Logger.getLogger(MessageController.class);
	@Autowired
	MessageService messageService;
	@Autowired
	FacilityStaffService facilityStaffService;
	@RequestMapping(value="/getMessages", method={RequestMethod.GET})
	 	public @ResponseBody PaginationDto getMessages(@RequestParam("msgSubject") String msgSubject,HttpServletRequest request){
	    PaginationDto paginationDto=new PaginationDto();
	 	List<MessageDto> limitedMsgesList=new ArrayList<MessageDto>();
     	HttpSession session=request.getSession();
		Long staffId=(Long) session.getAttribute("staffId");
		Long facilityId=(Long) session.getAttribute("facilityId");
		String msgType=request.getParameter("message");	
		
		int page=1;
	     int recordsPerPage = 5;
	     if(request.getParameter("pageNo") != null &&  request.getParameter("pageNo")!=""){
	            page = Integer.parseInt(request.getParameter("pageNo"));
	     }
	     if(facilityId!=null && staffId!=null ){
	         limitedMsgesList=messageService.getLimitedMessages(staffId,facilityId,msgType,(page-1)*recordsPerPage,recordsPerPage,msgSubject);	
	         paginationDto=new PaginationDto();
	         paginationDto.setMessageDtoList(limitedMsgesList);
	         paginationDto.setCurrentPage(page);
	        
	          List<MessageDto> allMsgesList=messageService.getMessages((Long)session.getAttribute("staffId"),(Long) session.getAttribute("facilityId"),msgSubject,Constants.MESSAGE);
	 	 		int totalCount=allMsgesList.size();
	 	 		int noOfPages = (int) Math.ceil(totalCount * 1.0 / recordsPerPage);
	 	 		paginationDto.setNoOfpages(noOfPages);
	 	 		session.setAttribute("noOfPages",noOfPages);
	 	 		session.setAttribute("currentPage",page);
	 	 		
	     }
	   
	 	
	   
		return paginationDto;
	     }
	
	@RequestMapping(value="/getAlerts", method={RequestMethod.GET})
	public @ResponseBody List<MessageDto> getALerts(HttpServletRequest request) {
		List<MessageDto> messageDtoList=new ArrayList<MessageDto>();
		HttpSession session=request.getSession();
		Long staffId=(Long)session.getAttribute("staffId");
		Long facilityId=(Long)session.getAttribute("facilityId");
		String alertType=request.getParameter("alert");
		  if(facilityId!=null && staffId!=null ){
	           messageDtoList=messageService.getAlerts(staffId,facilityId,alertType);
		  }
	    return messageDtoList;
	}
	
	@RequestMapping(value = "/deleteMessages", method = {RequestMethod.GET })
	public  @ResponseBody Long deleteMessages(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String messageAlertDetailsId1 = request.getParameter("messageAlertDetailsId");
		Long messageAlertDetailsId= Long.parseLong(messageAlertDetailsId1);
		return messageService.deleteMessages(messageAlertDetailsId);

	}	
	
	@RequestMapping(value = "/deleteAlerts", method = {RequestMethod.GET })
	public  @ResponseBody Long deleteAlerts(HttpServletRequest request) {
		HttpSession session=request.getSession();
		String messageAlertDetailsId1 = request.getParameter("messageAlertDetailsId");
		Long messageAlertDetailsId= Long.parseLong(messageAlertDetailsId1);
		return messageService.deleteAlerts(messageAlertDetailsId);

	}	
	
	@RequestMapping(value = "/getMsgSubj", method = {RequestMethod.GET })
	public  @ResponseBody List<String> getMsgSubj(HttpServletRequest request) {
		
		HttpSession session=request.getSession();
		List<String> listOfsubjs=new ArrayList<String>();
		Long staffId=(Long)session.getAttribute("staffId");
		Long facilityId=(Long)session.getAttribute("facilityId");
		if(facilityId!=null && staffId!=null ){
			listOfsubjs=messageService.getMsgSubj(facilityId,staffId);
		}
		return listOfsubjs;
	}
	
	@RequestMapping(value = "/findByMsgsubj", method = {RequestMethod.GET })
	public  @ResponseBody PaginationDto findByMsgsubj(@RequestParam("msgSubject") String msgSubject,HttpServletRequest request) {
		HttpSession session=request.getSession();
		int page=1;
		if(request.getParameter("pageNo") != null &&  request.getParameter("pageNo")!=""){
			page = Integer.parseInt(request.getParameter("pageNo"));
		}
		Long staffId=(Long) session.getAttribute("staffId");
		Long facilityId=(Long)session.getAttribute("facilityId");
		int recordsPerPage=5;
		List<MessageDto> listOfMessages=messageService.findByMsgsubj(msgSubject,facilityId,staffId,(page-1)*recordsPerPage,recordsPerPage);
		PaginationDto  paginationDto=new PaginationDto();
		paginationDto.setMessageDtoList(listOfMessages);
		paginationDto.setCurrentPage(page);
		List<MessageDto> allMsgesList=messageService.getMessages(staffId,facilityId,msgSubject,Constants.MESSAGE);
		int totalCount=allMsgesList.size();
	 	int noOfPages = (int) Math.ceil(totalCount * 1.0 / recordsPerPage);
	 	paginationDto.setNoOfpages(noOfPages);
	 	return paginationDto;
	
	}

}