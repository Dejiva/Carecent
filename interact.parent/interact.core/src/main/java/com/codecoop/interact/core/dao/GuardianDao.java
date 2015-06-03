package com.codecoop.interact.core.dao;

import org.springframework.stereotype.Component;

import com.codecoop.interact.core.domain.Guardian;

@Component
public class GuardianDao extends BaseDao<Guardian>{
	
	public void savePatientEpisode(Guardian guardian){
		saveOrUpdate(guardian);
	}
}
