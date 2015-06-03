package com.codecoop.interact.web.controller;


import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.dto.QIEvaluateNdManageCICDto;
import com.codecoop.interact.core.dto.QIHospInfoResultDto;
import com.codecoop.interact.core.dto.QIHospTrnsInfoDto;
import com.codecoop.interact.core.dto.QIHospitalTransferInfoDto;
import com.codecoop.interact.core.dto.SymptomsAgainstUnplannedResponseDto;
import com.codecoop.interact.core.service.QIGraphsService;
import com.codecoop.interact.web.model.QIMedicalEvaluationResponse;
import com.codecoop.interact.web.model.SessionManagedLists;

@RequestMapping("/qiGraphs")
@Controller
public class QIController extends SessionManagedLists {
	private static final Logger LOG = Logger.getLogger(QIController.class);
	@Autowired
	QIGraphsService qiGraphsService;
	@RequestMapping(value="/symptomsAgainstUnplanned", method={RequestMethod.GET})
	public @ResponseBody SymptomsAgainstUnplannedResponseDto getDataForSymptomsAgainstUnplanned(HttpServletRequest request) throws ParseException{

		HttpSession session=request.getSession();
		Long facilityId=(Long) session.getAttribute("facilityId");
		//Long facilityId=29l;
		//		String startDate="2015-04-27";
		//		String endDate="2016-04-27";
		String fromDate = null;
		String toDate = null;
		if(!StringUtils.isEmpty(request.getParameter("fromDate")) && !StringUtils.isEmpty(request.getParameter("toDate"))){
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
		}
		return  qiGraphsService.getDataForSymptomsAgainstUnplanned(facilityId,fromDate,toDate);

	}

	@RequestMapping(value="/hospitalTransferInformation", method={RequestMethod.GET})
	public @ResponseBody QIHospInfoResultDto hospitalTransferInformation(HttpServletRequest request){
		HttpSession session=request.getSession();
		Long facilityId=(Long) session.getAttribute("facilityId");
		String fromDate = null;
		String toDate = null;
		if(StringUtils.isEmpty(request.getParameter("fromDate")) && StringUtils.isEmpty(request.getParameter("toDate"))){
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
		}
		return qiGraphsService.getHospitalTransferStatisticsOfCurrentFacilityQIG(facilityId,fromDate,toDate);
	}

	@RequestMapping(value="/qiPage", method={RequestMethod.GET})
	public  String qiPage(){
		return "quality_improvement";
	}

	@RequestMapping(value="/actionsTakenToEvalNdMngCIC", method={RequestMethod.GET})
	public @ResponseBody QIMedicalEvaluationResponse evaluateNdManageChangeInCondition(HttpServletRequest request){
		HttpSession session=request.getSession();
		Long facilityId=(Long) session.getAttribute("facilityId");
		String fromDate = null;
		String toDate = null;
		if(!StringUtils.isEmpty(request.getParameter("fromDate")) && !StringUtils.isEmpty(request.getParameter("toDate"))){
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
		}
		QIMedicalEvaluationResponse response = new QIMedicalEvaluationResponse();
		List<QIHospTrnsInfoDto> list = new ArrayList<QIHospTrnsInfoDto>();
		QIEvaluateNdManageCICDto cicActionsDto =  null;
		try{
			cicActionsDto =  qiGraphsService.getEvaluateNdManageChangeInConditionQIG(facilityId,fromDate,toDate);
		}catch(Exception e){
			LOG.error(e.getMessage(), e);
		}
		Long nppaCount =0l;Long mdCount=0l;Long telCount=0l;
		Long totVisits=0l;Long totalTransfers=0l;
		if(!StringUtils.isEmpty(cicActionsDto)){
			nppaCount = cicActionsDto.getNppaCount();
			mdCount = cicActionsDto.getMdCount();
			telCount = cicActionsDto.getTelCount();
			totVisits =  nppaCount + mdCount + telCount;
			totalTransfers = cicActionsDto.getTotalTransfers();
		}

		QIHospTrnsInfoDto telCICAction=new QIHospTrnsInfoDto();
		telCICAction.setName("Telephone only");
		telCICAction.setCout(telCount);
		telCICAction.setPercent(telCount == 0 ? 0 : Math.round((telCount.floatValue()/totVisits)*100));
		list.add(telCICAction);

		QIHospTrnsInfoDto npPaCICAction=new QIHospTrnsInfoDto();
		npPaCICAction.setName("NP or PA visit");
		npPaCICAction.setCout(nppaCount);
		npPaCICAction.setPercent(nppaCount == 0 ? 0 : Math.round((nppaCount.floatValue()/totVisits)*100));
		list.add(npPaCICAction);

		QIHospTrnsInfoDto mdCICAction=new QIHospTrnsInfoDto();
		mdCICAction.setName("MD visit");
		mdCICAction.setCout(mdCount);
		mdCICAction.setPercent(mdCount == 0 ? 0 : Math.round((mdCount.floatValue()/totVisits)*100));
		list.add(mdCICAction);
		response.setMedicalEvaluation(list);
		response.setTotalTransfers(totalTransfers);

		return response;

	}
	@RequestMapping(value="/hospitalTransferPercentage", method={RequestMethod.GET})
	public @ResponseBody List<QIHospitalTransferInfoDto> hospitalTransferPercentage(HttpServletRequest request){
		HttpSession session=request.getSession();
		System.out.println("i am in controller");
		Long facilityId=(Long) session.getAttribute("facilityId");
		String fromDate = null;
		String toDate = null;
		if(!StringUtils.isEmpty(request.getParameter("fromDate")) && !StringUtils.isEmpty(request.getParameter("toDate"))){
			fromDate = request.getParameter("fromDate");
			toDate = request.getParameter("toDate");
		}
		return qiGraphsService.getHospitalTranferPerntOfCurrentAndOtherFacilities(facilityId,fromDate,toDate);
		
	}

}