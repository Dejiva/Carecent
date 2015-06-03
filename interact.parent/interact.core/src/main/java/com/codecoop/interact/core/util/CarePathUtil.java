package com.codecoop.interact.core.util;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;

import org.apache.commons.lang3.StringUtils;
import org.springframework.util.CollectionUtils;
import org.w3c.dom.Document;
import org.xml.sax.InputSource;
import org.xml.sax.SAXException;

import com.codecoop.interact.core.dto.CPAttrValueDto;
import com.codecoop.interact.core.dto.CarePathStepTrackerDto;
import com.codecoop.interact.core.dto.NextCarePathCode;
import com.codecoop.interact.core.dto.carepath.InstructionDto;
import com.codecoop.interact.core.dto.carepath.LabResultDto;
import com.codecoop.interact.core.dto.carepath.LabWorkDto;
import com.codecoop.interact.core.dto.carepath.MetConditionDto;
import com.codecoop.interact.core.dto.carepath.NextCarePathDto;
import com.codecoop.interact.core.dto.carepath.StepDto;
import com.codecoop.interact.core.dto.carepath.SymptomDto;
import com.codecoop.interact.core.dto.carepath.VitalSignDto;

public class CarePathUtil {

	public static CarePathStepTrackerDto populateAndEvaluateFormData(int stepOrder,StepDto carePathStep, Map<String, String> requestMap){
		CarePathStepTrackerDto carePathStepTracker = new CarePathStepTrackerDto();
		carePathStepTracker.setStepId(carePathStep.getStepId());
		carePathStepTracker.setStepOrder(stepOrder);
		carePathStepTracker.setStepName(carePathStep.getStepName());
		carePathStepTracker.setStepType(carePathStep.getStepType());
		boolean metAnyInstruction=populateInstructions(carePathStep.getInstruction(),requestMap,carePathStepTracker);
		boolean metAnySymptom = populateSymptoms(carePathStep.getSymptom(), requestMap, carePathStepTracker);
		boolean metAnyVitalSign = populateVitalSigns(carePathStep.getVitalSign(), requestMap, carePathStepTracker);
		boolean metAnyLabWorks = populateLabWorks(carePathStep.getLabWork(), requestMap, carePathStepTracker);
		boolean metAnyLatResults = populateLabResults(carePathStep.getLabResult(), requestMap, carePathStepTracker);



		if(metAnySymptom || metAnyVitalSign || metAnyLabWorks || metAnyLatResults||metAnyInstruction){
			//This will lead to MET_ANY in DECISION object
			populateNextStep(carePathStep.getDecision().getMetAny(), carePathStep, carePathStepTracker);
		}else{
			if(carePathStepTracker.isSignOrLabWorkExists()||carePathStepTracker.isLabWorkExists()){ 
				if(carePathStepTracker.isDataForSignOrLabWorkExists()){
					
					populateNextStep(carePathStep.getDecision().getMetNone(), carePathStep, carePathStepTracker);
				}
				else
				{ if(carePathStepTracker.isLabWorkExists()){
					carePathStep.setErrorMsg("Please provide Data in Lab Order Tab");
				   }else{
					   carePathStep.setErrorMsg("Please provide Data in Vital Sign Tab");
				   }
				}
			}else{
		
				populateNextStep(carePathStep.getDecision().getMetNone(), carePathStep, carePathStepTracker);
			}

		}
		return carePathStepTracker;	
	}

	private static void populateNextStep(MetConditionDto metCondition, StepDto carePathStep, CarePathStepTrackerDto carePathStepTracker){
		if(metCondition.getNextStepId() != null){
			carePathStepTracker.setNotifyDoctorFlag(false);
			carePathStepTracker.setNextStepId(metCondition.getNextStepId());
			carePathStep.setErrorMsg("");
		} else if (Constants.CAREPATH_NOTIFY_DOCTOR.equals(metCondition.getMessage())){
			carePathStepTracker.setNotifyDoctorFlag(true);
			
		}else{
			//DO_NOTHING
			if(carePathStep.getStepId()==1){
				carePathStep.setErrorMsg("Please select atleast one symptom");
			}
			
			carePathStepTracker.setNotifyDoctorFlag(false);
			carePathStepTracker.setNextStepId(carePathStep.getStepId());				
		}
	}


	private static boolean populateSymptoms(List<SymptomDto> symptoms, Map<String, String> requestMap, CarePathStepTrackerDto carePathStepTracker){
		boolean metAny = false;
		if(!CollectionUtils.isEmpty(symptoms)){
			for(SymptomDto symptom:symptoms){
				String value = (String) requestMap.get(symptom.getCarePathAttrCode());
				symptom.setExists(false);
				if(symptom.getCarePathAttrCode().equals(value)){
					metAny = true;
					CPAttrValueDto cpAttrValue = new CPAttrValueDto();
					cpAttrValue.setCarePathAttrCode(symptom.getCarePathAttrCode());
					cpAttrValue.setCarePathAttrName(symptom.getName());
					symptom.setExists(true);
					cpAttrValue.setMetCondition(true);
					//TODO need to look at this logic by Rama
					if(!CollectionUtils.isEmpty(symptom.getNextCarePath())){
						for(NextCarePathDto nextCarePath:symptom.getNextCarePath()){
							if(!nextCarePath.isStepFlag()){
								NextCarePathCode nextCarePathCode = populateNextCarePathStatus(nextCarePath.getCarePathCode());
								carePathStepTracker.getNextCarePathCodes().add(nextCarePathCode);
								cpAttrValue.setMetCondition(false);
							}							
						}
					}

					carePathStepTracker.getAttrValues().add(cpAttrValue);

				}
			}
		}
		return metAny;
	}

	//TODO need to check if the care path is scanned in database
	private static NextCarePathCode populateNextCarePathStatus(String carePathCode){
		NextCarePathCode nextCarePathCode = new NextCarePathCode();
		nextCarePathCode.setCarePathCode(carePathCode);
		nextCarePathCode.setScanned(false);
		return nextCarePathCode;
	}


	private static boolean populateVitalSigns(List<VitalSignDto> vitalSigns, Map<String, String> requestMap, CarePathStepTrackerDto carePathStepTracker){
		boolean metAny = false;
		if(!CollectionUtils.isEmpty(vitalSigns)){
			carePathStepTracker.setSignOrLabWorkExists(true);
			for(VitalSignDto vitalSign:vitalSigns){
				String value = (String)requestMap.get(vitalSign.getCarePathAttrCode().trim());
				vitalSign.setExists(false);
				if(!StringUtils.isEmpty(value)){
					carePathStepTracker.setDataForSignOrLabWorkExists(true);
					vitalSign.setValue(value);
					CPAttrValueDto cpAttrValue = new CPAttrValueDto();
					cpAttrValue.setCarePathAttrCode(vitalSign.getCarePathAttrCode());
					cpAttrValue.setCarePathAttrName(vitalSign.getName());
					vitalSign.setExists(true);

					if(vitalSign.getMin()!=null&&!vitalSign.getMin().isEmpty()){
						cpAttrValue.setMinValue(vitalSign.getMin());
					}

					if(vitalSign.getMax()!=null&&!vitalSign.getMax().isEmpty()){
						cpAttrValue.setMaxValue(vitalSign.getMax());
					}
					if(vitalSign.getDatatype()!=null&&!vitalSign.getDatatype().isEmpty()){
						cpAttrValue.setDataType(vitalSign.getDatatype());
					}
					if(VitalSignUtils.isMet(vitalSign, value)){
						metAny = true;
						cpAttrValue.setMetCondition(true);
					}else{
						cpAttrValue.setMetCondition(false);
					}
					cpAttrValue.setValue(value);
					carePathStepTracker.getAttrValues().add(cpAttrValue);
				}
			}
		}
		return metAny;
	}

	private static boolean populateLabWorks(List<LabWorkDto> labWorks, Map<String, String> requestMap, CarePathStepTrackerDto carePathStepTracker){
		boolean metAny = false;
		if(!CollectionUtils.isEmpty(labWorks)){
			carePathStepTracker.setLabWorkExists(true);
			for(LabWorkDto labWork:labWorks){
				labWork.setExists(false);
				String value = (String)requestMap.get(labWork.getCarePathAttrCode());
				if(!StringUtils.isEmpty(value)){
					carePathStepTracker.setDataForSignOrLabWorkExists(true);

					labWork.setExists(true);
					labWork.setValue(value);
					
					CPAttrValueDto cpAttrValue = new CPAttrValueDto();
					cpAttrValue.setCarePathAttrCode(labWork.getCarePathAttrCode());
					cpAttrValue.setCarePathAttrName(labWork.getName());
					if(labWork.getMin()!=null&&!labWork.getMin().isEmpty()){
						cpAttrValue.setMinValue(labWork.getMin());
					}

					if(labWork.getMax()!=null&&!labWork.getMax().isEmpty()){
						cpAttrValue.setMaxValue(labWork.getMax());
					}
					if(labWork.getDatatype()!=null&&!labWork.getDatatype().isEmpty()){
						cpAttrValue.setDataType(labWork.getDatatype());
					}
					if(LabWorkUtils.isMet(labWork, value)){
						metAny = true;
						cpAttrValue.setMetCondition(true);
					}else{
						cpAttrValue.setMetCondition(false);
					}


					cpAttrValue.setValue(value);
					carePathStepTracker.getAttrValues().add(cpAttrValue);
				}
				//labWork.setExists(false);
			}
		}
		return metAny;
	}

	private static boolean populateLabResults(List<LabResultDto> labResults, Map<String, String> requestMap, CarePathStepTrackerDto carePathStepTracker){
		boolean metAny = false;
		if(!CollectionUtils.isEmpty(labResults)){
			for(LabResultDto labResult:labResults){
				labResult.setExists(false);
				String value =(String)requestMap.get(labResult.getCarePathAttrCode());
				if(labResult.getCarePathAttrCode().equals(value)){
					metAny = true;
					
					labResult.setValue(value);
					CPAttrValueDto cpAttrValue = new CPAttrValueDto();
					cpAttrValue.setCarePathAttrCode(labResult.getCarePathAttrCode());
					cpAttrValue.setCarePathAttrName(labResult.getName());
					cpAttrValue.setMetCondition(true);
					labResult.setExists(true);
					carePathStepTracker.getAttrValues().add(cpAttrValue);
				}
			}
		}
		return metAny;
	}

	private static boolean populateInstructions(List<InstructionDto> instructionList, Map<String, String> requestMap, CarePathStepTrackerDto carePathStepTracker){
		boolean metAny = false;
		if(!CollectionUtils.isEmpty(instructionList)){
			for(InstructionDto instruction:instructionList){
				String value = (String) requestMap.get(instruction.getCarePathAttrCode());
				metAny = false;
				CPAttrValueDto cpAttrValue = new CPAttrValueDto();
				cpAttrValue.setCarePathAttrCode(instruction.getCarePathAttrCode());
				cpAttrValue.setCarePathAttrName(instruction.getName());
				cpAttrValue.setMetCondition(true);
				carePathStepTracker.getAttrValues().add(cpAttrValue);
			}
		}
		return metAny;
	}
	public static File stringToXml(String xmlSource,String newLocation) throws SAXException, ParserConfigurationException, IOException, TransformerException{
	    DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
	    DocumentBuilder builder = factory.newDocumentBuilder();
	    Document doc = builder.parse(new InputSource(new StringReader(xmlSource)));
	    TransformerFactory tFactory = TransformerFactory.newInstance();
	    Transformer transformer = tFactory.newTransformer();
	    DOMSource source = new DOMSource(doc);
	    File file =new  File(newLocation);
	    StreamResult result = new StreamResult(file);
	    transformer.transform(source, result);
	  return file;
	    
	}  
	@SuppressWarnings("resource")
	public  static String xmlToString(File path) throws IOException{
		BufferedReader reader = new BufferedReader( new FileReader (path));
	    String         line = null;
	    StringBuilder  stringBuilder = new StringBuilder();
	    String         ls = System.getProperty("line.separator");
	    while( ( line = reader.readLine() ) != null ) {
	        stringBuilder.append( line );
	        stringBuilder.append( ls );
	    }
	    return stringBuilder.toString();
	}

}
