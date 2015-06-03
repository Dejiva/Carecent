package com.codecoop.interact.core.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.codecoop.interact.core.dao.FacilityStaffDaoImpl;
import com.codecoop.interact.core.dao.StaffDaoImpl;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.domain.StaffContact;
import com.codecoop.interact.core.model.ForgotPasswordModel;
import com.codecoop.interact.core.util.CoreAppPropertyReader;
import com.codecoop.interact.core.util.PasswordGenUtil;

@Service
public class ResetPasswordService{

	private static final Logger LOG = Logger.getLogger(ResetPasswordService.class);
	@Autowired
	private FacilityStaffDaoImpl facilityStaffDao;

	@Autowired
	private StaffDaoImpl staffDaoImpl;

	@Autowired
	MailService mailService;

	@Autowired
	CoreAppPropertyReader coreAppPropertyReader;

	@Transactional
	public String resetPassword(String workMail){
		try{
			List<ForgotPasswordModel> fStaffList = facilityStaffDao.findStaffByWorkMail(workMail);
			for(ForgotPasswordModel fStaff:fStaffList){
				Staff staff = staffDaoImpl.findById(fStaff.getfStaffId());
				String genPassCode = PasswordGenUtil.generatePswd();
				staff.setPasswd(PasswordGenUtil.encriptPassword(genPassCode));
				staffDaoImpl.saveOrUpdate(staff);
				String receiver = fStaff.getWorkEmail();
				mailService.sendMail("Interact Staff ResetPassword",
						"\n\n\nPassword for"+"\t\""+
								fStaff.getUserName()+"\"\t"+"is reset to \""+genPassCode+
								"\"",receiver, coreAppPropertyReader.getUserRegMailSender());
			}
			return "Success";
		}catch(Exception e){
			LOG.error(e.getMessage(),e);
			e.printStackTrace();
			return "Failed";
		}
	} 

}
