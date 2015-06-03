package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.MedicineResolutionDao;
import com.codecoop.interact.core.domain.MedicineResolution;
import com.codecoop.interact.core.dto.MedicineResolutionDto;
 
@Service
public class MedicineResolutionService {

	private static final Logger LOG = Logger.getLogger(MedicineResolutionService.class);
	@Autowired
	MedicineResolutionDao  medicineResolutionDao;
	
	@Transactional
	public List<MedicineResolutionDto> getAllMedicineResolutionTypes(){
		
		List<MedicineResolutionDto> medicineResolutionDtoList=new ArrayList<MedicineResolutionDto>();
		
		for(MedicineResolution list:medicineResolutionDao.findAll()){
			MedicineResolutionDto dto=new MedicineResolutionDto();
			dto.setId(list.getId());
			dto.setResolutionType(list.getResolutionType());
			medicineResolutionDtoList.add(dto);
		}
	
		 return medicineResolutionDtoList;
	}
	
	

}
