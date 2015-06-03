package com.codecoop.interact.core.service;

import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Set;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBException;
import javax.xml.bind.Unmarshaller;

import org.apache.log4j.Logger;
import org.dozer.DozerBeanMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.carepath.CarePathXml;
import com.codecoop.interact.core.dao.CarePathDaoImpl;
import com.codecoop.interact.core.dao.PatientCarePathsDaoImpl;
import com.codecoop.interact.core.dao.SystemPropertiesDaoImpl;
import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.PatientCarePaths;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.dto.carepath.CarePathDto;
import com.codecoop.interact.core.dto.carepath.NextCarePathDto;
import com.codecoop.interact.core.dto.carepath.StepDto;
import com.codecoop.interact.core.dto.carepath.SymptomDto;
import com.codecoop.interact.core.dto.sbar.SbarReleventSymptomsDto;
import com.codecoop.interact.core.util.CarePathUtil;
import com.codecoop.interact.core.util.Constants;
import com.codecoop.interact.sbar.SbarReleventSymptoms;

@Service
public class CarePathGeneratorService {
	private static final Logger LOG = Logger.getLogger(CarePathGeneratorService.class);
	
	@Autowired
	CarePathDaoImpl carePathDao;

	@Autowired
	PatientCarePathsDaoImpl patientCarePathsDao;
	@Autowired
SystemPropertiesDaoImpl systemPropertiesDao;
	@Autowired
	DozerBeanMapper dozerBeanMapper;
	@Autowired
	PatientEpisodeService patientEpisodeService;

	// Un-Marshaling XML to Care-Path object
	@SuppressWarnings("deprecation")
	@Transactional
	public CarePathDto generate(String carePathCode,PatientEpisode patientEpisode) throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(CarePathXml.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
		CarePath carePath=carePathDao.findByCarePathCode(carePathCode);
		PatientCarePaths patientCarePaths=patientCarePathsDao.findByCPCodeAndP_EpisodeID(carePath, patientEpisode);
		CarePathXml carePathXml;
		if(patientCarePaths==null){
			patientCarePaths=new PatientCarePaths();
			patientCarePaths.setCarePath(carePath);
			patientCarePaths.setPatientEpisode(patientEpisode);
			try {
				patientCarePaths.setPatientCarePathXml(CarePathUtil.xmlToString(new File(carePathDao.getCarePathLocation(carePathCode))));
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			patientCarePathsDao.saveOrUpdate(patientCarePaths);
		}
	    carePathXml = (CarePathXml) jaxbUnmarshaller
					.unmarshal(new ByteArrayInputStream(patientCarePaths.getPatientCarePathXml().getBytes(Charset.forName("UTF-8"))));
		
		/*CarePathXml carePathXml = (CarePathXml) jaxbUnmarshaller
				.unmarshal(new File(carePathDao.getCarePathLocation(carePathCode)));*/
		CarePathDto carePathDto = dozerBeanMapper.map(carePathXml,CarePathDto.class);
		// TODO populate CarePathDto with patient specific data
		return carePathDto;
	}

	@Transactional
	public SbarReleventSymptomsDto  generateSbarReleventSymptoms() throws JAXBException {
		JAXBContext jaxbContext = JAXBContext.newInstance(SbarReleventSymptoms.class);
		Unmarshaller jaxbUnmarshaller = jaxbContext.createUnmarshaller();
	
		SbarReleventSymptoms sbarReleventSymptoms = (SbarReleventSymptoms) jaxbUnmarshaller
				.unmarshal(new File(systemPropertiesDao.findByPropertyName(Constants.SBAR_XML_LOCATION).getPropertyValue()));
				SbarReleventSymptomsDto sbarReleventSymptomsDto = dozerBeanMapper.map(sbarReleventSymptoms,SbarReleventSymptomsDto.class);
		return sbarReleventSymptomsDto;
		
	}
	public StepDto getStep(CarePathDto carePathDto, Integer stepId) {

		for (StepDto stepDto : carePathDto.getSteps().getStep()) {
			if (stepId.equals(stepDto.getStepId())) {
				stepDto.setCarePathName(carePathDto.getCarePathName());
				return stepDto;
			}
		}
		return null;
	}

	public CarePathDto populateNextCarePathAttr(CarePathDto carePathDto,PatientEpisode patientEpisode) {
		
        for(StepDto stepDto:carePathDto.getSteps().getStep()){
          
        	populateSymtomStepDto(stepDto.getSymptom(),patientEpisode );
        }	
		return carePathDto;
	}
	
	public void populateSymtomStepDto(List<SymptomDto> symtomDtoList,PatientEpisode patientEpisode)
	{  List<SymptomDto> nextCarePathList=null;
		Set<SymptomDto> modifiedSymtomDtoSet= new LinkedHashSet<SymptomDto>();
		if(symtomDtoList.size()>0){
			CarePathDto nextCarePathDto;
			for(SymptomDto symtom:symtomDtoList){
				if(symtom.getCarePathAttrCode().contains("_CIC_"))
					symtom.setFromNextCP(true);
				modifiedSymtomDtoSet.add(symtom);
				
				for(NextCarePathDto nextCarePath:symtom.getNextCarePath()){
					if(nextCarePath.isStepFlag()){
						String carePathCode=nextCarePath.getCarePathCode();
						Integer stepId=nextCarePath.getStepId();
					
						try {
							nextCarePathDto=generate(carePathCode,patientEpisode);
							StepDto step = getStep(nextCarePathDto, stepId);
							String carePathName=step.getCarePathName();
							 List<SymptomDto> temp=new ArrayList<SymptomDto>();
						   for(SymptomDto nextCarePathsymptom:step.getSymptom())
						   {
							  if(symtomDtoList.contains(nextCarePathsymptom)){
								
							  }
							   else{
								   nextCarePathsymptom.setName(nextCarePathsymptom.getName()+"("+carePathName+")");
								   nextCarePathsymptom.setFromNextCP(true);
								   temp.add(nextCarePathsymptom);
								   	 
							   }
						
						
						   }	
						   modifiedSymtomDtoSet.addAll(temp);
						} catch (JAXBException e) {

							e.printStackTrace();
						}

					}
				}
			}
			
			symtomDtoList.clear();
			symtomDtoList.addAll(modifiedSymtomDtoSet);
		}
	}



}
