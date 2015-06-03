package com.codecoop.interact.core.service;

import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:app-context.xml"})
@Transactional
public class AdmissionServiceTest { 
	
	@Autowired
	AdmissionService admissionService;
//
//	@Test
//	public void testAdmitPatient(){
//		Patient patient = 
//			new Patient("Test Patient", 25L, new Date(), "M", "O+", null,null,new Date(), 
//					"JGUMPART", new Date(), "JGUMPART");
//		
//		PatientEpisode patientEpisode = new PatientEpisode();
//		patientEpisode.setPatient(patient);
//		patient.getPatientEpisodeList().add(patientEpisode);
		
//		admissionService.admitPatient(patient);
//	}
	
	/*@Test
	@Transactional
	public void savePatientAddmision() {
		admissionService.createPatientEpisode();
	}*/

/*	@Test
	public void testSaveMedicalReconcilation(){
		MedicalReconciliationDto dto = new MedicalReconciliationDto();
		dto.setPatientEpisodeId(5L);
		dto.setMedicationDetails("Paracetemol taken");
		dto.setClarificationNeeded("For Fever");
		dto.setResolution("STOP");
		dto.setRecommendationId(2L);
		dto.setResolutionId(3L);
		
		admissionService.saveMedicalReconciliation(Collections.singletonList(dto), "JGUMPART");
		
	}*/
	
/*	@Test
	public void testGetMedicalReconcilationDto(){
		List<MedicalReconciliationDto> dtoList = 
			admissionService.getMedicalReconcilationDetailsByEpisodeId(5L);
		for(MedicalReconciliationDto dto : dtoList){
			System.out.println(dto.toString());
		}
	}*/
	
}
