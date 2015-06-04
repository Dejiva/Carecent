package com.codecoop.interact.web.controller;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

import com.codecoop.interact.core.dao.SbarDaoImpl;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.PrescribeMedicine;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.SbarNotes;
import com.codecoop.interact.core.dto.CarePathLabworksDto;
import com.codecoop.interact.core.dto.CarePathLabworksValuesFrmCICDto;
import com.codecoop.interact.core.dto.ChangeInConditionDto;
import com.codecoop.interact.core.dto.EvaluationMessageDto;
import com.codecoop.interact.core.dto.PatientEncounterDto;
import com.codecoop.interact.core.dto.PdfResponseDto;
import com.codecoop.interact.core.dto.PrescribeMedicineDto;
import com.codecoop.interact.core.dto.SbarDto;
import com.codecoop.interact.core.dto.SbarNotesDto;
import com.codecoop.interact.core.dto.SbarNotesResponseDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkAttrByGroupDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkAttrDto;
import com.codecoop.interact.core.dto.SignsSymptomsLabworkDto;
import com.codecoop.interact.core.dto.SystemNotesDto;
import com.codecoop.interact.core.dto.sbar.SbarReleventSymptomsDto;
import com.codecoop.interact.core.enumpack.ChangeInConditionType;
import com.codecoop.interact.core.model.LoggedUserModel;
import com.codecoop.interact.core.service.CarePathLabworksService;
import com.codecoop.interact.core.service.CarePathTrackerService;
import com.codecoop.interact.core.service.ChangeInConditionService;
import com.codecoop.interact.core.service.FacilityStaffService;
import com.codecoop.interact.core.service.PatientEpisodeService;
import com.codecoop.interact.core.service.SBARService;
import com.codecoop.interact.core.service.SignsSymptomsLabworkService;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.core.util.PatientUtils;
import com.codecoop.interact.web.model.CarePathLabworksResponse;
import com.codecoop.interact.web.model.CarePathLabworksValuesFrmCICResponse;
import com.codecoop.interact.web.model.LabworksResponse;
import com.codecoop.interact.web.model.SbarForm;
import com.codecoop.interact.web.model.SessionManagedLists;
import com.codecoop.interact.web.prbean.Attributes;
import com.codecoop.interact.web.prbean.ChangeInConditionPrBean;
import com.codecoop.interact.web.util.DateUtil;

/**
 * @author Ramesh
 *
 */
@RequestMapping("/sbar")
@Controller
public class SBARController extends SessionManagedLists {
	private static final Logger LOG = Logger.getLogger(SBARController.class);

	@Autowired
	SignsSymptomsLabworkService signsSymptomsLabworkService;

	@Autowired
	ChangeInConditionService changeInConditionService;
	@Autowired
	SBARService sbarService;
	@Autowired
	CarePathTrackerService carePathTrackerService;
	@Autowired
	PatientEpisodeService patientEpisodeService;

	@Autowired
	FacilityStaffService facilityStaffService;
	
	@Autowired
	CarePathLabworksService carePathLabworksService;


	@RequestMapping(value = "/createIntialSbar", method = { RequestMethod.GET })
	public @ResponseBody void createIntialSbar(HttpServletRequest request) throws Exception {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		// sbarService.
		PatientEpisode patientEpisode = patientEpisodeService
				.findById(patientEposodeId);
		sbarService.sbarsaveIntialSbar(patientEpisode,request.getUserPrincipal().getName());

	}

	@RequestMapping(value = "/sbarEvaluate", method = { RequestMethod.GET })
	public @ResponseBody EvaluationMessageDto evaluate(
			HttpServletRequest request) {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		HttpSession session = request.getSession(true);
		Long staffId = (Long) session.getAttribute("staffId");
		Long facilityId = (Long) session.getAttribute("facilityId");

		carePathTrackerService.invikeAllCarePaths(staffId, facilityId,
				patientEposodeId);
		EvaluationMessageDto evaluationMessages = sbarService
				.getEvaluationResult(patientEposodeId);
		// List<SbarNotesResponseDto> sbarNotesResponseList=new
		// ArrayList<SbarNotesResponseDto>();

		return evaluationMessages;
	}

	@RequestMapping(value = "/submit", method = { RequestMethod.GET })
	public  @ResponseBody String sbarPage(HttpServletRequest request,
			@ModelAttribute("sbarForm") SbarForm sbarForm) throws Exception {
		HttpSession session = request.getSession(true);
		Long staffId = (Long) session.getAttribute("staffId");
		Long facilityId = (Long) session.getAttribute("facilityId");
		System.out.println("docter......." + sbarForm.getRepotedToDoctor());
		SbarDto sbarDto = new SbarDto(sbarForm.getSbarPatientEpisodeId(), null,
				null,sbarForm.getPrimaryDiagnoses(),
				sbarForm.getOtherPertinentHistory(),
				sbarForm.getMedAlertChangeLastWeek(), sbarForm.getAllergies(),
				sbarForm.getMedAlertInWarfarin(),
				sbarForm.getResultOfLastInr(), sbarForm.getLatestInrDate(),
				sbarForm.getOnO2Flag(), sbarForm.getO2LitersPerMinute(),
				sbarForm.getAdvCarePlanDNRFlag(),
				sbarForm.getAdvCarePlanDNIFlag(),
				sbarForm.getAdvCarePlanDNHflag(),
				sbarForm.getAdvCarePlanNoEtrnalFeedFlag(),
				sbarForm.getOtherNewOrders(), sbarForm.getOtherPreferences(),
				sbarForm.getRnComment(), sbarForm.getLpnComment(),
				sbarForm.getMonitorVitalSignsFlag(), sbarForm.getLabWorkFlag(),
				sbarForm.getxRayFlag(), sbarForm.getEkg(),
				sbarForm.getProviderVisitFlag(),
				sbarForm.getTransferToHospitalFlag(),
				sbarForm.getOtherNewOrdersFlag(),
				sbarForm.getOtherOrderOrWill(),
				sbarForm.getOximetryOnRoomair(), sbarForm.getOximetryOnO2(),
				sbarForm.getOtherResidentCare(), sbarForm.getRepotedToFamily(),
				sbarForm.getManageInFacilityFlag());
		sbarDto.setGaurdianName(sbarForm.getGaurdianName());
		sbarDto.setMedicationAlertsDate(sbarForm.getMedicationAlertsDate());
		sbarDto.setRepotedToDate(sbarForm.getRepotedToDate());
		sbarDto.setSbarSymtomList(sbarForm.getSbarSymtomList());
		if (sbarForm.getRepotedToDoctor() != null
				&& sbarForm.getRepotedToDoctor() != "") {
			sbarDto.setDoctorId(Long.parseLong(sbarForm.getRepotedToDoctor()));
			System.out.println("Sbar sumiting........+"
					+ sbarForm.getSbarPatientEpisodeId());
		}

		sbarService.saveSbarData(facilityId, staffId, sbarDto);

		return "SUCESS";
	}

	@RequestMapping(value = "/getSymptomsChangeInConditionsByPatientEpisodeId", method = RequestMethod.GET)
	public @ResponseBody List<ChangeInConditionPrBean> getSymptomsCIC(
			@RequestParam Long patientEpisodeId,
			@RequestParam(required = false) Long symptomId,
			@RequestParam(required = false) Long encounterId,
			HttpServletRequest request) {

		List<ChangeInConditionDto> cicList = new ArrayList<ChangeInConditionDto>();
		if(encounterId!=null){
			
			cicList = changeInConditionService.getChangeInCondByEncounterAndType(encounterId,ChangeInConditionType.SYMPTOM_FLAG);
		}else{
		if (symptomId != null && symptomId != 0) {
			cicList = changeInConditionService
					.getChangeInCondByEpisodeIdAndSymptomId(patientEpisodeId,
							symptomId);
		} else {
			cicList = changeInConditionService
					.getChangeInCondByEpisodeIdAndType(patientEpisodeId,
							ChangeInConditionType.SYMPTOM_FLAG);
		}
		}
		List<ChangeInConditionPrBean> prBeanList = prepareChangeInConditionPrBeanList(cicList);

		return prBeanList;
	}

	@RequestMapping(value = "/pupulateSystemNotes", method = RequestMethod.GET)
	public @ResponseBody List<SystemNotesDto> pupulateSystemNotes(
			HttpServletRequest request) throws Exception {
		List<SystemNotesDto> systemNotes = new ArrayList<SystemNotesDto>();
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		systemNotes = sbarService.getSbarSystemNotes(patientEposodeId);
		return systemNotes;
	}

	@RequestMapping(value = "/getLabworkChangeInConditionsByPatientEpisodeId", method = RequestMethod.GET)
	public @ResponseBody List<ChangeInConditionPrBean> getLabworkCIC(
			@RequestParam Long patientEpisodeId,
			@RequestParam(required = false) Long symptomId,
			@RequestParam(required = false) Long encounterId,
			HttpServletRequest request) {

		List<ChangeInConditionDto> cicList = new ArrayList<ChangeInConditionDto>();
		 if(encounterId!=null){
				cicList = changeInConditionService.getChangeInCondByEncounterAndType(encounterId,ChangeInConditionType.LABWORK_FLAG);
		}else{
		if (symptomId != null && symptomId != 0) {
			cicList = changeInConditionService
					.getChangeInCondByEpisodeIdAndSymptomId(patientEpisodeId,
							symptomId);
		} else {
			cicList = changeInConditionService
					.getChangeInCondByEpisodeIdAndType(patientEpisodeId,
							ChangeInConditionType.LABWORK_FLAG);
		}
		}
		List<ChangeInConditionPrBean> prBeanList = prepareChangeInConditionPrBeanList(cicList);

		return prBeanList;
	}

	@RequestMapping(value = "/getVitalSignsChangeInConditionsByPatientEpisodeId", method = RequestMethod.GET)
	public @ResponseBody List<ChangeInConditionPrBean> getVitalSignsCIC(
			@RequestParam Long patientEpisodeId,
			@RequestParam(required = false) Long symptomId,
			@RequestParam(required = false) Long encounterId,
			HttpServletRequest request) {

		List<ChangeInConditionDto> cicList = new ArrayList<ChangeInConditionDto>();
        if(encounterId!=null){
			
			cicList = changeInConditionService.getChangeInCondByEncounterAndType(encounterId,ChangeInConditionType.SIGN_FLAG);
		}else{
		if (symptomId != null && symptomId != 0) {
			cicList = changeInConditionService
					.getChangeInCondByEpisodeIdAndSymptomId(patientEpisodeId,
							symptomId);
		} else {
			cicList = changeInConditionService
					.getChangeInCondByEpisodeIdAndType(patientEpisodeId,
							ChangeInConditionType.SIGN_FLAG);
		}
		}

		List<ChangeInConditionPrBean> prBeanList = prepareChangeInConditionPrBeanList(cicList);

		return prBeanList;
	}

	/**
	 * @param cicList
	 * @return
	 */
	private List<ChangeInConditionPrBean> prepareChangeInConditionPrBeanList(
			List<ChangeInConditionDto> cicList) {
		List<ChangeInConditionPrBean> prBeanList = new ArrayList<ChangeInConditionPrBean>();
		Map<Long, ChangeInConditionPrBean> cicPrBeanMap = new HashMap<Long, ChangeInConditionPrBean>();
		if (!CollectionUtils.isEmpty(cicList)) {
			Map<Long, Attributes> attrMap = new HashMap<Long, Attributes>();
			for (ChangeInConditionDto dto : cicList) {
				Long attrId = dto.getSignsSymptomsLabworkAttrId();

				SignsSymptomsLabworkAttrDto attrDto = getSignSymptomLabworkAttributesByAttrId(attrId);

				Long parentId = attrDto.getParentId();
				SignsSymptomsLabworkDto ssl = signsSymptomsLabworkService
						.getSignSymptomsLabWorkById(parentId);
				List<SignsSymptomsLabworkAttrDto> attrDtoList = getSignSymptomLabworkAttributesByParentId(parentId,null);
				ChangeInConditionPrBean prBean = cicPrBeanMap.get(ssl.getId()) != null ? cicPrBeanMap
						.get(ssl.getId()) : new ChangeInConditionPrBean();
						List<Attributes> attrList = prBean.getAttributes() != null ? prBean
								.getAttributes() : new ArrayList<Attributes>();

								for (SignsSymptomsLabworkAttrDto attrDto1 : attrDtoList) {

									Attributes attr = attrMap.get(attrDto1.getId()) != null ? attrMap
											.get(attrDto1.getId()) : new Attributes();
											attr.setAttributeId(attrDto1.getId());
											attr.setAttributeName(attrDto1.getAttrName());
											attr.setUnits(attrDto1.getUnits());

											if (attrId.compareTo(attrDto1.getId()) == 0) {
												attr.setAttributeValue(dto.getChangeInConditionValue());
												attr.setDoctorNotification(dto.getDocterNotification());
											}

											attr.setDataType(attrDto1.getDatatype());
											if (attrMap.get(attrDto1.getId()) == null) {
												attrMap.put(attrDto1.getId(), attr);
												attrList.add(attr);
											}

								}

								if (cicPrBeanMap.get(ssl.getId()) == null) {
									cicPrBeanMap.put(ssl.getId(), prBean);
									prBean.setSymptomName(ssl.getSignsSymptomsLabworkName());
									prBean.setSymptomId(ssl.getId());
									prBean.setComments(dto.getComments());
									prBean.setAttributes(attrList);
									prBean.setOccurredBefore(dto.getOccurredBefore());
									prBean.setStartedOnDate(DateUtil.parseStringDate(
											dto.getSymptomOccuredDate(),
											"MM/dd/yyyy hh:mm:ss a"));
									prBean.setSymptomStatus(dto.getSymptomStatus());
									prBean.setThingsMadeSymptomWorse(dto
											.getThingsMadeSymptomWorse());
									prBean.setThinsMadeSymptomBetter(dto
											.getThinsMadeSymptomBetter());
									prBean.setTreatmentForLastEpisode(dto
											.getTreatmentForLastEpisode());
									prBean.setDateCreated(dto.getDateCreated());
								}

			}

			Collection<ChangeInConditionPrBean> cicCollections = cicPrBeanMap
					.values();
			for (ChangeInConditionPrBean prBean : cicCollections) {
				prBeanList.add(prBean);
			}
		}

		Collections.sort(prBeanList, new Comparator<ChangeInConditionPrBean>() {
			@Override
			public int compare(ChangeInConditionPrBean arg0,
					ChangeInConditionPrBean arg1) {
				return arg0.getDateCreated().compareTo(arg1.getDateCreated());
			}
		});
		Collections.reverse(prBeanList);
		return prBeanList;
	}

	@RequestMapping(value = "/removeChangeInConditionRecord", method = RequestMethod.GET)
	public @ResponseBody String deleteChangeInConditionRecord(
			@RequestParam Long patientEpisodeId, @RequestParam Long symptomId,
			HttpServletRequest request) {
		changeInConditionService
		.deleteChangeInConditionRecordByEpisodeAndSymptomId(
				patientEpisodeId, symptomId);
		return "SUCCESS";
	}

	@RequestMapping(value = "/saveChangeInConditionsSymptoms", method = RequestMethod.POST)
	public @ResponseBody String saveChangeInConditionSymptoms(
			HttpServletRequest request, HttpServletResponse response) throws Exception {
		Long patientEpisodeId = null;
		try {
			patientEpisodeId = Long.valueOf(request
					.getParameter("patientEpisodeId"));
		} catch (NumberFormatException e) {
		}

		if (patientEpisodeId == null || patientEpisodeId == 0) {
			return "Incorrect Patient Episode Id";
		}
		Long symptomId = null;
		try {
			symptomId = Long.valueOf(request.getParameter("symptom"));
		} catch (NumberFormatException nfe) {
		}

		if (symptomId == null || symptomId == 0) {
			return "No Symptom selected";
		}
		String date = request.getParameter("symptomsDate");
		String status = request.getParameter("symptomsStatus");
		String appearedBefore = request.getParameter("symptomAppearedBefore");
		String thingsMadeSymptomWorse = request
				.getParameter("thingsMadeSymptomWorse");
		String thinsMadeSymptomBetter = request
				.getParameter("thinsMadeSymptomBetter");
		String treatmentForLastEpisode = request
				.getParameter("treatmentForLastEpisode");
		String comments = request.getParameter("comments");
		String userName = request.getParameter("userName");

		LOG.debug(" =============================================== ");
		LOG.debug(" Symptom Id " + symptomId);
		LOG.debug(" Symptom Date " + date);
		LOG.debug(" Symptom Status " + status);
		LOG.debug(" Symptom Appeared Before " + appearedBefore);
		LOG.debug(" Things made Symptoms Worse " + thingsMadeSymptomWorse);
		LOG.debug(" Things made Symptoms Better " + thinsMadeSymptomBetter);
		LOG.debug(" Treatment for Last Episode " + treatmentForLastEpisode);
		LOG.debug(" Other relevant information " + comments);
		LOG.debug(" User Name " + userName);

		List<ChangeInConditionDto> changeInConditionList = new ArrayList<ChangeInConditionDto>();
		List<SignsSymptomsLabworkAttrDto> valuesList = getSignSymptomLabworkAttributesByParentId(symptomId,patientEpisodeId);
		for (SignsSymptomsLabworkAttrDto attr : valuesList) {
			String value = request.getParameter("symptomValues" + attr.getId());
			LOG.debug("" + attr.getId() + " : " + value);
			if (!StringUtils.isEmpty(value)) {
				ChangeInConditionDto symptomsDto = new ChangeInConditionDto();
				symptomsDto.setSignsSymptomsLabworkAttrId(attr.getId());
				if ("BOOLEAN".equalsIgnoreCase(attr.getDatatype())) {
					symptomsDto.setChangeInConditionValue("true");
				} else {
					symptomsDto.setChangeInConditionValue(value);
				}
				symptomsDto.setPatientEpisodeId(patientEpisodeId);
				symptomsDto.setSymptomStatus(status);
				if (!StringUtils.isEmpty(date)) {
					symptomsDto.setSymptomOccuredDate(DateUtil.parseDate(date,
							"MM/DD/yyyy hh:mm:ss a"));
				}
				symptomsDto.setThingsMadeSymptomWorse(thingsMadeSymptomWorse);
				symptomsDto.setThinsMadeSymptomBetter(thinsMadeSymptomBetter);
				symptomsDto.setTreatmentForLastEpisode(treatmentForLastEpisode);
				symptomsDto.setComments(comments);
				symptomsDto.setOccurredBefore("YES"
						.equalsIgnoreCase(appearedBefore));

				changeInConditionList.add(symptomsDto);
			}

		}
        if(changeInConditionList!=null&&changeInConditionList.size()>0){
		changeInConditionService.saveOrUpdateChangeInConditions(changeInConditionList, symptomId, userName);
        }else
        {
        	deleteChangeInConditionRecord(patientEpisodeId, symptomId, request);
        }
     
		return "SUCCESS";
	}

	@RequestMapping(value = "/saveChangeInConditionVitalSignData", method = RequestMethod.POST)
	public @ResponseBody String saveChangeInConditionVitalSignData(
			HttpServletRequest request, HttpServletResponse response) {
		Long patientEpisodeId = null;
		try {
			patientEpisodeId = Long.valueOf(request
					.getParameter("patientEpisodeId"));
		} catch (NumberFormatException e) {
		}

		if (patientEpisodeId == null || patientEpisodeId == 0) {
			return "Incorrect Patient Episode Id";
		}

		String date = request.getParameter("symptomsDate");
		String status = request.getParameter("symptomsStatus");
		String appearedBefore = request.getParameter("symptomAppearedBefore");
		String thingsMadeSymptomWorse = request
				.getParameter("thingsMadeSymptomWorse");
		String thinsMadeSymptomBetter = request
				.getParameter("thinsMadeSymptomBetter");
		String treatmentForLastEpisode = request
				.getParameter("treatmentForLastEpisode");
		String comments = request.getParameter("comments");
		String userName = request.getParameter("userName");

		List<ChangeInConditionDto> changeInConditionList = new ArrayList<ChangeInConditionDto>();
		// List<ChangeInConditionDto> unSelectedCchangeInConditionList = new
		// ArrayList<ChangeInConditionDto>();
		List<SignsSymptomsLabworkAttrDto> valuesList = sbarService
				.getChangeInConditionAttrOnPatient(Constants.SIGN_FLAG ,patientEpisodeId);
		for (SignsSymptomsLabworkAttrDto attr : valuesList) {
			String value = request.getParameter("symptomValues" + attr.getId());

			if (!StringUtils.isEmpty(value)) {
				ChangeInConditionDto symptomsDto = new ChangeInConditionDto();
				symptomsDto.setSignsSymptomsLabworkAttrId(attr.getId());
				if ("BOOLEAN".equalsIgnoreCase(attr.getDatatype())) {
					symptomsDto.setChangeInConditionValue("true");
				} else {
					symptomsDto.setChangeInConditionValue(value);
				}
				symptomsDto.setPatientEpisodeId(patientEpisodeId);
				symptomsDto.setSymptomStatus(status);
				if (!StringUtils.isEmpty(date)) {
					symptomsDto.setSymptomOccuredDate(DateUtil.parseDate(date,
							"MM/DD/yyyy hh:mm:ss a"));
				}
				symptomsDto.setThingsMadeSymptomWorse(thingsMadeSymptomWorse);
				symptomsDto.setThinsMadeSymptomBetter(thinsMadeSymptomBetter);
				symptomsDto.setTreatmentForLastEpisode(treatmentForLastEpisode);
				symptomsDto.setComments(comments);
				symptomsDto.setOccurredBefore("YES"
						.equalsIgnoreCase(appearedBefore));

				changeInConditionList.add(symptomsDto);
			} else {
				changeInConditionService
				.deleteVitalSignRecordByEpisodeAndSymptomId(
						patientEpisodeId, attr.getId());
			}
		}
		changeInConditionService.saveOrUpdateChangeInConditionsData(
				changeInConditionList, userName);
		return "SUCCESS";
	}

	@RequestMapping(value = "/getChangeInConditionSymptoms", method = { RequestMethod.GET })
	public @ResponseBody List<SignsSymptomsLabworkDto> getChangeInConditionSymptoms(
			HttpServletRequest request) {
		return signsSymptomsLabworkService
				.getSignsSymptomsLabWorkByFlagType(ChangeInConditionType.SYMPTOM_FLAG);
	}

	@RequestMapping(value = "/getChangeInConditionSymptomValues", method = { RequestMethod.GET })
	public @ResponseBody List<SignsSymptomsLabworkAttrDto> getSignSymptomLabworkAttributes(
			@RequestParam Long id,HttpServletRequest request) {
		Long patientEpisodeId = (StringUtils.isEmpty(request.getParameter("patientEpisodeId"))) ?null:Long.valueOf(request.getParameter("patientEpisodeId"));
		return getSignSymptomLabworkAttributesByParentId(id,patientEpisodeId);
	}

	@RequestMapping(value = "/getChangeInConditionLabwork", method = { RequestMethod.GET })
	public @ResponseBody List<SignsSymptomsLabworkDto> getChangeInConditionLabwork(
			HttpServletRequest request) {
		return signsSymptomsLabworkService
				.getSignsSymptomsLabWorkByFlagType(ChangeInConditionType.LABWORK_FLAG);
	}

	@RequestMapping(value = "/getChangeInConditionVitalSigns", method = { RequestMethod.GET })
	public @ResponseBody List<SignsSymptomsLabworkDto> getChangeInConditionVitalSigns(
			HttpServletRequest request) {
		return signsSymptomsLabworkService
				.getSignsSymptomsLabWorkByFlagType(ChangeInConditionType.SIGN_FLAG);
	}

	private List<SignsSymptomsLabworkAttrDto> getSignSymptomLabworkAttributesByParentId(
			Long id,Long patientEpisodeId) {
		return signsSymptomsLabworkService
				.getSignSymptomsLabWorkAttributesByParentId(id,patientEpisodeId);
	}

	private SignsSymptomsLabworkAttrDto getSignSymptomLabworkAttributesByAttrId(
			Long id) {
		return signsSymptomsLabworkService
				.getSignSymptomsLabWorkAttributesById(id);
	}

	@RequestMapping(value = "/getActivePatientEpisodeId", method = { RequestMethod.GET })
	public @ResponseBody String getActivePatientEpisodeId(
			HttpServletRequest request) {
		Long patientId = Long.parseLong(request.getParameter("patientId"));
		HttpSession session = request.getSession(true);
		Long facilityId = (Long) session.getAttribute("facilityId");
		PatientEpisode patientEpisode = patientEpisodeService.findActicePatientEpisode(patientId,facilityId);
		String patientName = PatientUtils.getPatientFullName(patientEpisode
				.getPatient().getPatientFirstName(), patientEpisode
				.getPatient().getPatientLastName());
		return patientEpisode.getId().toString() + "=" + patientName;
	}

	@RequestMapping(value = "/populateIntialSbarData", method = { RequestMethod.GET })
	public @ResponseBody SbarDto populateIntialSbarData(
			HttpServletRequest request) {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		return sbarService.populateSbarData(patientEposodeId, false);
	}

	@RequestMapping(value = "/populateSbarData", method = { RequestMethod.GET })
	public @ResponseBody SbarDto populateSbarData(HttpServletRequest request) {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		Boolean isFromEdit = Boolean.parseBoolean(request
				.getParameter("isFormEdit"));
		return sbarService.populateSbarData(patientEposodeId, isFromEdit);
	}

	@RequestMapping(value = "/getActiveSbar", method = { RequestMethod.GET })
	public @ResponseBody String getActiveSbar(HttpServletRequest request) {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		if (sbarService.findByPatientEpisodeId(patientEposodeId) != null) {
			return "SBAR_PRASENT";
		} else {
			return "NO_SBAR";
		}

	}

	@RequestMapping(value = "/saveNotes", method = { RequestMethod.GET })
	public @ResponseBody SbarNotesDto saveNurseNotes(HttpServletRequest request)
			throws Exception {
		SbarNotesDto sbarNotesDto = new SbarNotesDto();
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		if (sbarService.findByPatientEpisodeId(patientEposodeId) != null) {
			String nurseNotes = request.getParameter("notes");
			HttpSession session = request.getSession(true);
			Long staffId = (Long) session.getAttribute("staffId");
			Long facilityId = (Long) session.getAttribute("facilityId");

			sbarNotesDto.setNotes(nurseNotes);
			sbarNotesDto.setPatientEpisodeId(patientEposodeId);
			sbarNotesDto.setNursingStaffId(staffId);
			sbarNotesDto.setFacilityId(facilityId);
			sbarService.saveSbarNotes(sbarNotesDto);
		}
		return sbarNotesDto;
	}

	@RequestMapping(value = "/getSbarNotesByPatientEpisodeId", method = { RequestMethod.GET })
	public @ResponseBody List<SbarNotesResponseDto> getNurseNotesByPatientEpisodeId(
			HttpServletRequest request) throws Exception {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		String nursingStaffType = request.getParameter("nursingStaffType");
		Sbar sbar = sbarService.findByPatientEpisodeId(patientEposodeId);
		List<SbarNotesResponseDto> sbarNotesResponseList = new ArrayList<SbarNotesResponseDto>();
		if (sbar != null) {
			sbarNotesResponseList = sbarService.getSbarNotes(sbar,
					nursingStaffType);

		}
		return sbarNotesResponseList;
	}

	@RequestMapping(value = "/deleteSbarNotes", method = { RequestMethod.GET })
	public @ResponseBody void deleteSbarNotes(HttpServletRequest request)
			throws Exception {
		Long notesId = Long.parseLong(request.getParameter("notesId"));
		sbarService.deleteSbarNotes(notesId);
	}

	@RequestMapping(value = "/getSbarNotesByNotesId", method = { RequestMethod.GET })
	public @ResponseBody String getSbarNotesByNotesId(HttpServletRequest request)
			throws Exception {
		Long notesId = Long.parseLong(request.getParameter("notesId"));
		SbarNotes sbarNotes = sbarService.findById(notesId);
		return sbarNotes.getNotes();
	}

	@RequestMapping(value = "/editSbarNotesByNotesId", method = { RequestMethod.GET })
	public @ResponseBody String editSbarNotesByNotesId(
			HttpServletRequest request) throws Exception {
		Long notesId = Long.parseLong(request.getParameter("notesId"));
		String notes = request.getParameter("notes");
		HttpSession session = request.getSession(true);
		String staffName = (String) session.getAttribute("staffName");
		sbarService.editSbarNotes(notesId, notes, staffName);
		return "ok";
	}

	@RequestMapping(value = "/getSbarReleventSymtoms", method = { RequestMethod.GET })
	public @ResponseBody SbarReleventSymptomsDto getSbarReleventSymptoms(
			HttpServletRequest request) throws Exception {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		return sbarService.genrateSbarReleventSymptoms(patientEposodeId);

	}

	@RequestMapping(value = "/getStopAndWacthSymtoms", method = { RequestMethod.GET })
	public @ResponseBody List<String> getStopAndWacthSymtoms(
			HttpServletRequest request) throws Exception {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		return sbarService.getStopAndWatchSymtoms(patientEposodeId);

	}

	@RequestMapping(value = "/changeSbarPatientStatus", method = { RequestMethod.GET })
	public @ResponseBody EvaluationMessageDto changeSbarPatientStatus(
			HttpServletRequest request) throws Exception {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		EvaluationMessageDto evaluationMessages = sbarService
				.getEvaluationResult(patientEposodeId);
		return evaluationMessages;
	}

	@RequestMapping(value = "/getChangeInConditionVitalSignsAttrOnPatient", method = { RequestMethod.GET })
	public @ResponseBody List<SignsSymptomsLabworkAttrDto> getChangeInConditionVitalSignsAttrOnPatient(
			HttpServletRequest request) throws Exception {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		List<SignsSymptomsLabworkAttrDto> signsSymptomsLabworkAttrDto = sbarService
				.getChangeInConditionAttrOnPatient(Constants.SIGN_FLAG,patientEposodeId);
		return signsSymptomsLabworkAttrDto;

	}

	@RequestMapping(value = "/getChangeInConditionLabWorkAttrOnPatient", method = { RequestMethod.GET })
	public @ResponseBody List<SignsSymptomsLabworkAttrByGroupDto> getChangeInConditionLabWorkAttrOnPatient(
			HttpServletRequest request) throws Exception {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		return signsSymptomsLabworkService
				.getSignsSymptomsLabWorkByFlagTypeOnPatient(patientEposodeId,
						ChangeInConditionType.LABWORK_FLAG);

	}

	@RequestMapping(value = "/getpdfPatientInfo", method = { RequestMethod.GET })
	public @ResponseBody PdfResponseDto getpdfPatientInfo(
			HttpServletRequest request) throws Exception {
		Long patientId = Long.parseLong(request.getParameter("patientId"));
		HttpSession session = request.getSession(true);

		Long facilityId = (Long) session.getAttribute("facilityId");

		PdfResponseDto patientDetails = sbarService.getPdfPatientInfo(
				patientId, facilityId);
		return patientDetails;
	}

	@RequestMapping(value = "/getMedicine", method = { RequestMethod.GET })
	public @ResponseBody List<PrescribeMedicineDto> getMedicine(
			HttpServletRequest request) throws Exception {
		Long patientEposodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		return sbarService.getMedicine(patientEposodeId);

	}

	@RequestMapping(value = "/saveRepotedDoctor", method = { RequestMethod.GET })
	public @ResponseBody String saveRepotedDoctorInSbar(
			HttpServletRequest request) {
		Long patientEpisodeId = Long.parseLong(request
				.getParameter("patientEpisodeId"));
		HttpSession session = request.getSession(true);
		Long staffId = (Long) session.getAttribute("staffId");
		Long facilityId = (Long) session.getAttribute("facilityId");
		Long fDoctorId = Long.parseLong(request.getParameter("doctorId"));
		String notes = (String) request.getParameter("notes");
		try {

			sbarService.saveRepotedDoctorInSbar(patientEpisodeId, fDoctorId,notes,staffId);
			
			return "SUCESS";
		} catch (Exception e) {

			return "FAILED";
		}

	}
	@RequestMapping(value="/saveMedicine", method={RequestMethod.GET})
	public @ResponseBody  PrescribeMedicineDto saveMedicine(HttpServletRequest request)throws Exception {
		PrescribeMedicineDto prescribeMedicineDto=new PrescribeMedicineDto();
		Long patientEposodeId=Long.parseLong(request.getParameter("patientEpisodeId"));
		String medicineName=request.getParameter("medicineName");

		if (sbarService.findByPatientEpisodeId(patientEposodeId) != null) {
			HttpSession session = request.getSession(true);
			Long staffId = (Long) session.getAttribute("staffId");
			Long facilityId = (Long) session.getAttribute("facilityId");
			prescribeMedicineDto.setPatientEpisodeId(patientEposodeId);
			prescribeMedicineDto.setMedicineName(medicineName);
			prescribeMedicineDto.setStaffId(staffId);
			sbarService.saveprescribeMedicine(prescribeMedicineDto);
		}
		return prescribeMedicineDto;
	}

	@RequestMapping(value = "/updateMedicine", method = { RequestMethod.GET })
	public @ResponseBody PrescribeMedicine updateMedicine(
			HttpServletRequest request) {
		Long prescribeMedicineId = Long.parseLong(request
				.getParameter("prescribeMedicineId"));
		String medicineName = request.getParameter("medicineName");

		return sbarService.updateMedicine(prescribeMedicineId, medicineName);
	}

	@RequestMapping(value = "/removeMedicine", method = { RequestMethod.GET })
	public @ResponseBody Long removeMedicine(HttpServletRequest request) {
		Long prescribeMedicineId = Long.parseLong(request
				.getParameter("prescribeMedicineId"));
		return sbarService.removeMedicine(prescribeMedicineId);
	}

	@RequestMapping(value = "/moveToAdmisionFromCA", method = { RequestMethod.GET })
	public @ResponseBody String moveToAdmisionFromCA(
			HttpServletRequest request) {
		Long patientEpisodeId = Long.parseLong(request.getParameter("patientEpisodeId"));
		HttpSession session = request.getSession(true);
		LoggedUserModel user = (LoggedUserModel)session.getAttribute("loggedUser");
		try {
			sbarService.moveToAdmisionFromCA(patientEpisodeId, user);
			return "SUCESS";
		} catch (Exception e) {

			return "FAILED";
		}

	}

	@RequestMapping(value = "/moveToAcuteCare", method = { RequestMethod.GET })
	public @ResponseBody String moveToAcuteCare(HttpServletRequest request) {
		Long patientEpisodeId = Long.parseLong(request.getParameter("patientEpisodeId"));
		String plannedDetails=request.getParameter("plannedDetails");
		HttpSession session = request.getSession(true);
		Long staffId = (Long) session.getAttribute("staffId");
		Long facilityId = (Long) session.getAttribute("facilityId");
		FacilityStaff fStaff = facilityStaffService.findFacilityStaff(
				facilityId, staffId);
		try {

			sbarService.moveToAcuteCare(patientEpisodeId,plannedDetails, fStaff);
			return "SUCESS";
		} catch (Exception e) {

			return "FAILED";
		}

	}

	@RequestMapping(value="/getCarePathLabworkEntries", method={RequestMethod.GET})
	public @ResponseBody CarePathLabworksResponse getCarePathLabworksEntries(HttpServletRequest request){
		Long patientEpisodeId=Long.parseLong(request.getParameter("patientEpisodeId"));
		CarePathLabworksResponse response = new CarePathLabworksResponse();
		if(!StringUtils.isEmpty(patientEpisodeId)){
			
			List<CarePathLabworksDto> labworksList = carePathLabworksService.getCarePathLabworksEntries(patientEpisodeId);
			if(labworksList != null){
				for(CarePathLabworksDto list:labworksList){
					list.setSignsSymptomsLabworkAttrs(signsSymptomsLabworkService.getSignSymptomsLabWorkAttributesByParentId(list.getSignsSymptomsLabworkId(),patientEpisodeId));
				}
				response.setLabworks(labworksList);
				response.setStatus("success");
			}else{
				response.setStatus("Failed");
			}
		}else{
			response.setStatus("Patient id Not available");
		}
		return response;
	}


	@RequestMapping(value = "/updateCarePathLabworkEntries", method = { RequestMethod.GET })
	public @ResponseBody String updateCarePathLabworkEntries(
			HttpServletRequest request) {
		HttpSession session = request.getSession();
		Long carepathApprLabworkId = StringUtils.isEmpty(request
				.getParameter("carepathApprLabworkId")) ? 0L : Long
						.parseLong(request.getParameter("carepathApprLabworkId"));
		Long carepathRejLabworkId = StringUtils.isEmpty(request
				.getParameter("carepathRejLabworkId")) ? 0L : Long
						.parseLong(request.getParameter("carepathRejLabworkId"));
		Long pSbarId = StringUtils.isEmpty(request.getParameter("sbarId")) ? null:Long.parseLong(request.getParameter("sbarId"));
		Long signSymptomLabworkId = StringUtils.isEmpty(request.getParameter("sslId")) ? null:Long.parseLong(request.getParameter("sslId"));
		LoggedUserModel loggedUser = (LoggedUserModel) session
				.getAttribute("loggedUser");
		CarePathLabworksDto dto = new CarePathLabworksDto();
		if (!StringUtils.isEmpty(carepathApprLabworkId)
				&& carepathApprLabworkId != 0) {
			dto.setId(carepathApprLabworkId);
			dto.setApproved(true);
			dto.setRejected(false);
			dto.setFacilityStaffId(loggedUser.getResponsibilityId());
		}
		if (!StringUtils.isEmpty(carepathRejLabworkId)
				&& carepathRejLabworkId != 0) {
			dto.setId(carepathRejLabworkId);
			dto.setRejected(true);
			dto.setApproved(false);
			dto.setFacilityStaffId(loggedUser.getResponsibilityId());

		}
		dto.setSbarId(pSbarId);
		dto.setSignsSymptomsLabworkId(signSymptomLabworkId);
		dto.setStandingOrders(loggedUser.getStandingOrders());
		if (loggedUser.getResponsibilityName().equalsIgnoreCase(
				Constants.DOCTOR_TYPE)) {
			dto.setFacilityStaffId(loggedUser.getFstaffId());
		} else if (loggedUser.getResponsibilityName().equalsIgnoreCase(
				Constants.NURSE_TYPE)) {
			dto.setFacilityStaffId(loggedUser.getFstaffId());
		}
		return carePathLabworksService.saveOrUpdate(dto);
	}

	@RequestMapping(value = "/getlabworkValuesFromCIC", method = { RequestMethod.GET })
	public @ResponseBody CarePathLabworksValuesFrmCICResponse getLabworkValuesFromCIC(@RequestParam Long patientEpisodeId,@RequestParam Long signSymptomLabworkId){
		CarePathLabworksValuesFrmCICResponse response = new CarePathLabworksValuesFrmCICResponse();
		List<CarePathLabworksValuesFrmCICDto> list = new ArrayList<CarePathLabworksValuesFrmCICDto>();
		if(!StringUtils.isEmpty(patientEpisodeId) && !StringUtils.isEmpty(signSymptomLabworkId)){
			list = carePathLabworksService.getLabworkValuesFromCIC(patientEpisodeId, signSymptomLabworkId);
		}
		if(list.size() > 0){
			response.setList(list);
			response.setStatus("ok");
		}else{
			response.setStatus("notOk");
		}
		return response;
	}

	@RequestMapping(value = "/showLabworksNotInCarePathLabworks", method = { RequestMethod.GET })
	public @ResponseBody LabworksResponse showLabworksNotInCarePathLabworks(@RequestParam Long patientEpisodeId){
		LabworksResponse response = new LabworksResponse();
		
		List<SignsSymptomsLabworkDto> list = carePathLabworksService.showLabworksNotInCarePathLabworks(patientEpisodeId);
		if(list !=null){
			response.setList(list);
			response.setStatus("success");
		}else{
			response.setStatus("Not Available");
		}
		return response;
	}

	@RequestMapping(value = "/saveApprovedLabworkIncLabworks", method = { RequestMethod.GET })
	public @ResponseBody String saveApprovedLabworkIncLabworks(@RequestParam Long patientEpisodeId,@RequestParam Long signSymptomLabworkId,HttpSession session){
		CarePathLabworksDto dto =new CarePathLabworksDto();
		
		LoggedUserModel loggedUser = (LoggedUserModel) session.getAttribute("loggedUser");
		dto.setPatientEpisodeId(patientEpisodeId);
		dto.setSignsSymptomsLabworkId(signSymptomLabworkId);
		dto.setRejected(false);
		dto.setFacilityStaffId(loggedUser.getFstaffId());
		dto.setStandingOrders(loggedUser.getStandingOrders());
		if (loggedUser.getResponsibilityName().equalsIgnoreCase(
				Constants.DOCTOR_TYPE)) {
			dto.setApproved(true);
		} else if (loggedUser.getResponsibilityName().equalsIgnoreCase(
				Constants.NURSE_TYPE)) {
			dto.setApproved(false);
		}
		return carePathLabworksService.saveOrUpdate(dto);
	}
	@RequestMapping(value = "/getEncounters", method = RequestMethod.GET)
	public @ResponseBody List<PatientEncounterDto> getAllEncounters(@RequestParam Long patientEpisodeId,HttpServletRequest request) {
		   
		return sbarService.findAllEncounterss(patientEpisodeId);
	}
	

}
