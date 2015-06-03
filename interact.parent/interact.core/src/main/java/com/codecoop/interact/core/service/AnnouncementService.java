package com.codecoop.interact.core.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.AnnouncementDao;
import com.codecoop.interact.core.domain.Announcement;
import com.codecoop.interact.core.dto.AnnouncementDto;

@Service
public class AnnouncementService {

	private static final Logger LOG = Logger.getLogger(AnnouncementService.class);
	
	@Autowired
	AnnouncementDao dao;

	@Transactional
	public AnnouncementDto saveAnnouncement(AnnouncementDto dto) {
		Announcement announcement = new Announcement();
		announcement.setAnnouncementText(dto.getAnnouncementText());
		announcement.setActiveFlag(dto.getActiveFlag());
		announcement.setDeletedFlag(dto.getDeletedFlag());
		announcement.setModifiedDate(new Date());
		announcement.setCreatedDate(new Date());
		announcement.setUserCreated(dto.getUserCreated());
		announcement.setFacilityId(dto.getFacilityId());
		dao.save(announcement);
		
		dto.setId(announcement.getId());
		dto.setCreatedDate(announcement.getCreatedDate().toString());
		dto.setModifiedDate(announcement.getModifiedDate().toString());
		return dto;
	}

	@Transactional
	public List<AnnouncementDto> getAllAnnouncement(Long facilityId) {
		List<AnnouncementDto> dto = new ArrayList<AnnouncementDto>();
		List<Announcement> adao = dao.getAllAnnouncement(facilityId);

		if (adao != null) {
			for (Announcement announcement : adao) {
				AnnouncementDto dt = new AnnouncementDto();
				dt.setId(announcement.getId());
				dt.setUserCreated(announcement.getUserCreated());
				dt.setAnnouncementText(announcement.getAnnouncementText());
				dt.setActiveFlag(announcement.getActiveFlag());
				dt.setDeletedFlag(announcement.getDeletedFlag());
				dt.setCreatedDate((announcement.getCreatedDate()==null)?"-":announcement.getCreatedDate().toString());
				dt.setModifiedDate(announcement.getModifiedDate().toString());
				
				dto.add(dt);
			}
		}

		return dto;
	}
	
	@Transactional
	public List<AnnouncementDto> getAllActiveAnnouncement(Long facilityId) {
		List<AnnouncementDto> dto = new ArrayList<AnnouncementDto>();
		List<Announcement> adao = dao.getAllActiveAnnouncement(facilityId);

		if (adao != null) {
			for (Announcement announcement : adao) {
				AnnouncementDto dt = new AnnouncementDto();
				dt.setId(announcement.getId());
				dt.setUserCreated(announcement.getUserCreated());
				dt.setAnnouncementText(announcement.getAnnouncementText());
				dt.setActiveFlag(announcement.getActiveFlag());
				dt.setDeletedFlag(announcement.getDeletedFlag());
				dt.setCreatedDate((announcement.getCreatedDate()==null)?"-":announcement.getCreatedDate().toString());
				dt.setModifiedDate(announcement.getModifiedDate().toString());
				dto.add(dt);
			}
		}

		return dto;
	}

	@Transactional
	public void avtivateAnnouncement(Integer id, Integer actflag) {
		Announcement announcement = dao.getAnnounceMent(id);
		announcement.setActiveFlag(actflag);
		dao.save(announcement);
	}

	@Transactional
	public void deleteAnnouncement(Integer id) {
		Announcement announcement = dao.getAnnounceMent(id);
		announcement.setDeletedFlag(1);
		dao.save(announcement);
	}
	
	@Transactional
	public AnnouncementDto updateAnnouncement(Integer id, String annText){
		Announcement announcement = dao.getAnnounceMent(id);
		AnnouncementDto dto=new AnnouncementDto();
		
		announcement.setAnnouncementText(annText);
		announcement.setModifiedDate(new Date());
		
		dao.save(announcement);
		
		dto.setId(announcement.getId());
		dto.setAnnouncementText(announcement.getAnnouncementText());
		dto.setActiveFlag(announcement.getActiveFlag());
		dto.setCreatedDate((announcement.getCreatedDate()==null)?"-":announcement.getCreatedDate().toString());
		dto.setModifiedDate(announcement.getModifiedDate().toString());
		dto.setUserCreated(announcement.getUserCreated());
		dto.setDeletedFlag(announcement.getDeletedFlag());
		
		return dto;
	}
	
}
