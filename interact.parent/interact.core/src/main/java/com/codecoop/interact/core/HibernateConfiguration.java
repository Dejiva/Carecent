package com.codecoop.interact.core;


import java.util.Properties;


import javax.sql.DataSource;

import org.hibernate.dialect.MySQLDialect;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.orm.hibernate3.HibernateTransactionManager;
import org.springframework.orm.hibernate3.annotation.AnnotationSessionFactoryBean;

import com.codecoop.interact.core.domain.AcuteCare;
import com.codecoop.interact.core.domain.AcuteCareAttributes;
import com.codecoop.interact.core.domain.AcuteCareAttributesValues;
import com.codecoop.interact.core.domain.AdmissionAttributes;
import com.codecoop.interact.core.domain.AdmissionAttributesValues;
import com.codecoop.interact.core.domain.AlertsToAssigned;
import com.codecoop.interact.core.domain.AlertsToOtherStaff;
import com.codecoop.interact.core.domain.Announcement;
import com.codecoop.interact.core.domain.AssignedDocRelation;
import com.codecoop.interact.core.domain.CarePath;
import com.codecoop.interact.core.domain.CarePathLabworks;
import com.codecoop.interact.core.domain.CarePathTracker;
import com.codecoop.interact.core.domain.CarePathTrackerAttr;
import com.codecoop.interact.core.domain.ChangeInCondition;
import com.codecoop.interact.core.domain.CicDecisionParms;
import com.codecoop.interact.core.domain.Ethnicity;
import com.codecoop.interact.core.domain.Facility;
import com.codecoop.interact.core.domain.FacilityAddress;
import com.codecoop.interact.core.domain.FacilityContact;
import com.codecoop.interact.core.domain.FacilityStaff;
import com.codecoop.interact.core.domain.Guardian;
import com.codecoop.interact.core.domain.GuardianAddress;
import com.codecoop.interact.core.domain.GuardianContact;
import com.codecoop.interact.core.domain.Hospital;
import com.codecoop.interact.core.domain.HospitalAddress;
import com.codecoop.interact.core.domain.HospitalCareTeam;
import com.codecoop.interact.core.domain.HospitalContact;
import com.codecoop.interact.core.domain.HospitalStaff;
import com.codecoop.interact.core.domain.HsptlStaffSpeciality;
import com.codecoop.interact.core.domain.HsptlStaffType;
import com.codecoop.interact.core.domain.MedicalReconciliation;
import com.codecoop.interact.core.domain.MedicineResolution;
import com.codecoop.interact.core.domain.MessageAlert;
import com.codecoop.interact.core.domain.MessageAlertDetails;
import com.codecoop.interact.core.domain.MessageAlertScenarios;
import com.codecoop.interact.core.domain.MessageEligibilityStaff;
import com.codecoop.interact.core.domain.PLanguage;
import com.codecoop.interact.core.domain.Patient;
import com.codecoop.interact.core.domain.PatientAddress;
import com.codecoop.interact.core.domain.PatientCarePaths;
import com.codecoop.interact.core.domain.PatientContact;
import com.codecoop.interact.core.domain.PatientEncounter;
import com.codecoop.interact.core.domain.PatientEpisode;
import com.codecoop.interact.core.domain.PrescribeMedicine;
import com.codecoop.interact.core.domain.Responsibilities;
import com.codecoop.interact.core.domain.Sbar;
import com.codecoop.interact.core.domain.SbarNotes;
import com.codecoop.interact.core.domain.SbarSymptoms;
import com.codecoop.interact.core.domain.SignsSymptomsLabwork;
import com.codecoop.interact.core.domain.SignsSymptomsLabworkAttr;
import com.codecoop.interact.core.domain.Staff;
import com.codecoop.interact.core.domain.StaffAddress;
import com.codecoop.interact.core.domain.StaffContact;
import com.codecoop.interact.core.domain.StaffRoles;
import com.codecoop.interact.core.domain.States;
import com.codecoop.interact.core.domain.StopAndWatch;
import com.codecoop.interact.core.domain.StopAndWatchHistory;
import com.codecoop.interact.core.domain.StopAndWatchHistoryModified;
import com.codecoop.interact.core.domain.SystemProperties;

@Configuration
public class HibernateConfiguration {

	@Value("#{interactDataSource}")
	private DataSource interactDataSource;

	@Bean
	public AnnotationSessionFactoryBean sessionFactoryBean() {
		Properties props = new Properties();
		props.put("hibernate.dialect", MySQLDialect.class.getName());
		props.put("hibernate.format_sql", "true");
		props.put("hibernate.show_sql", false);
	//	props.put("hibernate.hbm2ddl.auto","validate");

		AnnotationSessionFactoryBean bean = new AnnotationSessionFactoryBean();
		bean.setAnnotatedClasses(new Class[]{
				Ethnicity.class,
				Facility.class,
				FacilityAddress.class,
				FacilityContact.class,
				Staff.class,
				StaffAddress.class,
				StaffContact.class,
				FacilityStaff.class,
				StaffRoles.class,
				MessageAlert.class,
				MessageAlertDetails.class,
				MessageEligibilityStaff.class,
				MessageAlertScenarios.class,
				Responsibilities.class,
				
				AdmissionAttributes.class,
				AdmissionAttributesValues.class,
				Patient.class,
				PatientAddress.class,
				PatientContact.class,
				PLanguage.class,
				StopAndWatch.class,
				StopAndWatchHistory.class,
				StopAndWatchHistoryModified.class,
				PatientEpisode.class,
				CarePathTracker.class,
				CarePathTrackerAttr.class,
				ChangeInCondition.class,
				SignsSymptomsLabwork.class,
				SbarSymptoms.class,
				SignsSymptomsLabworkAttr.class,
//				SignsSymptomsLabworkAttrCICDecisionParms.class,
    			CicDecisionParms.class,
    			States.class,
				Sbar.class,
				SbarNotes.class,
				CarePath.class,
				Guardian.class,
				GuardianContact.class,
				GuardianAddress.class,
				Hospital.class,
				HospitalAddress.class,
				HospitalContact.class,
				HospitalStaff.class,
				HospitalCareTeam.class,
				MedicalReconciliation.class,
				MedicineResolution.class,
				HsptlStaffSpeciality.class,
				HsptlStaffType.class,
				AlertsToAssigned.class,
				AlertsToOtherStaff.class,
				SystemProperties.class,
				PatientCarePaths.class,
				AcuteCareAttributes.class,
				AcuteCareAttributesValues.class,
				AcuteCare.class,
				AssignedDocRelation.class,
				Announcement.class,
				PatientEncounter.class,
				PrescribeMedicine.class,
				PatientEncounter.class,
				CarePathLabworks.class
				
	});		
		bean.setHibernateProperties(props);
		bean.setDataSource(this.interactDataSource);
		bean.setSchemaUpdate(true);
		return bean;
	}

	@Bean
	public HibernateTransactionManager transactionManager() {
		return new HibernateTransactionManager( sessionFactoryBean().getObject() );
	}

}
