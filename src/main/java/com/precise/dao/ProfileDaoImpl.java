package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.LinkedList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;

import com.precise.dbconnection.ConnectionDao;
import com.precise.model.Label;
import com.precise.model.Reply;
import com.precise.model.StudentBean;
import com.precise.model.UserProfile;

@Repository
public class ProfileDaoImpl extends ConnectionDao implements ProfileDao {

	@Autowired
	private JdbcTemplate jdbcTemplate;

	 public java.sql.Date dateFormatter(Date date) {
	 		String dateForMySql = "";
	 		java.sql.Date sqlDate = null;
	 		try {
	 			if (date == null) {
	 				dateForMySql = null;
	 			} else {
	 				SimpleDateFormat simpleDateFormat = new SimpleDateFormat(
	 						"yyyy-MM-dd");
	 				dateForMySql = simpleDateFormat.format(date);
	 				java.util.Date stringToDate = simpleDateFormat.parse(dateForMySql);
	 				sqlDate = new java.sql.Date(stringToDate.getTime());
	 			}
	 		} catch (Exception e) {
	 			e.printStackTrace();
	 		}
	 		return sqlDate;
	 	}
	public String submitUserData(UserProfile userProfile, int userId, int roleId) throws SQLException {
		System.out.println("roleId value in submit::"+roleId);
		String procedureCall = "";
		Connection connection = null;
		String returnValue = "success";
		System.out.println("usetId::"+userId);
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			boolean shareProfile;
			boolean lockByMantoreValue;
			procedureCall = "{call proc_userProfile(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "userProfileInsert");
			callableSt.setInt(2, userId);
			callableSt.setInt(3, Integer.parseInt(userProfile.getRollNumber()));
			callableSt.setString(4, userProfile.getFullName());
			callableSt.setString(5, userProfile.getCvName());
			System.out.println("mentore value::"+userProfile.getMentor());
			callableSt.setString(6, userProfile.getMentor());
			callableSt.setInt(7, Integer.parseInt(userProfile.getGender().replace(",", "")));
			String str_date = userProfile.getDateOfBirth().replace(",", "");
			System.out.println("str_date:::" + str_date);
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy");
			Date date = formatter.parse(str_date);
			java.sql.Date sqlTimeStamp = new java.sql.Date(date.getTime());
			System.out.println("date::"+sqlTimeStamp);
			callableSt.setDate(8, sqlTimeStamp);
			callableSt.setString(9, userProfile.getDomainRoom());
			callableSt.setString(10, userProfile.getPhoneEtn()==null?"":userProfile.getPhoneEtn());
			callableSt.setString(11, userProfile.getMobile());
			callableSt.setString(12, userProfile.getEmailAddress());
			callableSt.setString(13, userProfile.getAlternativeEmailid()==null?"":userProfile.getAlternativeEmailid());
			callableSt.setInt(14, userProfile.getUserProfilePkId());
			callableSt.setBoolean(15, true);
			System.out.println("return method valuessss::::"
					+ checkMethod(userProfile.getRollNumberLock(), userProfile.getRollNumberApprove()));
			callableSt.setString(16, checkMethod(userProfile.getRollNumberLock(), userProfile.getRollNumberApprove()));
			callableSt.setString(17, checkMethod(userProfile.getFullNameLock(), userProfile.getFullNameApprove()));
			callableSt.setString(18, checkMethod(userProfile.getCvNameLock(), userProfile.getCvNameApprove()));
			callableSt.setString(19, checkMethod(userProfile.getMentorLock(), userProfile.getMentorApprove()));
			callableSt.setString(20, checkMethod(userProfile.getGenderLock(), userProfile.getGenderApprove()));
			callableSt.setString(21,
					checkMethod(userProfile.getDateOfBirthLock(), userProfile.getDateOfBirthApprove()));
			callableSt.setString(22,
					checkMethod(userProfile.getDomainRoomLocked(), userProfile.getDomianRoomApproved()));
			callableSt.setString(23, checkMethod(userProfile.getPhoneExtLock(), userProfile.getPhoneExtApprove()));
			callableSt.setString(24, checkMethod(userProfile.getMobileLock(), userProfile.getMobileApprove()));
			callableSt.setString(25,
					checkMethod(userProfile.getEmailAddressLock(), userProfile.getEmailAddressApprove()));
			callableSt.setString(26,
					checkMethod(userProfile.getAlternativeEmailLock(), userProfile.getAlternativeEmailApprove()));
			callableSt.setInt(27, roleId);
			if(userProfile.getShareProfile().equals("yes")){
				shareProfile=true;
			}else{
				shareProfile=false;
			}
			callableSt.setBoolean(28,shareProfile);
			callableSt.setInt(29,0);
			System.out.println("profile mentore::"+userProfile.getLockProfileByMentor());
			if(userProfile.getLockProfileByMentor()!=null){
			if(userProfile.getLockProfileByMentor().equals("on")){
				System.out.println("inside condition:::");
				callableSt.setBoolean(30,true);
			}else{
				callableSt.setBoolean(30,false);
			}
			}else{
				callableSt.setBoolean(30,false);
			}
			
			
			callableSt.execute();

			procedureCall = "{call proc_10std(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStat10 = connection.prepareCall(procedureCall);
			callableStat10.setString(1, "insert");
			callableStat10.setInt(2, 0);
			callableStat10.setInt(3, userId);
			callableStat10.setString(4, userProfile.getCity());
			callableStat10.setString(5, userProfile.getState());
			callableStat10.setString(6, userProfile.getBoard());
			callableStat10.setString(7, userProfile.getSchool());
			callableStat10.setString(8, userProfile.getPercentage());
			callableStat10.setString(9, checkMethod(userProfile.getCity10thLock(), userProfile.getCity10thApprove()));
			callableStat10.setString(10,
					checkMethod(userProfile.getState10thLock(), userProfile.getState10thApprove()));
			callableStat10.setString(11,
					checkMethod(userProfile.getBoard10thLock(), userProfile.getBoard10thApprove()));
			callableStat10.setString(12,
					checkMethod(userProfile.getSchool10thLock(), userProfile.getSchool10thApprove()));
			callableStat10.setString(13,
					checkMethod(userProfile.getPercentage10thLock(), userProfile.getPercentage10thApprove()));
			callableStat10.setBoolean(14, true);
			callableStat10.setInt(15, roleId);
			callableStat10.execute();

			procedureCall = "{call proc_12th(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStat112th = connection.prepareCall(procedureCall);
			callableStat112th.setString(1, "insert");
			callableStat112th.setInt(2, 0);
			callableStat112th.setInt(3, userId);
			callableStat112th.setString(4, userProfile.getCityTwelve());
			callableStat112th.setString(5, userProfile.getStateTwelve());
			callableStat112th.setString(6, userProfile.getBoardTwelve());
			callableStat112th.setString(7, userProfile.getSchoolTwelve());
			callableStat112th.setString(8, userProfile.getPercenatageTwelve());
			callableStat112th.setString(9,
					checkMethod(userProfile.getCity12thLock(), userProfile.getCity12thApprove()));
			callableStat112th.setString(10,
					checkMethod(userProfile.getState12thLock(), userProfile.getState12thApprove()));
			callableStat112th.setString(11,
					checkMethod(userProfile.getBoard12thLock(), userProfile.getBoard12thApprove()));
			callableStat112th.setString(12,
					checkMethod(userProfile.getSchool12thLock(), userProfile.getSchool12thApprove()));
			callableStat112th.setString(13,
					checkMethod(userProfile.getPercentage12thLock(), userProfile.getPercentage12thApprove()));
			callableStat112th.setBoolean(14, true);
			callableStat112th.setInt(15, roleId);
			callableStat112th.execute();
			
			procedureCall = "{call proc_Graduate(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatGraduate = connection.prepareCall(procedureCall);
			callableStatGraduate.setString(1, "insert");
			callableStatGraduate.setInt(2, userId);
			callableStatGraduate.setInt(3, 0);
			callableStatGraduate.setString(4, userProfile.getGraduteDegree());
			callableStatGraduate.setString(5, userProfile.getDepartment());
			callableStatGraduate.setString(6, userProfile.getInstitute());
			callableStatGraduate.setString(7, userProfile.getAbbreviation());
			callableStatGraduate.setString(8, userProfile.getLocation());
			callableStatGraduate.setString(9, userProfile.getCgpa()==null?"":userProfile.getCgpa());
			callableStatGraduate.setString(10, userProfile.getCgpaScale()==null?"":userProfile.getCgpaScale());
			callableStatGraduate.setString(11, userProfile.getPercentageGraduate());
			callableStatGraduate.setBoolean(12, true);
			callableStatGraduate.setString(13,
					checkMethod(userProfile.getGraduateDregreeLock(), userProfile.getGraduateDregreeApprove()));
			callableStatGraduate.setString(14,
					checkMethod(userProfile.getGraduateDepartmentLock(), userProfile.getGraduateDepartmentApprove()));
			callableStatGraduate.setString(15,
					checkMethod(userProfile.getGraduatedInstituteLock(), userProfile.getGrdauateInstituteApprove()));
			callableStatGraduate.setString(16, checkMethod(userProfile.getGrdaduateAbbrivationLock(),
					userProfile.getGrdaduateAbbrivationApprove()));
			callableStatGraduate.setString(17,
					checkMethod(userProfile.getGraduateLocationLock(), userProfile.getGraduateLocationApprove()));
			callableStatGraduate.setString(18,
					checkMethod(userProfile.getGraduateCgpaLock(), userProfile.getGraduateCgpaApprove()));
			callableStatGraduate.setString(19,
					checkMethod(userProfile.getGraduatePercentageLock(), userProfile.getGraduatePercentageApprove()));
			callableStatGraduate.setString(20,
					checkMethod(userProfile.getGraduateCgpaScaleLock(), userProfile.getGraduateCgpaScaleApprove()));
			callableStatGraduate.setInt(21, roleId);
			callableStatGraduate.execute();

			if(userProfile.getPostgraduateDegree()!=null){
			procedureCall = "{call postGraduateDegree(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatPostGraduate = connection.prepareCall(procedureCall);
			callableStatPostGraduate.setString(1, "insert");
			callableStatPostGraduate.setInt(2, userId);
			callableStatPostGraduate.setInt(3, 0);
			callableStatPostGraduate.setString(4, userProfile.getPostgraduateDegree());
			callableStatPostGraduate.setString(5, userProfile.getPostgraduateDepartment());
			callableStatPostGraduate.setString(6, userProfile.getPostgraduateInstitute());
			callableStatPostGraduate.setString(7, userProfile.getPostgraduateAbbreviation());
			callableStatPostGraduate.setString(8, userProfile.getPostgraduateCGPA()==null?"":userProfile.getPostgraduateCGPA());
			callableStatPostGraduate.setString(9, userProfile.getPostgraduateCGPAScale()==null?"":userProfile.getPostgraduateCGPAScale());
			callableStatPostGraduate.setString(10, userProfile.getPostgraduatePercentage()==null?"":userProfile.getPostgraduatePercentage());
			callableStatPostGraduate.setBoolean(11, true);
			callableStatPostGraduate.setString(12,
					checkMethod(userProfile.getPgDegreeLock(), userProfile.getPgDegreeApprove()));
			callableStatPostGraduate.setString(13,
					checkMethod(userProfile.getPgDepartmentLock(), userProfile.getPgDepartmentApprove()));
			callableStatPostGraduate.setString(14,
					checkMethod(userProfile.getPgInstituteLock(), userProfile.getPgInstituteApprove()));
			callableStatPostGraduate.setString(15,
					checkMethod(userProfile.getPgAbbrivationLock(), userProfile.getPgAbbrivationApprove()));
			callableStatPostGraduate.setString(16, null);
			callableStatPostGraduate.setString(17,
					checkMethod(userProfile.getPgCgpaLock(), userProfile.getPgCgpaApprove()));
			callableStatPostGraduate.setString(18,
					checkMethod(userProfile.getPgPercentageLock(), userProfile.getPgPercentageApprove()));
			callableStatPostGraduate.setString(19,
					checkMethod(userProfile.getPgcgpaScaleLock(), userProfile.getPgcgpaScaleApprove()));
			callableStatPostGraduate.setInt(20, roleId);
			callableStatPostGraduate.execute();
			}

			if(userProfile.getCompany() !=null && userProfile.getCompany().size()>0){
			CallableStatement summerInternShip = connection
					.prepareCall("{call proc_SummerInternship(?,?,?,?,?,?,?,?,?)}");
			CallableStatement summerInternShipUpdate = connection
					.prepareCall("{call proc_SummerInternship(?,?,?,?,?,?,?,?,?)}");
			List companyName = userProfile.getCompany();
			System.out.println("summerInternship size::" + companyName.size());
			for (int i = 0; i < companyName.size(); i++) {
				if (userProfile.getSummerInternshipPkId().get(i).equals("0")) {
					summerInternShip.setString(1, "insert");
					summerInternShip.setInt(2, userId);
					summerInternShip.setInt(3, 0);
					summerInternShip.setString(4, (String) userProfile.getCompany().get(i));
					summerInternShip.setString(5, (String) userProfile.getRole().get(i));
					summerInternShip.setString(6, (String) userProfile.getLocationSummerInternship().get(i));
					summerInternShip.setString(7, (String) userProfile.getDurationSummerInternship().get(i));
					summerInternShip.setBoolean(8, true);
					summerInternShip.setString(9, null);
					summerInternShip.addBatch();
				} else {
					System.out.println("inside else condition::::::::::::::::::::::::::::::::::::::::::::::::::"+userProfile.getSummerIdHidden());
					String summerLock = (userProfile.getSummerIdHidden().get(i)).equals("0") ? null
							: userProfile.getSummerIdHidden().get(i);
					System.out.println("lock values::::::::"+summerLock);
					String summerApprove = (userProfile.getSummerApproveId().get(i)).equals("0") ? null
							: userProfile.getSummerApproveId().get(i);
					summerInternShipUpdate.setString(1, "update");
					summerInternShipUpdate.setInt(2, userId);
					summerInternShipUpdate.setInt(3, Integer.parseInt(userProfile.getSummerInternshipPkId().get(i)));
					summerInternShipUpdate.setString(4, (String) userProfile.getCompany().get(i));
					summerInternShipUpdate.setString(5, (String) userProfile.getRole().get(i));
					summerInternShipUpdate.setString(6, (String) userProfile.getLocationSummerInternship().get(i));
					summerInternShipUpdate.setString(7, (String) userProfile.getDurationSummerInternship().get(i));
					summerInternShipUpdate.setBoolean(8, true);
					summerInternShipUpdate.setString(9, checkMethod(summerLock, summerApprove));
					summerInternShipUpdate.execute();
				}

			}
			int[] val = summerInternShip.executeBatch();
			}

			List OtherInternship = userProfile.getOtherInternShipCompany();
			CallableStatement otherInternShip = connection.prepareCall("{call proc_otherInternShip(?,?,?,?,?,?,?,?)}");
			CallableStatement otherInternShipUpdate = connection
					.prepareCall("{call proc_otherInternShip(?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < OtherInternship.size(); i++) {

				if (userProfile.getOtherInternshipPkId().get(i).equals("0")) {
					otherInternShip.setString(1, "insert");
					otherInternShip.setInt(2, userId);
					otherInternShip.setInt(3, 0);
					otherInternShip.setString(4, (String) userProfile.getOtherInternShipCompany().get(i));
					otherInternShip.setString(5, (String) userProfile.getOtherInternShipDuration().get(i));
					otherInternShip.setString(6, (String) userProfile.getOtherInternShipLocation().get(i));
					otherInternShip.setBoolean(7, true);
					otherInternShip.setString(8, null);
					otherInternShip.addBatch();
				} else {
					System.out.println("other internship lock values::"+userProfile.getOtherHiddenLock());
					String otherLock = (userProfile.getOtherHiddenLock().get(i)).equals("0") ? null
							: userProfile.getOtherHiddenLock().get(i);
					String otherApprove = (userProfile.getOtherHiddenApprove().get(i)).equals("0") ? null
							: userProfile.getOtherHiddenApprove().get(i);
					otherInternShipUpdate.setString(1, "update");
					otherInternShipUpdate.setInt(2, userId);
					otherInternShipUpdate.setInt(3, Integer.parseInt(userProfile.getOtherInternshipPkId().get(i)));
					otherInternShipUpdate.setString(4, (String) userProfile.getOtherInternShipCompany().get(i));
					otherInternShipUpdate.setString(5, (String) userProfile.getOtherInternShipDuration().get(i));
					otherInternShipUpdate.setString(6, (String) userProfile.getOtherInternShipLocation().get(i));
					otherInternShipUpdate.setBoolean(7, true);
					otherInternShipUpdate.setString(8, checkMethod(otherLock, otherApprove));
					otherInternShipUpdate.execute();
				}
			}
			otherInternShip.executeBatch();

			CallableStatement workExperience = connection
					.prepareCall("{call proc_workExperience(?,?,?,?,?,?,?,?,?,?,?)}");
			CallableStatement workExperienceUpdate = connection
					.prepareCall("{call proc_workExperience(?,?,?,?,?,?,?,?,?,?,?)}");
			for (int i = 0; i < userProfile.getCompanyNameWorkExperience().size(); i++) {
				if (userProfile.getWorkExperiencePkId().get(i).equals("0")) {
					workExperience.setString(1, "insert");
					workExperience.setInt(2, userId);
					workExperience.setInt(3, 0);
					workExperience.setString(4, (String) userProfile.getCompanyNameWorkExperience().get(i));
					workExperience.setString(5, (String) userProfile.getDesignationWorkExperience().get(i));
					workExperience.setString(6, (String) userProfile.getIndustryWorkExperience().get(i));
					workExperience.setString(7, (String) userProfile.getFunctionAreaWorkExperience().get(i));
					/*workExperience.setString(8, (String) userProfile.getTotalWorkMonthsWorkExperience().get(i));*/
					workExperience.setString(8, (String) userProfile.getDurationWorkExperience().get(i));
					workExperience.setString(9, (String) userProfile.getDurationWorkExperience().get(i));
					workExperience.setBoolean(10, true);
					workExperience.setString(11, null);
					workExperience.addBatch();
				} else {
					String workLock = (userProfile.getWorkHiddenLock().get(i)).equals("0") ? null
							: userProfile.getWorkHiddenLock().get(i);
					String workApprove = (userProfile.getWorkHiddenApprove().get(i)).equals("0") ? null
							: userProfile.getWorkHiddenApprove().get(i);
					workExperienceUpdate.setString(1, "update");
					workExperienceUpdate.setInt(2, userId);
					workExperienceUpdate.setInt(3, Integer.parseInt(userProfile.getWorkExperiencePkId().get(i)));
					workExperienceUpdate.setString(4, (String) userProfile.getCompanyNameWorkExperience().get(i));
					workExperienceUpdate.setString(5, (String) userProfile.getDesignationWorkExperience().get(i));
					workExperienceUpdate.setString(6, (String) userProfile.getIndustryWorkExperience().get(i));
					workExperienceUpdate.setString(7, (String) userProfile.getFunctionAreaWorkExperience().get(i));
					//workExperienceUpdate.setString(8, (String) userProfile.getTotalWorkMonthsWorkExperience().get(i));
					workExperienceUpdate.setString(8, (String) userProfile.getDurationWorkExperience().get(i));
					workExperienceUpdate.setString(9, (String) userProfile.getDurationWorkExperience().get(i));
					workExperienceUpdate.setBoolean(10, true);
					workExperienceUpdate.setString(11, checkMethod(workLock, workApprove));
					workExperienceUpdate.execute();
				}
			}
			workExperience.executeBatch();

			System.out.println("value::::::::::::::::"+userProfile.getCvFile());
			CallableStatement uploadCv = connection.prepareCall("{call Proc_cvFile(?,?,?,?,?,?,?,?,?)}");
			CallableStatement uploadCvUpdate = connection.prepareCall("{call Proc_cvFile(?,?,?,?,?,?,?,?,?)}");
			System.out.println("size:::"+userProfile.getCvFile().size());
			for (int i = 0; i < userProfile.getCvFile().size(); i++) {
				System.out.println("cvTititle:::"+userProfile.getCvTitle().get(i));
				System.out.println(!userProfile.getCvTitle().toString().equalsIgnoreCase("") || userProfile.getCvTitle().get(i)!=null);
				if(!userProfile.getCvTitle().get(i).isEmpty()){
				System.out.println("userProfile.getCvPkId().get(i)::"+userProfile.getCvPkId().get(i));
				if (userProfile.getCvPkId().get(i).equals("0")) {
					System.out.println("in if");
					uploadCv.setString(1, "insert");
					uploadCv.setInt(2, 0);
					uploadCv.setInt(3, userId);
					uploadCv.setBytes(4, userProfile.getCvFile().get(i).getBytes());
					uploadCv.setString(5, userProfile.getCvFile().get(i).getOriginalFilename());
					uploadCv.setBoolean(6, true);
					if(userProfile.getCvTitle().size()>0){
						uploadCv.setString(7, userProfile.getCvTitle().get(i));
					}else{
						uploadCv.setString(7, null);
					}
					if(userProfile.getCvFilePathList().size()>0){
						uploadCv.setString(8, userProfile.getCvFilePathList().get(i));
					}else{
						uploadCv.setString(8, null);
					}
					
					uploadCv.setString(9, "0");
					uploadCv.addBatch();
				} else {
					System.out.println("inside else condition::");
					System.out.println("cvFilePath:::"+userProfile.getCvFilePathList().get(i));
				//	System.out.println("cv pk id::"+userProfile.getCvPkId().get(i));
					String cvkLock = (userProfile.getCvLockHidden().get(i)).equals("0") ? null
							: userProfile.getCvLockHidden().get(i);
					String cvApprove = (userProfile.getCvApproveHidden().get(i)).equals("0") ? null
							: userProfile.getCvApproveHidden().get(i);
				
						System.out.println("inside if condition::::"+userProfile.getCvPkId().get(i));
						uploadCvUpdate.setString(1, "update");
						uploadCvUpdate.setInt(2, Integer.parseInt(userProfile.getCvPkId().get(i)));
						uploadCvUpdate.setInt(3, userId);
						uploadCvUpdate.setBytes(4, userProfile.getCvFile().get(i).getBytes());
						uploadCvUpdate.setString(5, userProfile.getCvFile().get(i).getOriginalFilename());
						uploadCvUpdate.setBoolean(6, true);
						if( userProfile.getCvTitle().size()>0){
							uploadCvUpdate.setString(7, userProfile.getCvTitle().get(i));
						}else{
							uploadCvUpdate.setString(7, null);
						}
						if(userProfile.getCvFilePathList().size()>0){
							uploadCvUpdate.setString(8, userProfile.getCvFilePathList().get(i)==null?null:userProfile.getCvFilePathList().get(i));
						}else{
							uploadCvUpdate.setString(8, null);
						}
						
						
						System.out.println("method retuen value::" + checkMethod(cvkLock, cvApprove));
						uploadCvUpdate.setString(9, checkMethod(cvkLock, cvApprove));
						uploadCvUpdate.addBatch();
					
				}
			}
			}
			uploadCv.executeBatch();
			uploadCvUpdate.executeBatch();

			System.out.println("Resume lock::" + userProfile.getResumeLock());
			System.out.println("Resume approve::" + userProfile.getResmueApprove());
			
			procedureCall = "{call proc_resume(?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStatResume = connection.prepareCall(procedureCall);
			callableStatResume.setString(1, "insert");
			callableStatResume.setInt(2, 0);
			callableStatResume.setInt(3, userId);
			callableStatResume.setString(4, userProfile.getProfileWriteUp());
			callableStatResume.setBytes(5, userProfile.getDaWriteUp().getBytes());
			callableStatResume.setString(6, userProfile.getProfileLoack());
			callableStatResume.setBoolean(7, true);
			System.out.println(userProfile.getResumeLock()+"dfgdfgfdgdfgdfgdgdfgdf"+ userProfile.getResmueApprove());
			callableStatResume.setString(8, checkMethod(userProfile.getResumeLock(), userProfile.getResmueApprove()));
			callableStatResume.setInt(9, roleId);
			callableStatResume.setBytes(10, userProfile.getPhotoUpload().getBytes());
			callableStatResume.setString(11, userProfile.getPhotoUpload().getOriginalFilename());
			System.out.println("file values:::"+userProfile.getPhotoUpload().getBytes());
			System.out.println("photo upload file name::"+userProfile.getPhotoUpload().getOriginalFilename());
			System.out.println("userProfile.getPhototUploadFilePath()"+userProfile.getPhototUploadFilePath());
			System.out.println("da write up file path::"+userProfile.getDaWriteUpFilePath());
			callableStatResume.setString(12, userProfile.getPhototUploadFilePath());
			System.out.println("dawriteup original file name::"+userProfile.getDaWriteUp().getOriginalFilename());
			callableStatResume.setString(13, userProfile.getDaWriteUp().getOriginalFilename());
			callableStatResume.setString(14, userProfile.getDaWriteUpFilePath());
			callableStatResume.execute();
			connection.commit();
		}
 catch (Exception e) {
			returnValue = "false";
			e.printStackTrace();
			connection.rollback();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return returnValue;

	}

	public UserProfile getUsersDetails(int userId, UserProfile userProfile) {
		Connection connection;
		String procedureCall = "";
		boolean adminLock=false;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();

			procedureCall = "{call proc_userProfile(?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "select");
			callableSt.setInt(2, userId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				userProfile.setRollNumber(rs.getString("RollNumber"));
				userProfile.setFullName(rs.getString("Name"));
				userProfile.setCvName(rs.getString("CVName"));
				userProfile.setMentor(rs.getString("MentorName"));
				userProfile.setGender(rs.getString("Gender"));
				System.out.println("date of birth values from sq" + rs.getDate("DateofBirth"));
				SimpleDateFormat sdfr = new SimpleDateFormat("yyyy-MM-dd");
				
				String dateString = sdfr.format(rs.getDate("DateofBirth"));
			//	System.out.println("dateString "+dateString);
				dateString = dateString.replaceAll("-","");
			//	System.out.println("dateString "+dateString);
				//dateString = dateString.substring(4, 6)+"-"+dateString.substring(6,8)+"-"+dateString.substring(0,4);
				dateString = dateString.substring(6,8)+"-"+dateString.substring(4,6)+"-"+dateString.substring(0,4);
				System.out.println("dateString "+dateString);
				userProfile.setDateOfBirth(dateString);
				userProfile.setDomainRoom(rs.getString("DormandRoom"));
				userProfile.setPhoneEtn(rs.getString("PhoneExtn"));
				userProfile.setMobile(rs.getString("Mobile"));
				userProfile.setEmailAddress(rs.getString("EmailID"));
				userProfile.setAlternativeEmailid(rs.getString("AlternativeEmailID"));
				userProfile.setUserId(rs.getString("Fk_IIMStdId"));
				userProfile.setUserProfilePkId(rs.getInt("Pk_UserProfileid"));
				userProfile.setRollNumberApprove(rs.getString("RollNumberStatus"));
				userProfile.setFullNameApprove(rs.getString("NameStatus"));
				userProfile.setCvNameApprove(rs.getString("CVNameStatus"));
				userProfile.setMentorApprove(rs.getString("MentorNameStatus"));
				userProfile.setGenderApprove(rs.getString("GenderStatus"));
				userProfile.setDateOfBirthApprove(rs.getString("DateofBirthStatus"));
				userProfile.setDomianRoomApproved(
						rs.getString("DormandRoomStatus") == null ? "0" : rs.getString("DormandRoomStatus"));
				userProfile.setPhoneExtApprove(rs.getString("PhoneExtnStatus")=="0"?"":(rs.getString("PhoneExtnStatus")));
				userProfile.setMobileApprove(rs.getString("MobileStatus"));
				userProfile.setEmailAddressApprove(
						rs.getString("EmailIDStatus") == null ? "0" : rs.getString("EmailIDStatus"));
				System.out.println("alternative email add::"+rs.getString("AlternativeEmailIDStatus"));
				userProfile.setAlternativeEmailApprove(rs.getString("AlternativeEmailIDStatus")=="0"?"":rs.getString("AlternativeEmailIDStatus"));
				System.out.println("share profile value:::::"+rs.getBoolean("ShareProfile"));
				if(rs.getBoolean("ShareProfile")==true){
					userProfile.setShareProfile("yes");
				}else{
					userProfile.setShareProfile("no");
				}
				adminLock=rs.getBoolean("AdminLock");
				System.out.println("mentotrelockselectValue::"+rs.getBoolean("MentorProfileLock"));
				if(rs.getBoolean("MentorProfileLock")==true){
					System.out.println("inside if cindition::");
					userProfile.setLockProfileByMentor("1");
				}else{
					System.out.println("inside else cindition::");
					userProfile.setLockProfileByMentor("0");
				}
			}
			
			if(adminLock==true){
				userProfile.setProfileLoack("Locked");
			}else{
				userProfile.setProfileLoack("Unlocked");
			}
			

			CallableStatement callableSt10th = connection.prepareCall("{call proc_10std(?,?,?)}");
			callableSt10th.setString(1, "select");
			callableSt10th.setInt(2, 0);
			callableSt10th.setInt(3, userId);
			ResultSet rs10th = callableSt10th.executeQuery();
			while (rs10th.next()) {
				userProfile.setCity(rs10th.getString("City"));
				System.out.println("city" + rs10th.getString("City"));
				userProfile.setState(rs10th.getString("State"));
				userProfile.setBoard(rs10th.getString("Board"));
				userProfile.setSchool(rs10th.getString("School"));
				userProfile.setPercentage(rs10th.getString("Percentage"));
				userProfile.setCity10thApprove(rs10th.getString("CityStatus"));
				userProfile.setState10thApprove(rs10th.getString("StateStatus"));
				userProfile.setBoard10thApprove(rs10th.getString("BoardStatus"));
				userProfile.setSchool10thApprove(rs10th.getString("SchoolStatus"));
				userProfile.setPercentage10thApprove(rs10th.getString("PercentageStatus"));
			}

			CallableStatement callableSt12 = connection.prepareCall("{call proc_12th(?,?,?)}");
			callableSt12.setString(1, "select");
			callableSt12.setInt(2, 0);
			callableSt12.setInt(3, userId);
			ResultSet rs12 = callableSt12.executeQuery();
			while (rs12.next()) {
				userProfile.setCityTwelve(rs12.getString("City"));
				userProfile.setStateTwelve(rs12.getString("State"));
				userProfile.setBoardTwelve(rs12.getString("Board"));
				userProfile.setSchoolTwelve(rs12.getString("School"));
				userProfile.setPercenatageTwelve(rs12.getString("Percentage"));
				userProfile.setCity12thApprove(rs12.getString("CityStatus"));
				userProfile.setState12thApprove(rs12.getString("StateStatus"));
				userProfile.setBoard12thApprove(rs12.getString("BoardStatus"));
				userProfile.setSchool12thApprove(rs12.getString("SchoolStatus"));
				userProfile.setPercentage12thApprove(rs12.getString("PercentageStatus"));
			}

			CallableStatement callableStGraduate = connection.prepareCall("{call proc_Graduate(?,?,?)}");
			callableStGraduate.setString(1, "select");
			callableStGraduate.setInt(2, userId);
			callableStGraduate.setInt(3, 0);
			ResultSet rsGraduate = callableStGraduate.executeQuery();
			while (rsGraduate.next()) {
				userProfile.setGraduteDegree(rsGraduate.getString("Degree"));
				userProfile.setDepartment(rsGraduate.getString("Department"));
				userProfile.setInstitute(rsGraduate.getString("Institute"));
				userProfile.setAbbreviation(rsGraduate.getString("Abbreviation"));
				System.out.println("abberi-select" + userProfile.getAbbreviation());
				userProfile.setLocation(rsGraduate.getString("Location"));
				userProfile.setCgpa(rsGraduate.getString("CGPA"));
				userProfile.setCgpaScale(rsGraduate.getString("CGPAScale"));
				userProfile.setPercentageGraduate(rsGraduate.getString("Percentage"));
				userProfile.setGraduateDregreeApprove(rsGraduate.getString("DegreeStatus"));
				userProfile.setGraduateDepartmentApprove(rsGraduate.getString("DepartmentStatus"));
				userProfile.setGrdauateInstituteApprove(rsGraduate.getString("InstituteStatus"));
				userProfile.setGrdaduateAbbrivationApprove(rsGraduate.getString("AbbreviationStatus"));
				userProfile.setGraduateLocationApprove(rsGraduate.getString("LocationStatus"));
				userProfile.setGraduateCgpaApprove(rsGraduate.getString("CGPAStatus"));
				userProfile.setGraduatePercentageApprove(rsGraduate.getString("PercentageStatus"));
				userProfile.setGraduateCgpaScaleApprove(rsGraduate.getString("CGPAScaleStatus"));
			}

			CallableStatement callableStPostGraduate = connection.prepareCall("{call postGraduateDegree(?,?,?)}");
			callableStPostGraduate.setString(1, "select");
			callableStPostGraduate.setInt(2, userId);
			System.out.println("user id " + userId);
			callableStPostGraduate.setInt(3, 0);
			ResultSet rsPostGraduate = callableStPostGraduate.executeQuery();
			System.out.println("resultset" + rsPostGraduate);
			while (rsPostGraduate.next()) {
				userProfile.setPostgraduateDegree(rsPostGraduate.getString("Degree"));
				userProfile.setPostgraduateDepartment(rsPostGraduate.getString("Department"));
				userProfile.setPostgraduateInstitute(rsPostGraduate.getString("Institute"));
				userProfile.setPostgraduateAbbreviation(rsPostGraduate.getString("Abbreviation"));
				System.out.println("psdst-select" + userProfile.getAbbreviation());
				userProfile.setPostgraduateCGPA(rsPostGraduate.getString("CGPA"));
				userProfile.setPostgraduateCGPAScale(rsPostGraduate.getString("CGPAScale"));
				userProfile.setPostgraduatePercentage(rsPostGraduate.getString("Percentage"));
				userProfile.setPgDegreeApprove(rsPostGraduate.getString("DegreeStatus"));
				userProfile.setPgDepartmentApprove(rsPostGraduate.getString("DepartmentStatus"));
				userProfile.setPgInstituteApprove(rsPostGraduate.getString("InstituteStatus"));
				userProfile.setPgAbbrivationApprove(rsPostGraduate.getString("AbbreviationStatus"));
				userProfile.setPgCgpaApprove(rsPostGraduate.getString("CGPAStatus"));
				userProfile.setPgPercentageApprove(rsPostGraduate.getString("PercentageStatus"));
				userProfile.setPgcgpaScaleApprove(rsPostGraduate.getString("CGPAScaleStatus"));
			}

			rs = null;
			CallableStatement summerInernship = connection.prepareCall("{call proc_SummerInternship(?,?)}");
			summerInernship.setString(1, "select");
			summerInernship.setInt(2, userId);
			rs = summerInernship.executeQuery();
			List<String> company = new LinkedList<String>();
			List<String> role = new LinkedList<String>();
			List<String> location = new LinkedList<String>();
			List<String> Duration = new LinkedList<String>();
			List<String> summerInternshipPkId = new LinkedList<String>();
			List<String> summerInternshipstatus = new LinkedList<String>();
			System.out.println("before rs:::");
			while (rs.next()) {
				company.add(rs.getString("Company"));
				role.add(rs.getString("Role"));
				location.add(rs.getString("Location"));
				Duration.add(rs.getString("Duration"));
				summerInternshipPkId.add(rs.getString("Pk_SInternshipId"));
				summerInternshipstatus.add(rs.getString("Status"));
			}
			userProfile.setCompany(company);
			userProfile.setRole(role);
			userProfile.setLocationSummerInternship(location);
			userProfile.setDurationSummerInternship(Duration);
			userProfile.setSummerInternshipPkId(summerInternshipPkId);
			userProfile.setSummerApproveId(summerInternshipstatus);

			rs = null;
			CallableStatement otherInernship = connection.prepareCall("{call proc_otherInternShip(?,?)}");
			otherInernship.setString(1, "select");
			otherInernship.setInt(2, userId);
			rs = otherInernship.executeQuery();
			List<String> otherCompany = new LinkedList<String>();
			List<String> otherDuration = new LinkedList<String>();
			List<String> otherLocation = new LinkedList<String>();
			List<String> otherInternPkId = new LinkedList<String>();
			List<String> otherInternStatus = new LinkedList<String>();
			while (rs.next()) {
				otherCompany.add(rs.getString("ComapnyName"));
				otherDuration.add(rs.getString("Duration"));
				otherLocation.add(rs.getString("Location"));
				otherInternPkId.add(rs.getString("Pk_InternshipId"));
				otherInternStatus.add(rs.getString("Status"));
			}
			userProfile.setOtherInternShipCompany(otherCompany);
			userProfile.setOtherInternShipDuration(otherDuration);
			userProfile.setOtherInternShipLocation(otherLocation);
			userProfile.setOtherInternshipPkId(otherInternPkId);
			userProfile.setOtherHiddenApprove(otherInternStatus);

			rs = null;
			int totalExp=0;
			CallableStatement workExperience = connection.prepareCall("{call proc_workExperience(?,?)}");
			workExperience.setString(1, "select");
			workExperience.setInt(2, userId);
			rs = workExperience.executeQuery();
			List<String> workExperienceCompany = new LinkedList<String>();
			List<String> workDesignation = new LinkedList<String>();
			List<String> workDuration = new LinkedList<String>();
			List<String> workIndustry = new LinkedList<String>();
			List<String> workFunctionalArea = new LinkedList<String>();
			List<String> totalWork = new LinkedList<String>();
			List<String> workExperinecePk = new LinkedList<String>();
			List<String> workExperienceApprove = new LinkedList<String>();
			while (rs.next()) {
				totalExp=totalExp+rs.getInt("Duration");
				workExperienceCompany.add(rs.getString("CompanyName"));
				workDesignation.add(rs.getString("Designation"));
				workDuration.add(rs.getString("Duration"));
				workIndustry.add(rs.getString("Industry"));
				workFunctionalArea.add(rs.getString("FunctionArea"));
				totalWork.add(rs.getString("TotalWork"));
				workExperinecePk.add(rs.getString("Pk_ExperienceId"));
				workExperienceApprove.add(rs.getString("Status"));
			}
			
			userProfile.setTotalWorkExperience(totalExp);

			userProfile.setCompanyNameWorkExperience(workExperienceCompany);
			userProfile.setDesignationWorkExperience(workDesignation);
			userProfile.setDurationWorkExperience(workDuration);
			userProfile.setIndustryWorkExperience(workIndustry);
			userProfile.setFunctionAreaWorkExperience(workFunctionalArea);
			userProfile.setTotalWorkMonthsWorkExperience(totalWork);
			userProfile.setWorkExperiencePkId(workExperinecePk);
			userProfile.setWorkHiddenApprove(workExperienceApprove);

			rs = null;
			CallableStatement cvFile = connection.prepareCall("{call Proc_cvFile(?,?,?)}");
			cvFile.setString(1, "select");
			cvFile.setInt(2, 0);
			cvFile.setInt(3, userId);
			rs = cvFile.executeQuery();
			List<String> cvFileName = new LinkedList<String>();
			List<String> cvFileId = new LinkedList<String>();
			List<String> cvPkId = new LinkedList<String>();
			List<String> cvFileTitle = new LinkedList<String>();
			List<String> cvstatus = new LinkedList<String>();
			while (rs.next()) {
				cvFileName.add(rs.getString("fileName"));
				
				cvFileId.add(rs.getString("Pk_CvId"));
				cvFileTitle.add(rs.getString("Title"));
				cvstatus.add(rs.getString("Status"));
			}
			System.out.println("cv file id list size::" + cvFileId.size());
			userProfile.setCvFileName(cvFileName);
			userProfile.setCvFileId(cvFileId);
			userProfile.setCvPkId(cvPkId);
			userProfile.setCvTitle(cvFileTitle);
			userProfile.setCvApproveHidden(cvstatus);

			rs = null;
			CallableStatement resume = connection.prepareCall("{call proc_resume(?,?,?)}");
			resume.setString(1, "select");
			resume.setInt(2, 0);
			resume.setInt(3, userId);
			rs = resume.executeQuery();
			while (rs.next()) {
				userProfile.setProfileWriteUp(rs.getString("ProfileWriteup"));
				//userProfile.setDaWriteUp(rs.getString("DAWriteup"));
				//userProfile.setProfileLoack(rs.getString("ProfileLock"));
				userProfile.setResmueApprove(rs.getString("Status"));
				userProfile.setPhotoUploadFileName(rs.getString("photoUploadFileName"));
				userProfile.setPhotoUploadPkId(rs.getString("Pk_ResumeId"));
				userProfile.setDaWriteUpFileName(rs.getString("DAWriteupFileName"));
				userProfile.setDaWriteUpFilePath(rs.getString("DAWriteupFilePath"));
			}
		} catch (Exception e) {
			System.out.println("Exception in getUsersDetails method ::" + e.getMessage());
			e.printStackTrace();
		}
		return userProfile;

	}

	/*public byte[] getByteData(int pkId) {
		Connection connection;
		byte[] bytes = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cvFile = connection.prepareCall("{call Proc_cvFile(?,?)}");
			cvFile.setString(1, "selectFiledata");
			cvFile.setInt(2, pkId);
			ResultSet rs = cvFile.executeQuery();
			while (rs.next()) {
				bytes = rs.getBytes("CvFile");
				System.out.println(bytes);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}*/

	public String checkMethod(String lockStatus, String approveStatus) {
		String returnValue = "";
		if ((approveStatus == null || approveStatus.isEmpty()) && (lockStatus == null || lockStatus.isEmpty())) {
			returnValue = "0";
		} else {
			if (approveStatus == null || approveStatus.isEmpty()) {
				if (lockStatus.equals("1")) {
					returnValue = "1";
				}
			} else {
				if (approveStatus.equals("2") && lockStatus == null) {
					returnValue = "2";
				} else if (approveStatus.equals("2") && lockStatus.equals("1")) {
					returnValue = "1";
				}
			}

		}
		return returnValue;
	}

	public List<StudentBean> getStudentList(int userId) {
		System.out.println("Mentor Id::::" + userId);
		List<StudentBean> listBean = new ArrayList<StudentBean>();
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cvFile = connection.prepareCall("{call Sp_StdMentor(?)}");
			cvFile.setInt(1, userId);
			ResultSet rs = cvFile.executeQuery();
			while (rs.next()) {
				StudentBean bean = new StudentBean();
				bean.setMentorId(rs.getString("Fk_MentorId"));
				bean.setStudentId(rs.getString("Fk_StudentId"));
				bean.setStudentName(rs.getString("StudentName"));
				listBean.add(bean);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println("List value::" + listBean);
		return listBean;
	}

	public byte[] getPhotoUploadData(int pkId) {
		byte[] bytes = null;
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement photoUploadFile = connection.prepareCall("{call [proc_resume](?,?)}");
			photoUploadFile.setString(1, "selectPhotoUploadData");
			photoUploadFile.setInt(2, pkId);
			ResultSet rs = photoUploadFile.executeQuery();
			while (rs.next()) {
				bytes = rs.getBytes("photoUploadFile");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
	
	public String getUserName(int userId){
		String userName="";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement photoUploadFile = connection.prepareCall("{call [proc_userProfile](?,?)}");
			photoUploadFile.setString(1, "selectUserName");
			photoUploadFile.setInt(2, userId);
			ResultSet rs = photoUploadFile.executeQuery();
			if (rs.next()) {
				userName=rs.getString("username").replace("", "_");
				
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return userName;
	}
	
	public String getExperineceValues(int userId){
		String intershipExp="",interviewExp="";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement photoUploadFile = connection.prepareCall("{call [proc_IIMStudent](?,?,?,?)}");
			photoUploadFile.setString(1, "getExperienceValue");
			photoUploadFile.setInt(2, 0);
			photoUploadFile.setInt(3, 0);
			photoUploadFile.setInt(4, userId);
			ResultSet rs = photoUploadFile.executeQuery();
			if (rs.next()) {
			System.out.println("get internship excpe::"+rs.getBoolean("internshipExperience"));
			System.out.println("get internship excpe::"+rs.getBoolean("interviewExperience"));
			if(rs.getBoolean("internshipExperience")==true){
				intershipExp="1";
			}else{
				intershipExp="0";
			}
			
			if(rs.getBoolean("interviewExperience")==true){
				interviewExp="1";
			}else{
				interviewExp="0";
			}
		}
		}catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return intershipExp+"@@"+interviewExp;
	}
	
	public String deleteSummerInternship(String pkId){
		System.out.println("pkId::"+pkId);
		String returnValue="";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement photoUploadFile = connection.prepareCall("{call [proc_SummerInternship](?,?,?)}");
			photoUploadFile.setString(1, "delete");
			photoUploadFile.setInt(2, 0);
			photoUploadFile.setInt(3, Integer.parseInt(pkId));
			photoUploadFile.execute();
			returnValue="succcess";
		}catch (SQLException e) {
			returnValue="failure";
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public String deleteOtherInternshipData(String pkId){
		System.out.println("pkId::"+pkId);
		String returnValue="";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement photoUploadFile = connection.prepareCall("{call [proc_otherInternShip](?,?,?)}");
			photoUploadFile.setString(1, "delete");
			photoUploadFile.setInt(2, 0);
			photoUploadFile.setInt(3, Integer.parseInt(pkId));
			photoUploadFile.execute();
			returnValue="succcess";
		}catch (SQLException e) {
			returnValue="failure";
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public String deleteWorkExperience(String pkId){
		System.out.println("pkId::"+pkId);
		String returnValue="";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement photoUploadFile = connection.prepareCall("{call [proc_workExperience](?,?,?)}");
			photoUploadFile.setString(1, "delete");
			photoUploadFile.setInt(2, 0);
			photoUploadFile.setInt(3, Integer.parseInt(pkId));
			photoUploadFile.execute();
			returnValue="succcess";
		}catch (SQLException e) {
			returnValue="failure";
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public String deleteCvFile(String pkId){
		System.out.println("deleteCvFile pkId::"+pkId);
		String returnValue="";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement photoUploadFile = connection.prepareCall("{call [Proc_cvFile](?,?)}");
			photoUploadFile.setString(1, "delete");
			photoUploadFile.setInt(2, Integer.parseInt(pkId));
			photoUploadFile.execute();
			returnValue="succcess";
		}catch (SQLException e) {
			returnValue="failure";
			e.printStackTrace();
		}
		return returnValue;
	}
	
	public List<Label> getMenoreList(){
		List<Label> mentoreList=new ArrayList<Label>();
		final String procedureCall = "{call proc_mentor(?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectMentor");			
			ResultSet rs = callableSt.executeQuery();
			Label label=null;
			while (rs.next()) {	
				label=new Label();
				label.setLabelId(rs.getInt("FK_IIMStudent"));
				label.setLabelName(rs.getString("userName"));
				label.setEmailId(rs.getString("EmailId"));
				System.out.println("prefix in map-"+rs.getString("userName")+"id-  "+rs.getInt("FK_IIMStudent"));				
				mentoreList.add(label);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("mentore list::::"+mentoreList.toString());
		return mentoreList;
	}
	
	public String getMentoreName(int stuId){
		List<Label> mentoreList=new ArrayList<Label>();
		String mentorName=null;
		final String procedureCall = "{call proc_mentor(?,?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectMentorByStudent");
			callableSt.setInt(2,0);
			callableSt.setInt(3,stuId);
			
			ResultSet rs = callableSt.executeQuery();
		
			while (rs.next()) {	
				
				mentorName=rs.getString("userName");
				
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		System.out.println("mentore list::::"+mentoreList.toString());
		return mentorName;
	}
	
	
	public String getGrupValues(int studentId){
		String gropuId="";
		final String procedureCall = "{call proc_userProfile(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectgroupId");
			callableSt.setInt(2, studentId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {	
				gropuId=rs.getString("groupid");
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return gropuId;
	}
	
	public byte[] getDAWriteUp(int pkId){
		System.out.println("pkId::::"+pkId);
		Connection connection;
		byte[] bytes = null;
		try {
			 connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement photoUploadFile = connection.prepareCall("{call [proc_resume](?,?)}");
			photoUploadFile.setString(1, "selectDaWriteUp");
			photoUploadFile.setInt(2, pkId);
			ResultSet rs = photoUploadFile.executeQuery();
			while (rs.next()) {
				bytes = rs.getBytes("DAWriteup");
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}
	@Override
	public String getImageFile(int pkId) {
		Connection connection;
		String bytes = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement cvFile = connection.prepareCall("{call Proc_cvFile(?,?)}");
			cvFile.setString(1, "selectFiledata");
			cvFile.setInt(2, pkId);
			ResultSet rs = cvFile.executeQuery();
			while (rs.next()) {
				bytes = rs.getString("ResumePath");
				System.out.println("parth"+bytes);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return bytes;
	}

}
