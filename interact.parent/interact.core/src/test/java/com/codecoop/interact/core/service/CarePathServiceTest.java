package com.codecoop.interact.core.service;

import javax.xml.bind.JAXBException;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.dto.carepath.CarePathDto;
import com.codecoop.interact.core.dto.carepath.InstructionDto;
import com.codecoop.interact.core.dto.carepath.StepDto;
import com.codecoop.interact.core.dto.carepath.SymptomDto;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations={"classpath:test-app-context.xml"})
@Transactional
public class CarePathServiceTest { 
	
	@Autowired
	CarePathGeneratorService carePathGeneratorService;
	
	@Test
	@Transactional
	public void testCarePathGeneration() {
		try {
		//	Patient
			CarePathDto changeInBehaviour = carePathGeneratorService.generate("CIB",new PatientEpisode());
			System.out.println("----------------------------------");
			for(StepDto step: changeInBehaviour.getSteps().getStep()){
				System.out.println("Step Name : " + step.getStepName() + " : Step ID : " + step.getStepId());
				for(SymptomDto symptom: step.getSymptom()){
					System.out.println(symptom.getCarePathAttrCode() + " --> Symptom Name : " + symptom.getName());
				}
				for(InstructionDto inst:step.getInstruction()){
					System.out.println(inst.getCarePathAttrCode() + " --> Instruction : " + inst.getName());
					for(String detail:inst.getDetailDescription()){
						System.out.println(inst.getCarePathAttrCode() + " --> detail : " + detail);
					}
				}
			}
			
		} catch (JAXBException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	
	
}
