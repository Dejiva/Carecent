package com.codecoop.interact.web.util;

import java.util.ArrayList;
import java.util.List;

import org.codehaus.jackson.map.ObjectMapper;
import org.springframework.util.CollectionUtils;

import com.codecoop.interact.core.dto.carepath.InstructionDto;
import com.codecoop.interact.core.dto.carepath.LabResultDto;
import com.codecoop.interact.core.dto.carepath.LabWorkDto;
import com.codecoop.interact.core.dto.carepath.StepAttributeDto;
import com.codecoop.interact.core.dto.carepath.StepDto;
import com.codecoop.interact.core.dto.carepath.SymptomDto;
import com.codecoop.interact.core.dto.carepath.VitalSignDto;
import com.codecoop.interact.web.carepath.FieldBean;
import com.codecoop.interact.web.carepath.FieldRow;

public class CarePathFormGenerator {

	public static String generateForm(StepDto carePathStep){
		if(carePathStep.getErrorMsg()==null)
		{
			carePathStep.setErrorMsg("");
		}
		List<FieldRow> formFields = new ArrayList<FieldRow>();
		formFields.add(addCarePathName(carePathStep.getCarePathName()));
		formFields.add(addCarePathError(carePathStep.getErrorMsg()));
		formFields.add(addSetpId(carePathStep.getStepId().toString()));
		formFields.add(addSetpName(carePathStep.getStepName()));
		addSymptoms(carePathStep.getSymptom(), formFields);
		addVitalSigns(carePathStep.getVitalSign(), formFields);
		addLabWorks(carePathStep.getLabWork(), formFields);
		addLabResults(carePathStep.getLabResult(), formFields);
		addInstructions(carePathStep.getInstruction(), formFields);
//		addNextButton(formFields);
		
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(formFields);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}

	public static String generateCarePathMessage(String message) {
		ObjectMapper mapper = new ObjectMapper();
		String json = null;
		try {
			json = mapper.writeValueAsString(message);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		return json;
	}
	
	private static FieldRow addSetpId(String stepId){
		FieldRow row = new FieldRow();
		row.getFields().add(generateHiddenTextBox("stepId", stepId));
		return row;
		
	}
	private static FieldRow addSetpName(String stepName){
		FieldRow row = new FieldRow();
		row.getFields().add(generateHeading(stepName));
		return row;
	}
	private static FieldRow addCarePathName(String carePathNaame){
		FieldRow row = new FieldRow();
		FieldBean carePathName=generateTitle(carePathNaame);
		row.getFields().add(carePathName);
		
		return row;
	}
	private static FieldRow addCarePathError(String carePathError){
		FieldRow row = new FieldRow();
		FieldBean carePathErrorMsg=generateErrorMessage(carePathError);
		row.getFields().add(carePathErrorMsg);
		
		return row;
	}
		
	private static void addSymptoms(List<SymptomDto> symptoms, List<FieldRow> formFields){
		if(!CollectionUtils.isEmpty(symptoms)){
			for(SymptomDto symptom:symptoms){
				formFields.add(addSymptomField(symptom));
			}
		}
	}
	
	private static void addVitalSigns(List<VitalSignDto> vitalSigns, List<FieldRow> formFields){
		if(!CollectionUtils.isEmpty(vitalSigns)){
			for(VitalSignDto vitalSign:vitalSigns){
				formFields.add(addVitalSignField(vitalSign));
			}
		}
	}
	
	private static void addLabWorks(List<LabWorkDto> labWorks, List<FieldRow> formFields){
		if(!CollectionUtils.isEmpty(labWorks)){
			for(LabWorkDto labWork:labWorks){
				formFields.add(addLabWorkField(labWork));
			}
		}
	}	
	
	private static void addInstructions(List<InstructionDto> instructions, List<FieldRow> formFields){
		if(!CollectionUtils.isEmpty(instructions)){
			for(InstructionDto instruction:instructions){
				formFields.add(addInstructionField(instruction));
			}
		}
	}
	
	private static void addLabResults(List<LabResultDto> labResults, List<FieldRow> formFields){
		if(!CollectionUtils.isEmpty(labResults)){
			for(LabResultDto labResult:labResults){
				formFields.add(addLabResultField(labResult));
			}
		}
	}
	
	private static FieldRow addSymptomField(SymptomDto symptom){
		FieldRow row = new FieldRow();
		FieldBean checkBox = generateCheckBox(symptom);
		row.getFields().add(checkBox);
		return row;
	}
	
	private static FieldRow addVitalSignField(VitalSignDto vitalSign) {
		FieldRow row = new FieldRow();
		FieldBean label = generateLabel(vitalSign, false);
		FieldBean textBox = generateTextBox(vitalSign, false, 4);
		//FieldBean units=generateUnits(vitalSign, false);
		textBox.getProperty().put("readOnly", "readOnly");
		if(vitalSign.isExists()){
			textBox.setValue(vitalSign.getValue());
			
		}
		row.getFields().add(label);
		row.getFields().add(textBox);
		return row;
	}
	
	private static FieldRow addLabWorkField(LabWorkDto labWork) {
		FieldRow row = new FieldRow();
		FieldBean label = generateLabel(labWork, false);
		FieldBean textBox = generateTextBox(labWork, false, 6);	
		textBox.getProperty().put("readOnly", "readOnly");
		if(labWork.isExists()){
			
			textBox.setValue(labWork.getValue());
		}
		row.getFields().add(label);
		row.getFields().add(textBox);
		return row;
	}
	
	private static FieldRow addInstructionField(InstructionDto instruction){
		FieldRow row = new FieldRow();
		FieldBean label = generateLabel(instruction, true);
		row.getFields().add(label);
		if(!CollectionUtils.isEmpty(instruction.getDetailDescription())){
			for(String detail:instruction.getDetailDescription()){
//				label = generateLabel(detail, true);
				label.getListItems().add(detail);
			}
		}
		
		return row;
	}
	
	private static FieldRow addLabResultField(LabResultDto labResult){
		FieldRow row = new FieldRow();
		FieldBean checkBox = generateCheckBox(labResult);
		checkBox.getProperty().put("disabled", "disabled");
		row.getFields().add(checkBox);
		return row;
	}
	
	private static FieldBean generateCheckBox(StepAttributeDto stepAttr){
		FieldBean filedBean = new FieldBean();
		filedBean.setFieldType("checkbox");
		filedBean.setFiledId(stepAttr.getCarePathAttrCode());
		filedBean.setValue(stepAttr.getCarePathAttrCode());
		filedBean.setLabel(stepAttr.getName());
		if(stepAttr.isExists()){
			filedBean.getProperty().put("checked", "true");
		}
		filedBean.getProperty().put("class","inline-label");
	  if(stepAttr.isFromNextCP()){
		//  filedBean.getProperty().put("class", "move-right");
		  filedBean.getProperty().put("style", "left: 5%;margin-right:5%");
	  }
		return filedBean;
	}
	
	private static FieldBean generateTextBox(StepAttributeDto stepAttr, boolean labelFlag, Integer size){
		FieldBean fieldBean = new FieldBean();
		fieldBean.setFieldType("text");
		fieldBean.setFiledId(stepAttr.getCarePathAttrCode());
		if(labelFlag){
			fieldBean.setLabel(stepAttr.getName());
		}
		if(size != null){
			fieldBean.getProperty().put("maxlength", "4");
			fieldBean.getProperty().put("size", "4");
		}
		return fieldBean;
	}
	
	private static FieldBean generateHiddenTextBox(String id, String value){
		FieldBean filedBean = new FieldBean();
		filedBean.setFieldType("hidden");
		filedBean.setFiledId(id);
		filedBean.setValue(value);
		return filedBean;
	}
//	private static FieldBean generateUnits(StepAttributeDto stepAttr, boolean arrowFlag){
//		FieldBean fieldBean = new FieldBean();
//		if(arrowFlag){
//			fieldBean.setFieldType("label");
//			fieldBean.setLabel(stepAttr.);
//		}else{
//			fieldBean.setFieldType("inputlabel");
//			fieldBean.setLabel(stepAttr.getName());
//		}
//		
//		return null;
//	}
	
	private static FieldBean generateLabel(StepAttributeDto stepAttr, boolean arrowFlag){
		FieldBean fieldBean = new FieldBean();
		if(arrowFlag){
			fieldBean.setFieldType("label");
			fieldBean.setLabel(stepAttr.getName());
		}else{
			fieldBean.setFieldType("inputlabel");
			fieldBean.setLabel(stepAttr.getName());
		}
		return fieldBean;
	}

	private static FieldBean generateLabel(String str, boolean arrowFlag){
		FieldBean fieldBean = new FieldBean();
		if(arrowFlag){
			fieldBean.setLabel(str);
		}else{
			fieldBean.setLabel(str);
		
		}
		return fieldBean;
	}
	
	private static FieldBean generateHeading(String str){
		FieldBean filedBean = new FieldBean();
		filedBean.setFieldType("heading");
		filedBean.setFiledId("stepName");
		filedBean.setHeading(str);
		return filedBean;
	}
	private static FieldBean generateTitle(String str){
		FieldBean filedBean = new FieldBean();
		filedBean.setFieldType("title");
		filedBean.setFiledId("carePathName");
		filedBean.setTitle(str);
		return filedBean;
	}
	private static FieldBean generateErrorMessage(String str){
		FieldBean filedBean = new FieldBean();
		filedBean.setFieldType("error");
		filedBean.setFiledId("carePathError");
		filedBean.setText(str);
		return filedBean;
	}
}
