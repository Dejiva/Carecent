package com.codecoop.interact.web.controller;

import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.xml.bind.JAXBException;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.dao.CarePathDaoImpl;
import com.codecoop.interact.core.dao.PatientDaoImpl;
import com.codecoop.interact.core.dao.PatientEpisodeDao;
import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.dto.CarePathListingDto;
import com.codecoop.interact.core.dto.CarePathStepsResponseDto;
import com.codecoop.interact.core.dto.ViewAllCarePathsResponseDto;
import com.codecoop.interact.core.dto.carepath.CarePathDto;
import com.codecoop.interact.core.dto.carepath.StepDto;
import com.codecoop.interact.core.service.CarePathGeneratorService;
import com.codecoop.interact.core.service.CarePathTrackerService;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.core.service.SBARService;
import com.codecoop.interact.core.util.CarePathUtil;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.web.model.AdmissionForm;
import com.codecoop.interact.web.model.DashboardForm;
import com.codecoop.interact.web.model.SessionManagedLists;
import com.codecoop.interact.web.model.StaffRegistrationForm;
import com.codecoop.interact.web.model.StopAndWatchForm;
import com.codecoop.interact.web.prbean.CarePathListingPrBean;
import com.codecoop.interact.web.util.CarePathFormGenerator;

@RequestMapping("/carepath")
@Controller
public class CarePathController extends SessionManagedLists{

	private static final Logger LOG = Logger.getLogger(CarePathController.class);
	@Autowired
	private CarePathGeneratorService carePathGeneratorServic;
	@Autowired
	private CarePathTrackerService carePathTrackerService; 
	@Autowired
	PatientDaoImpl patientDao;
	@Autowired
	PatientEpisodeDao patientEpisodeDao;
	@Autowired 
	CarePathDaoImpl carePathDao;
	@Autowired
	PatientEpisodeService patientEpisodeService;
	@Autowired
	SBARService sbarService;
	
	
    @RequestMapping(value="/invoke", method={RequestMethod.GET})
	public @ResponseBody String carepathPage(HttpServletRequest request, ModelMap modelMap) {
		modelMap.addAttribute("dashboardForm", new DashboardForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
		modelMap.addAttribute("stopAndWatchForm", new StopAndWatchForm());
		modelMap.addAttribute("admissionForm", new AdmissionForm());
		modelMap.addAttribute("staffRegistrationForm", new StaffRegistrationForm());
          
		HttpSession session = request.getSession(true);
 		CarePathDto carePathDto = null;
		StepDto step = null;
		String stepId = request.getParameter("stepId");
		String message=null;
		String currCarePathCode=null;
		 Long staffId=(Long)session.getAttribute("staffId");
		 Long facilityId=(Long)session.getAttribute("facilityId");
		//Long patientEpisodeId=40L;
	//	Long patientId=patientEpisodeService.getPatient(patientEpisodeId).getId();
		Map<String, String> paramMap =null;
		if(stepId == null){
				currCarePathCode=request.getParameter("CARE_PATH_CODE");
				carePathDto = new CarePathDto();
				session.setAttribute("currCarePathCode", currCarePathCode);
				carePathDto.setCarePathCode(currCarePathCode);
				Long patientEpisodeId=Long.parseLong(request.getParameter("patientEpisodeId"));
				Long patientId=patientEpisodeService.findById(patientEpisodeId).getPatient().getId();
				carePathDto.setPatientId(patientId);
				carePathDto.setPatientEpisodeId(patientEpisodeId);
				session.setAttribute("carePathDto", carePathDto);
		}else{
			currCarePathCode=(String)session.getAttribute("currCarePathCode");
			carePathDto=(CarePathDto)session.getAttribute("carePathDto");
			paramMap = getRequestParameterMap(request);
     	}
		carePathDto=carePathTrackerService.invokeCarePath(staffId,facilityId,carePathDto, paramMap);
		session.setAttribute("carePathDto", carePathDto);
		session.setAttribute("carePathName",carePathTrackerService.getCarePathName(currCarePathCode));
		if(StringUtils.isNotBlank(carePathDto.getMessage())){
			return CarePathFormGenerator.generateCarePathMessage(carePathDto.getMessage());			
		}else{
			step = carePathGeneratorServic.getStep(carePathDto, carePathDto.getStepId());
			/*loading CIC carePathAtrr int the CarePathStep*/
	        
			paramMap=carePathTrackerService.getValueMapFromChangeInCondition(carePathDto.getPatientId(),facilityId);
    		CarePathUtil.populateAndEvaluateFormData(carePathDto.getStepOrder(),step, paramMap);
    		
			return CarePathFormGenerator.generateForm(step);
		}
	}
	
    @RequestMapping(value="/viewAllCarePaths", method={RequestMethod.GET})
   	public @ResponseBody List<ViewAllCarePathsResponseDto > viewAllCarePaths(@RequestParam(required = false) Long encounterId,HttpServletRequest request, ModelMap modelMap)throws JAXBException {
    	Long patientEpisodeId=Long.parseLong(request.getParameter("patientEpisodeId"));
   		HttpSession session = request.getSession(true);
   		Long staffId=(Long)session.getAttribute("staffId");
   		Long facilityId = (Long)session.getAttribute("facilityId");
    	List<ViewAllCarePathsResponseDto > viewCarePathsList =carePathTrackerService.getViewAllCarePath(staffId,facilityId,patientEpisodeId,encounterId);
    	
    	
    	      return viewCarePathsList;
       }
    @RequestMapping(value="/viewCarePath", method={RequestMethod.GET})
	public @ResponseBody List<CarePathStepsResponseDto > viewcarepathPage(HttpServletRequest request, ModelMap modelMap)throws  JAXBException {
    	String currCarePathCode=request.getParameter("CARE_PATH_CODE");
		Long patientEpisodeId=Long.parseLong(request.getParameter("patientEpisodeId"));
		HttpSession session = request.getSession(true);
		Long facilityId=(Long)session.getAttribute("facilityId");
		Long staffId=(Long)session.getAttribute("staffId");
		CarePathDto carePathDto = new CarePathDto();
		carePathDto.setCarePathCode(currCarePathCode);
		Long patientId=patientEpisodeService.findById(patientEpisodeId).getPatient().getId();
		carePathDto.setPatientId(patientId);
		carePathDto.setPatientEpisodeId(patientEpisodeId);
		Sbar sbar=sbarService.findByPatientEpisodeId(patientEpisodeId);
		List<CarePathStepsResponseDto> carePathStepList=carePathTrackerService.viewCarePath(staffId,facilityId,sbar.getId(),carePathDto);
        return carePathStepList;
    }
    private Map<String,String> getRequestParameterMap(HttpServletRequest request)
	{
		Map<String,String> requestMap=new HashMap<String,String>();
		@SuppressWarnings("unchecked")
		Enumeration<String> keyset=request.getParameterNames();
		while(keyset.hasMoreElements())	{
			String attr0=(String)keyset.nextElement();
			String attr1[]=request.getParameterValues(attr0);
			requestMap.put(attr0, attr1[0]);
		}
		return requestMap;
	}
	@RequestMapping(value="/getPreviousStep", method={RequestMethod.GET})
	public @ResponseBody String getPreviousStep(HttpServletRequest request, ModelMap modelMap) {
	   // List<PatientCarePathT>
		HttpSession session = request.getSession(true);
		StepDto step=null;
		Long facilityId=(Long)session.getAttribute("facilityId");
		CarePathDto carePathDto=(CarePathDto)session.getAttribute("carePathDto");
		if(Constants.CAREPATH_NOTIFY_DOCTOR.equals(carePathDto.getMessage())){
			carePathDto.setMessage(null);
		}else{
			carePathDto=carePathTrackerService.getPreviousCarePathDto(carePathDto,facilityId);	
		}
		session.setAttribute("carePathDto", carePathDto);
		step = carePathGeneratorServic.getStep(carePathDto, carePathDto.getStepId());
		return CarePathFormGenerator.generateForm(step);
	}
	
	@RequestMapping(value="/getCarePathName", method={RequestMethod.GET})
	public @ResponseBody String getCarePathName(HttpServletRequest request, ModelMap modelMap) {
		String carePathCode=(String)request.getParameter("CARE_PATH_CODE");
		return carePathTrackerService.getCarePathName(carePathCode).trim();
	}
	
	@RequestMapping(value="/getCarePathNamesWithStatus/{patientEpisodeId}", method={RequestMethod.GET})
	public @ResponseBody List<CarePathListingPrBean> getCarePathsToDisplay(@PathVariable Long patientEpisodeId) {
		List<CarePathListingDto> refCarePathMap  = 
			carePathTrackerService.getRefCarePathBasedOnCarePathAttr(patientEpisodeId);
		
		List<CarePathListingPrBean> prBeanList = new ArrayList<CarePathListingPrBean>();
		for(CarePathListingDto dto : refCarePathMap) {
			CarePathListingPrBean prBean = new CarePathListingPrBean();
			prBean.setCarePathCode(dto.getCarePathCode());
			prBean.setCarePathName(dto.getCarePathName());
			prBean.setStatus(dto.getStatus());
			List<String> suggestions = new ArrayList<String>();
			suggestions.addAll(dto.getSuggestions());
			prBean.setSuggestedBy(suggestions);
			prBeanList.add(prBean);
		}
		
		return prBeanList;
	}
	
	@RequestMapping(value="/getRunningCarePath/{patientEpisodeId}",method={RequestMethod.GET})
	public @ResponseBody List<CarePath> getRunningCarePaths(@PathVariable Long patientEpisodeId){
	
		
		return carePathTrackerService.getRunningCarePaths(patientEpisodeId);

}
}

 