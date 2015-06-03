package com.codecoop.interact.core.service;


import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import com.codecoop.interact.core.dao.QIDao;
import com.codecoop.interact.core.dao.SignsSymptomsLabworkDao;
import com.codecoop.interact.core.dto.QIEvaluateNdManageCICDto;
import com.codecoop.interact.core.dto.QIHospInfoResultDto;
import com.codecoop.interact.core.dto.QIHospTransInfostatisticsDOWDto;
import com.codecoop.interact.core.dto.QIHospTransInfostatisticsDto;
import com.codecoop.interact.core.dto.QIHospTransInfostatisticsTODDto;
import com.codecoop.interact.core.dto.QIHospTrnsInfoDetailsDto;
import com.codecoop.interact.core.dto.QIHospTrnsInfoDto;
import com.codecoop.interact.core.dto.QIHospitalTransferInfoDto;
import com.codecoop.interact.core.dto.SymptomsAgainstUnplannedDto;
import com.codecoop.interact.core.dto.SymptomsAgainstUnplannedResponseDto;
import com.codecoop.interact.core.dto.UtilsDto;

@Service
public class QIGraphsService {

	private static final Logger LOG = Logger.getLogger(QIGraphsService.class);
	@Autowired
	SignsSymptomsLabworkDao signsSymptomsLabworkDao;

	@Autowired
	QIDao qiDao;
	
	
	@Transactional
	public SymptomsAgainstUnplannedResponseDto getDataForSymptomsAgainstUnplanned(Long facilityId,String startDate,String endDate) {

		SymptomsAgainstUnplannedResponseDto symptomsAgainstUnplannedResponse=new SymptomsAgainstUnplannedResponseDto();
		if(StringUtils.isEmpty(startDate)||StringUtils.isEmpty(endDate)){
			int year = Calendar.getInstance().get(Calendar.YEAR);
			startDate=year+"-01-01";
			endDate=year+"-12-31";
		}
		List<SymptomsAgainstUnplannedDto> symptomsAgainstUnplannedList=new ArrayList<SymptomsAgainstUnplannedDto>();
		Long totalTransfers=signsSymptomsLabworkDao.getNumberOfTranfersBySymptoms(facilityId,startDate,endDate);

		float percentage=0.0f;
		for(SymptomsAgainstUnplannedDto symptomsAgainstUnplanned:signsSymptomsLabworkDao.getDataForSymptomsAgainstUnplanned(facilityId,startDate,endDate)){
			Long transfersBySym=0l;
			if(symptomsAgainstUnplanned.getNumberOfTransfers()!=null){
				transfersBySym=symptomsAgainstUnplanned.getNumberOfTransfers();
			}else
			{
				symptomsAgainstUnplanned.setNumberOfTransfers(0l);
			}

			if(totalTransfers!=null && totalTransfers!=0 ){
				percentage=(transfersBySym*100.0f)/totalTransfers;

			}
			symptomsAgainstUnplanned.setTransferPercent(percentage);
			symptomsAgainstUnplannedList.add(symptomsAgainstUnplanned);
		}
		if(totalTransfers!=null){
			symptomsAgainstUnplannedResponse.setTotalTransfers(totalTransfers);
		}else
		{
			symptomsAgainstUnplannedResponse.setTotalTransfers(0L);
		}
		symptomsAgainstUnplannedResponse.setSymptomsAgainstUnplanned(symptomsAgainstUnplannedList);

		symptomsAgainstUnplannedResponse.setStartDate(startDate);
		symptomsAgainstUnplannedResponse.setEndDate(endDate);
		return symptomsAgainstUnplannedResponse;

	}


	@Transactional
	public QIHospInfoResultDto getHospitalTransferStatisticsOfCurrentFacilityQIG(Long facilityId,String from,String to){
			QIHospInfoResultDto result=new QIHospInfoResultDto();
			Long morning=0L;Long sunday=0L;
			Long afterNoon=0L;Long monday=0L;
			Long evening=0L;Long tuesday=0L;
			Long night=0L;Long wednesday=0L;
			Long todTotal=0L;Long thursday=0L;
			Long friday=0L;Long saturday=0L;
			Long dowTotal=0L;
			try{
			List<UtilsDto> betWeendates = qiDao.getBetweenDatesofYearFromFDischarge(facilityId,from,to);
			for(UtilsDto findDate:betWeendates){
				QIHospTransInfostatisticsDto dto = qiDao.getHospitalTransferStatisticsOfCurrentFacilityQIG(facilityId,findDate.getDate());
				QIHospTransInfostatisticsDOWDto dayOfWeekDto = StringUtils.isEmpty(dto.getDayOfWeekStatistics())?null:dto.getDayOfWeekStatistics();
				QIHospTransInfostatisticsTODDto timeOfDay = StringUtils.isEmpty(dto.getTimeOfDayStatistics())?null:dto.getTimeOfDayStatistics();

				todTotal = timeOfDay.getMorning() + timeOfDay.getAfterNoon() + timeOfDay.getEvening() + timeOfDay.getNight()+todTotal;
				morning = timeOfDay.getMorning()+morning;
				afterNoon = timeOfDay.getAfterNoon()+afterNoon;
				evening = timeOfDay.getEvening()+evening;
				night = timeOfDay.getNight()+night;

				dowTotal = dayOfWeekDto.getSunday() + dayOfWeekDto.getMonday() + dayOfWeekDto.getTuesday() + dayOfWeekDto.getWednesday() + dayOfWeekDto.getThursday() + dayOfWeekDto.getFriday() + dayOfWeekDto.getSaturday() +dowTotal;
				sunday = dayOfWeekDto.getSunday() + sunday;
				monday = dayOfWeekDto.getMonday() + monday;
				tuesday = dayOfWeekDto.getTuesday() + tuesday;
				wednesday = dayOfWeekDto.getWednesday() + wednesday;
				thursday = dayOfWeekDto.getThursday() + thursday;
				friday = dayOfWeekDto.getFriday() + friday;
				saturday = dayOfWeekDto.getSaturday() + saturday;

			}
			}catch(Exception e){
				LOG.error(e.getMessage(), e);
			}
			QIHospTrnsInfoDetailsDto TimeOfDayDetails = new QIHospTrnsInfoDetailsDto();

			List<QIHospTrnsInfoDto> TimeOfDayinfoList = new ArrayList<QIHospTrnsInfoDto>();

			QIHospTrnsInfoDto morningInfo=new QIHospTrnsInfoDto();
			morningInfo.setName("Morning (7am - noon)");
			morningInfo.setCout(morning);
			morningInfo.setPercent(morning == 0 ? 0 : Math.round((morning.floatValue()/todTotal)*100));
			TimeOfDayinfoList.add(morningInfo);

			QIHospTrnsInfoDto afterNoonInfo=new QIHospTrnsInfoDto();
			afterNoonInfo.setName("AfterNoon (noon - 7pm)");
			afterNoonInfo.setCout(afterNoon);
			afterNoonInfo.setPercent(afterNoon == 0 ? 0 : Math.round((afterNoon.floatValue()/todTotal)*100));
			TimeOfDayinfoList.add(afterNoonInfo);

			QIHospTrnsInfoDto eveningInfo=new QIHospTrnsInfoDto();
			eveningInfo.setName("Evening (7pm - midnight)");
			eveningInfo.setCout(evening);
			eveningInfo.setPercent(evening == 0 ? 0 : Math.round((evening.floatValue()/todTotal)*100));
			TimeOfDayinfoList.add(eveningInfo);

			QIHospTrnsInfoDto nightInfo=new QIHospTrnsInfoDto();
			nightInfo.setName("Night (midnight - 7am)");
			nightInfo.setCout(night);
			nightInfo.setPercent(night == 0 ? 0 : Math.round((night.floatValue()/todTotal)*100));
			TimeOfDayinfoList.add(nightInfo);

			TimeOfDayDetails.setInfo(TimeOfDayinfoList);
			TimeOfDayDetails.setTotal(todTotal);
			result.setTimeOfDay(TimeOfDayDetails);

			QIHospTrnsInfoDetailsDto dayOfWeekDetails = new QIHospTrnsInfoDetailsDto();

			List<QIHospTrnsInfoDto> dayOfWeekinfoList = new ArrayList<QIHospTrnsInfoDto>();

			QIHospTrnsInfoDto sundayInfo = new QIHospTrnsInfoDto();
			sundayInfo.setName("Sunday");
			sundayInfo.setCout(sunday);
			sundayInfo.setPercent(saturday == 0 ? 0 : Math.round((saturday.floatValue()/dowTotal)*100));
			dayOfWeekinfoList.add(sundayInfo);

			QIHospTrnsInfoDto mondayInfo = new QIHospTrnsInfoDto();
			mondayInfo.setName("Monday");
			mondayInfo.setCout(monday);
			mondayInfo.setPercent(monday == 0 ? 0 : Math.round((monday.floatValue()/dowTotal)*100));
			dayOfWeekinfoList.add(mondayInfo);

			QIHospTrnsInfoDto tuesdayInfo = new QIHospTrnsInfoDto();
			tuesdayInfo.setName("Tuesday");
			tuesdayInfo.setCout(tuesday);
			tuesdayInfo.setPercent(tuesday == 0 ? 0 : Math.round((tuesday.floatValue()/dowTotal)*100));
			dayOfWeekinfoList.add(tuesdayInfo);

			QIHospTrnsInfoDto wednesdayInfo = new QIHospTrnsInfoDto();
			wednesdayInfo.setName("Wednesday");
			wednesdayInfo.setCout(wednesday);
			wednesdayInfo.setPercent(wednesday == 0 ? 0 : Math.round((wednesday.floatValue()/dowTotal)*100));
			dayOfWeekinfoList.add(wednesdayInfo);

			QIHospTrnsInfoDto thursdayInfo = new QIHospTrnsInfoDto();
			thursdayInfo.setName("Thursday");
			thursdayInfo.setCout(thursday);
			thursdayInfo.setPercent(thursday == 0 ? 0 : Math.round((thursday.floatValue()/dowTotal)*100));
			dayOfWeekinfoList.add(thursdayInfo);

			QIHospTrnsInfoDto fridayInfo = new QIHospTrnsInfoDto();
			fridayInfo.setName("Friday");
			fridayInfo.setCout(friday);
			fridayInfo.setPercent(friday == 0 ? 0 : Math.round((friday.floatValue()/dowTotal)*100));
			dayOfWeekinfoList.add(fridayInfo);

			QIHospTrnsInfoDto saturdayInfo = new QIHospTrnsInfoDto();
			saturdayInfo.setName("Saturday");
			saturdayInfo.setCout(saturday);
			saturdayInfo.setPercent(saturday == 0 ? 0 : Math.round((saturday.floatValue()/dowTotal)*100));
			dayOfWeekinfoList.add(saturdayInfo);

			dayOfWeekDetails.setTotal(dowTotal);
			dayOfWeekDetails.setInfo(dayOfWeekinfoList);
			result.setDayOfWeek(dayOfWeekDetails);

			return result;
	}

	@Transactional
	public QIEvaluateNdManageCICDto getEvaluateNdManageChangeInConditionQIG(Long facilityId,String from,String to){	
		QIEvaluateNdManageCICDto evaluation = qiDao.getEvaluateNdManageChangeInConditionQIG(facilityId, from, to);
		List<UtilsDto> totTransfers = qiDao.getBetweenDatesofYearFromFDischarge(facilityId, from, to);
		if(!StringUtils.isEmpty(evaluation) && !CollectionUtils.isEmpty(totTransfers)) {
			evaluation.setTotalTransfers(Long.valueOf(totTransfers.size()));
		 }
		 return evaluation;
	}
	
	
	
	@Transactional
	public List<QIHospitalTransferInfoDto> getHospitalTranferPerntOfCurrentAndOtherFacilities(
			Long facilityId, String fromDate, String toDate) {
		if(StringUtils.isEmpty(fromDate) || StringUtils.isEmpty(toDate)){
			int year = Calendar.getInstance().get(Calendar.YEAR);
			fromDate=year+"-01-01";
			toDate=year+"-12-31";
			System.out.println(toDate);
		}
		
		
		List<QIHospitalTransferInfoDto> data1=qiDao.selectedData(facilityId,fromDate,toDate);
		System.out.println("i am in controller1");
//		System.out.println(data1.get(4).getMonthName());
//		System.out.println(data1.get(4).getCfPercentage());
//		System.out.println(data1.get(4).getOfPercentage());
		return data1;
		
	}
	


}

