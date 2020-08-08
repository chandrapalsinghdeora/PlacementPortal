package com.precise.dao;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.rowset.SqlRowSet;
import org.springframework.stereotype.Repository;
import org.springframework.web.multipart.MultipartFile;
import com.precise.model.Cluster;
import com.precise.model.Cohort;
import com.precise.model.CompanyMaster;
import com.precise.model.FirmManagementForm;
import com.precise.model.ProcessManagement;
import com.precise.model.RoleCompany;
import com.precise.model.SelectionRoundMaster;
import com.precise.model.YearMaster;
import com.sun.swing.internal.plaf.basic.resources.basic;

@Repository("firmMgt")
public class FirmManagementDAOImpl implements FirmManagementDAO {
	@Autowired
	private JdbcTemplate jdbcTemplate;

	public JdbcTemplate getJdbcTemplate() {
		return jdbcTemplate;
	}

	@Override
	public List<ProcessManagement> getAllProcess() {
		String sql = "SELECT * FROM tbl_mst_Process where [IsActive]=1";
		List<ProcessManagement> listProcess = jdbcTemplate.query(sql, new RowMapper<ProcessManagement>() {
			@Override
			public ProcessManagement mapRow(ResultSet rs, int rowNum) throws SQLException {
				ProcessManagement processManagement = new ProcessManagement();
				processManagement.setProcessId(rs.getInt("Pk_ProcessId"));
				processManagement.setProcessName(rs.getString("ProcessName"));
				return processManagement;
			}
		});
		return listProcess;
	}

	@Override
	public List<Cluster> getAllCluster() {
		String sql = "SELECT * FROM tbl_mst_Cluster where [IsActive]=1";
		List<Cluster> listCluster = jdbcTemplate.query(sql, new RowMapper<Cluster>() {
			@Override
			public Cluster mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cluster cluster = new Cluster();
				cluster.setClusterId(rs.getInt("Pk_ClusterId"));
				cluster.setClusterName(rs.getString("CluserName"));
				return cluster;
			}
		});
		return listCluster;
	}

	@Override
	public List<Cohort> getAllCohort() {
		String sql = "SELECT * FROM tbl_mst_Cohort where [IsActive]=1";
		List<Cohort> listCohort = jdbcTemplate.query(sql, new RowMapper<Cohort>() {
			@Override
			public Cohort mapRow(ResultSet rs, int rowNum) throws SQLException {
				Cohort cohort = new Cohort();
				cohort.setCohortId(rs.getInt("Pk_CphortId"));
				cohort.setCohortName(rs.getString("CohortName"));
				return cohort;
			}
		});
		return listCohort;
	}

	@Override
	public List<SelectionRoundMaster> getAllRound() {
		String sql = "SELECT * FROM tbl_mst_SelectionRound where [IsActive]=1";
		List<SelectionRoundMaster> listCohort = jdbcTemplate.query(sql, new RowMapper<SelectionRoundMaster>() {
			@Override
			public SelectionRoundMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
				SelectionRoundMaster selectionRoundMaster = new SelectionRoundMaster();
				selectionRoundMaster.setNoOfRoundId(rs.getInt("Pk_RoundId"));
				selectionRoundMaster.setNoOfRound(rs.getInt("Round"));
				return selectionRoundMaster;
			}
		});
		return listCohort;
	}

	@Override
	public List<YearMaster> getAllYear() {
		String sql = "SELECT * FROM tbl_mst_Year where [IsActive]=1";
		List<YearMaster> listCohort = jdbcTemplate.query(sql, new RowMapper<YearMaster>() {
			@Override
			public YearMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
				YearMaster yearMaster = new YearMaster();
				yearMaster.setYearId(rs.getInt("Pk_YearId"));
				yearMaster.setYear(rs.getInt("YearName"));
				return yearMaster;
			}
		});
		return listCohort;
	}

	@Override
	public List<RoleCompany> getAllRole() {
		String sql = "SELECT * FROM tbl_mst_Designation where [IsActive]=1";
		List<RoleCompany> listRole = jdbcTemplate.query(sql, new RowMapper<RoleCompany>() {
			@Override
			public RoleCompany mapRow(ResultSet rs, int rowNum) throws SQLException {
				RoleCompany roleCompany = new RoleCompany();
				roleCompany.setRoleCompanyId(rs.getInt("Pk_DesignationId"));
				roleCompany.setRoleCompany(rs.getString("DesignationName"));
				return roleCompany;
			}
		});
		return listRole;
	}

	@Override
	public List<CompanyMaster> getAllCompany() {
		String sql = "SELECT distinct Pk_CompanyId ,CompanyName FROM tbl_mst_company where isActive=1";
		List<CompanyMaster> listRole = jdbcTemplate.query(sql, new RowMapper<CompanyMaster>() {
			@Override
			public CompanyMaster mapRow(ResultSet rs, int rowNum) throws SQLException {
				CompanyMaster company = new CompanyMaster();
				company.setCompanyId(rs.getInt("Pk_CompanyId"));
				company.setCompanyName(rs.getString("CompanyName"));
				return company;
			}
		});
		return listRole;
	}

	public String saveFirmManagementForm(FirmManagementForm firmManagementForm, int userId) throws SQLException {

		String sql = " SELECT Pk_CompanyId ,CompanyName FROM tbl_mst_company where CompanyName='"
				+ firmManagementForm.getCompanyName() + "'";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		int compId = 0;
		while (rows.next()) {
			compId = rows.getInt("Pk_CompanyId");
		}

		String sql1 = " SELECT Pk_CphortId ,Fk_ClusterId  FROM tbl_mst_Cohort where IsActive = 1 and Pk_CphortId='"
				+ firmManagementForm.getCohortName() + "'";

		SqlRowSet rows1 = jdbcTemplate.queryForRowSet(sql1);
		int cohortId = 0;
		int clustId = 0;
		while (rows1.next()) {
			cohortId = rows1.getInt("Pk_CphortId");
			clustId = rows1.getInt("Fk_ClusterId");
		}

		/*
		 * String sql2 =
		 * " SELECT  Pk_ProcessId,ProcessName FROM tbl_mst_Process where ProcessName='"
		 * + firmManagementForm.getProcessName() + "'";
		 * 
		 * SqlRowSet rows2 = jdbcTemplate.queryForRowSet(sql2); int procsId = 0;
		 * while (rows2.next()) { procsId = rows2.getInt("Pk_ProcessId"); }
		 */
		// System.out.println("compid- "+compId+" cmp name-"+firmManagementForm.getCompanyName());

		String procedureCall = "";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			connection.setAutoCommit(false);
			String jobDescriptionFilePathList = firmManagementForm.getJobDescriptionFilePathList();
			// List<String>
			// additionalFilePathList=firmManagementForm.getAdditionalFilePathList();
			procedureCall = "{call sp_InsertCompany(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Insert");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, compId); // firmManagementForm.getCompanyId()
			callableSt.setInt(4, firmManagementForm.getProcessId());// procsId
			callableSt.setInt(5, 0);
			// callableSt.setInt(5, firmManagementForm.getRoleCompanyId());
			callableSt.setInt(6, firmManagementForm.getYear());
			callableSt.setString(7, null);
			callableSt.setBoolean(8, firmManagementForm.isPreferenceServe());
			callableSt.setBytes(9, firmManagementForm.getJobDescription().getBytes());

			callableSt.setBytes(10, null);
			// callableSt.setBytes(10,firmManagementForm.getAdditionalFile().getBytes());
			
			callableSt.setInt(11, clustId);// clustId
											// //firmManagementForm.getClusterId()
			callableSt.setInt(12, cohortId);// cohortId
											// //firmManagementForm.getCohortId()

			callableSt.setBoolean(13, true);
			// callableSt.setBoolean(13, firmManagementForm.isCoverLetter());
			callableSt.setInt(14, 0);
			callableSt.setString(15, null);
			callableSt.setString(16, null);
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date openingDate = formatter.parse(firmManagementForm.getOpeningDate());
			java.sql.Timestamp openingSqlTimeStamp = new java.sql.Timestamp(openingDate.getTime());
			// System.out.println("date::opening-" + openingDate+"open sql
			// date-"+openingSqlTimeStamp);
			Date closingDate = formatter.parse(firmManagementForm.getCloseingDate());
			java.sql.Timestamp closingSqlTimeStamp = new java.sql.Timestamp(closingDate.getTime());
			// System.out.println("date::closingDate-" + closingDate+"close sql
			// date-"+closingSqlTimeStamp);
			callableSt.setTimestamp(17, openingSqlTimeStamp);
			callableSt.setTimestamp(18, closingSqlTimeStamp);
			// callableSt.setDate(17,new
			// java.sql.Date(firmManagementForm.getOpeningDate().getTime()));
			// callableSt.setDate(18,new
			// java.sql.Date(firmManagementForm.getCloseingDate().getTime());
			callableSt.setInt(19, 0);
			callableSt.setInt(20, 0);
			callableSt.setString(21, null);
			// callableSt.setInt(19, firmManagementForm.getWorkExp());
			// callableSt.setString(20, firmManagementForm.getCompensation());
			callableSt.setInt(22, 0);
			// callableSt.setInt(21, firmManagementForm.getNoHire());
			callableSt.setString(23, firmManagementForm.getInterviewExperience());
			callableSt.setString(24, firmManagementForm.getInternshipExperience());
			callableSt.setString(25, firmManagementForm.getAdditionalTextArea());
			callableSt.setInt(26, firmManagementForm.getCreatedBy());
			callableSt.setBoolean(27, firmManagementForm.isMultCVRelates());
			callableSt.setString(28, jobDescriptionFilePathList);
			callableSt.setString(29, null);
			callableSt.setDate(30, null);
			callableSt.setInt(31, 0);
			callableSt.setDate(32, null);
			callableSt.setInt(33, 0);
			callableSt.setInt(34, 0);
			callableSt.setInt(35, 0);
			callableSt.setInt(35, 0);
			callableSt.setString(36, null);
			callableSt.setInt(37, firmManagementForm.getLimitCVNo());
			callableSt.setInt(38, firmManagementForm.getNoOfRound());
			callableSt.setString(39, null);
			callableSt.setInt(40, 0);
			callableSt.setInt(41, 0);
			callableSt.setInt(42, 0);
			callableSt.setBoolean(43, firmManagementForm.isHrHotlist());
			System.out.println("firmManagementForm.firmManagementForm.isHrHotlist()()-" + firmManagementForm.isHrHotlist());
			callableSt.execute();

			int companyId = 0;
			procedureCall = "{call sp_InsertCompany(?)}";
			try {
				callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "select");
				ResultSet rs = callableSt.executeQuery();
				if (rs.next()) {
					companyId = rs.getInt("Pk_ApplicationId");
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
			List<String> urlList = firmManagementForm.getUrl();
			List<String> urlDescList = firmManagementForm.getUrlDescription();
			CallableStatement urlWithDesc = connection
					.prepareCall("{call sp_InsertCompany(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			if (urlList.size() > 0) {
				for (int i = 0; i < urlList.size(); i++) {
					urlWithDesc.setString(1, "InsertUrl");
					urlWithDesc.setInt(2, companyId);
					urlWithDesc.setInt(3, 0);
					urlWithDesc.setInt(4, 0);
					urlWithDesc.setInt(5, 0);
					urlWithDesc.setInt(6, 0);
					urlWithDesc.setString(7, null);
					urlWithDesc.setBoolean(8, true);
					urlWithDesc.setBytes(9, null);
					urlWithDesc.setBytes(10, null);
					urlWithDesc.setInt(11, 0);
					urlWithDesc.setInt(12, 0);
					urlWithDesc.setBoolean(13, true);
					urlWithDesc.setInt(14, 0);

					urlWithDesc.setString(15, urlList.get(i));
					String urldisp;
					try {
						urldisp = urlDescList.get(i);
					} catch (ArrayIndexOutOfBoundsException e) {
						urldisp = null;
					}
					urlWithDesc.setString(16, urldisp);

					/*
					 * urlWithDesc.setString(15, urlList.get(i));
					 * urlWithDesc.setString(16, urlDescList.get(i));
					 */

					urlWithDesc.addBatch();
				}
				urlWithDesc.executeBatch();
			}
			//System.out.println("company Role Id:::"+firmManagementForm.getRoleCompanyId());
			List<Integer> roleCompanyId = firmManagementForm.getRoleCompanyId();
			List<Integer> workExp = firmManagementForm.getWorkExp();
			List<String> compensation = firmManagementForm.getCompensation();
			List<Integer> noHire = new ArrayList<Integer>();
			noHire = firmManagementForm.getNoHire();
			List<Integer> maxNoHire = new ArrayList<Integer>();
			maxNoHire = firmManagementForm.getMaxNoHire();
			List<Integer> avgNoHire = new ArrayList<Integer>();
			avgNoHire = firmManagementForm.getAvgNoHire();
			List<Integer> mapRoleId = new ArrayList<Integer>();
			mapRoleId = firmManagementForm.getMapRoleId();

			List<Boolean> coverLetter = new ArrayList<Boolean>();
			coverLetter = firmManagementForm.getCoverLetter();
			// List<MultipartFile>jobDescription=firmManagementForm.getJobDescription();
			// List<MultipartFile>additionalFile=firmManagementForm.getAdditionalFile();
			// List<String>interviewExperience=firmManagementForm.getInterviewExperience();
			// List<String>internshipExperience=firmManagementForm.getInternshipExperience();
			List<Integer> workExpMax = firmManagementForm.getWorkExpMax();
			// String
			// jobDescriptionFilePathList=firmManagementForm.getJobDescriptionFilePathList();
			// List<String>
			// additionalFilePathList=firmManagementForm.getAdditionalFilePathList();

			int dbRoleId=0;
			if (roleCompanyId.size() > 1) {
				String roleCall = "{call sp_InsertCompany(?)}";
				CallableStatement callableStRole = connection.prepareCall(roleCall);
				callableStRole.setString(1, "selectMaxRoleId");
				ResultSet rs = callableStRole.executeQuery();
				if (rs.next()) {
					dbRoleId = rs.getInt("roleCount");
				}
			}

			CallableStatement roleWithDesc = connection.prepareCall(
					"{call sp_InsertCompany(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			// System.out.println("size="+urlList.size()+"job------"+jobDescriptionFilePathList.size());
			if (roleCompanyId.size() > 0) {
				int maxExp = 0;
				for (int i = 0; i < roleCompanyId.size(); i++) {
						
					roleWithDesc.setString(1, "InserRole");
					roleWithDesc.setInt(2, companyId);
					roleWithDesc.setInt(3, 0);
					roleWithDesc.setInt(4, 0);
					roleWithDesc.setInt(5, roleCompanyId.get(i));
					roleWithDesc.setInt(6, 0);
					roleWithDesc.setString(7, null);
					roleWithDesc.setBoolean(8, true);
					roleWithDesc.setBytes(9, null);
					roleWithDesc.setBytes(10, null);
					roleWithDesc.setInt(11, 0);
					roleWithDesc.setInt(12, 0);
					// System.out.println("helllooooo");
					// System.out.println("coverLetter.get(i--)---"+"
					// "+coverLetter.isEmpty()+" s");
					boolean id;
					try {
						id = coverLetter.get(i);
					} catch (NullPointerException nullPointer) {
						id = false;
					}

					roleWithDesc.setBoolean(13, id);

					roleWithDesc.setInt(14, 0);
					roleWithDesc.setString(15, null);
					roleWithDesc.setString(16, null);
					roleWithDesc.setDate(17, null);
					roleWithDesc.setDate(18, null);
					// System.out.println("workExp"+workExp.get(i));
					if (workExp.size() > 0) {
						roleWithDesc.setInt(19, workExp.get(i)==null?0:workExp.get(i));
					} else {
						roleWithDesc.setInt(19, 0);
					}
					if (workExpMax.size() > 0) {
						maxExp  =workExpMax.get(i)==null?0:workExpMax.get(i);
						roleWithDesc.setInt(20, maxExp==0?20000:maxExp);
					} else {
						roleWithDesc.setInt(20, 20000);
					}
					if (compensation.size() > 0) {
						roleWithDesc.setString(21, compensation.get(i)==null?"":compensation.get(i));
					} else {
						roleWithDesc.setString(21, null);
					}
					if (noHire.size() > 0) {
						roleWithDesc.setInt(22, noHire.get(i)==null?0:noHire.get(i));
					} else {
						roleWithDesc.setInt(22, 0);
					}
					roleWithDesc.setString(23, null);
					roleWithDesc.setString(24, null);
					roleWithDesc.setString(25, null);
					roleWithDesc.setInt(26, 0);
					roleWithDesc.setBoolean(27, true);
					// roleWithDesc.setString(28,jobDescriptionFilePathList.get(i));
					// roleWithDesc.setString(29,
					// additionalFilePathList.get(i));

					roleWithDesc.setString(28, null);
					roleWithDesc.setString(29, null);
					roleWithDesc.setDate(30, null);
					roleWithDesc.setInt(31, 0);
					roleWithDesc.setDate(32, null);
					roleWithDesc.setInt(33, 0);
					roleWithDesc.setInt(34, 0);
					roleWithDesc.setInt(35, 0);
					roleWithDesc.setInt(35, 0);
					roleWithDesc.setString(36, null);
					roleWithDesc.setInt(37, 0);
					roleWithDesc.setInt(38, 0);
					roleWithDesc.setString(39, null);
					if (maxNoHire.size() > 0) {
						roleWithDesc.setInt(40, maxNoHire.get(i)==null?0:maxNoHire.get(i));
					} else {
						roleWithDesc.setInt(40, 0);
					}
					if (avgNoHire.size() > 0) {
						roleWithDesc.setInt(41, avgNoHire.get(i)==null?0:avgNoHire.get(i));
					} else {
						roleWithDesc.setInt(41, 0);
					}

					if (mapRoleId.size() > 0) {
						if(mapRoleId.get(i)==0){
							roleWithDesc.setInt(42, mapRoleId.get(i));
						}else{
							roleWithDesc.setInt(42, dbRoleId+mapRoleId.get(i));
						}
					} else {
						roleWithDesc.setInt(42, 0);
					}

					roleWithDesc.addBatch();
				}
				roleWithDesc.executeBatch();
			}

			List<MultipartFile> additionalFile = firmManagementForm.getAdditionalFile();
			List<String> additionalFilePathList = firmManagementForm.getAdditionalFilePathList();
			roleWithDesc = connection
					.prepareCall("{call sp_InsertCompany(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}");
			// System.out.println("size="+urlList.size()+"job------"+jobDescriptionFilePathList.size());
			if (additionalFile.size() > 0) {
				for (int i = 0; i < additionalFile.size(); i++) {
					roleWithDesc.setString(1, "AdditionalFile");
					roleWithDesc.setInt(2, companyId);
					roleWithDesc.setInt(3, 0);
					roleWithDesc.setInt(4, 0);
					roleWithDesc.setInt(5, 0);
					roleWithDesc.setInt(6, 0);
					roleWithDesc.setString(7, null);
					roleWithDesc.setBoolean(8, true);
					roleWithDesc.setBytes(9, null);
					roleWithDesc.setBytes(10, additionalFile.get(i).getBytes());
					roleWithDesc.setInt(11, 0);
					roleWithDesc.setInt(12, 0);
					roleWithDesc.setBoolean(13, false);
					roleWithDesc.setInt(14, 0);
					roleWithDesc.setString(15, null);
					roleWithDesc.setString(16, null);
					roleWithDesc.setDate(17, null);
					roleWithDesc.setDate(18, null);
					roleWithDesc.setInt(19, 0);
					roleWithDesc.setInt(20, 0);
					roleWithDesc.setString(21, null);
					roleWithDesc.setInt(22, 0);
					roleWithDesc.setString(23, null);
					roleWithDesc.setString(24, null);
					roleWithDesc.setString(25, null);
					roleWithDesc.setInt(26, 0);
					roleWithDesc.setBoolean(27, true);
					roleWithDesc.setString(28, null);
					roleWithDesc.setString(29, additionalFilePathList.get(i));
					roleWithDesc.addBatch();
				}
				roleWithDesc.executeBatch();
			}

			connection.commit();
		} catch (Exception e) {
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
		return null;
	}

	public java.sql.Date dateFormatter(Date date) {
		String dateForMySql = "";
		java.sql.Date sqlDate = null;
		try {
			if (date == null) {
				dateForMySql = null;
			} else {
				SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd");
				dateForMySql = simpleDateFormat.format(date);
				java.util.Date stringToDate = simpleDateFormat.parse(dateForMySql);
				sqlDate = new java.sql.Date(stringToDate.getTime());
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return sqlDate;
	}

	@Override
	public List<Cohort> getCohortByClusterId(String cname) {
		// System.out.println("FirmManagementDAOImpl.getCohortByClusterId()"+cname);
		final String procedureCall = "{call proc_Master(?,?,?,?,?,?,?,?)}";
		Connection connection = null;
		List<Cohort> cohort = new ArrayList<Cohort>();
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectcohortById");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, 0);

			callableSt.setString(8, cname);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Cohort c = new Cohort();
				c.setCohortId(rs.getInt("Pk_CphortId"));
				c.setCohortName(rs.getString("CohortName"));
				c.setClusterId(rs.getInt("Fk_ClusterId"));
				cohort.add(c);
			}
		} catch (Exception e) {
			e.printStackTrace();
		}
		return cohort;
	}

	public FirmManagementForm getCompanyDetails(int appId, FirmManagementForm firmMangForm) {
		//System.out.println("application id in getCompanyDetails method::" + appId);
		String procedureCall = "{call Sp_SelectCompany(?,?)}";
		Connection connection = null;
		try {
			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Company");
			callableSt.setInt(2, appId);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				//System.out.println("inside while connection::");
				firmMangForm.setApplicationId(rs.getInt("Pk_ApplicationId"));
				firmMangForm.setCompanyName(rs.getString("CompanyName"));
				firmMangForm.setProcessId(rs.getInt("Pk_ProcessId"));
				firmMangForm.setProcessName(rs.getString("ProcessName"));
				firmMangForm.setYear(Integer.parseInt(rs.getString("Year")));
				firmMangForm.setClusterId(rs.getInt("Pk_ClusterId"));
				firmMangForm.setCohortId(rs.getInt("Pk_CphortId"));
				firmMangForm.setCohortName(rs.getString("CohortName"));
				//System.out.println("set perfServy::"+rs.getBoolean("PrefenceSurvey"));
				firmMangForm.setPreferenceServe(rs.getBoolean("PrefenceSurvey"));
				firmMangForm.setMultCVRelates(rs.getBoolean("CVReleated"));
				firmMangForm.setNoOfSelectedRounds(rs.getInt("NoOfSelectionRound"));
			//	System.out.println("date::"+rs.getString("OpningDatetime")+":::"+rs.getString("ClosingDatetime"));
				String[] openingDateTime=rs.getString("OpningDatetime").split(" ");
			//	System.out.println("openingDateTime:::"+openingDateTime.toString());
				String[] openingDate=openingDateTime[0].split("-");
				String actualDate=openingDate[2]+"-"+openingDate[1]+"-"+openingDate[0]+" ";
				firmMangForm.setOpeningDate(actualDate+openingDateTime[1]);
				openingDateTime=rs.getString("ClosingDatetime").split(" ");
				openingDate=openingDateTime[0].split("-");
				actualDate=openingDate[2]+"-"+openingDate[1]+"-"+openingDate[0]+" ";
				firmMangForm.setCloseingDate(actualDate+openingDateTime[1]);
				firmMangForm.setAdditionalTextArea(rs.getString("AdditionalText"));
				firmMangForm.setInterviewExperience(rs.getString("InterViewExp"));
				firmMangForm.setInternshipExperience(rs.getString("InternshipExp"));
				//System.out.println("noOfRole:::"+rs.getInt("CVReleatedNo"));
				firmMangForm.setNoOfRole(rs.getInt("CVReleatedNo"));
				if (rs.getString("JobDescPath") != null) {
					StringBuffer jobDescPath = new StringBuffer(rs.getString("JobDescPath")).reverse();
					String[] indexOfJobDesc = jobDescPath.toString().split("\\\\");
					StringBuffer buffer = new StringBuffer(indexOfJobDesc[0]).reverse();
					System.out.println("buffer:::" + buffer);
					firmMangForm.setJobDecPath(buffer.toString());
				} else {
					firmMangForm.setJobDecPath("");
				}
				firmMangForm.setHrHotlist(rs.getBoolean("IsHRHotList"));
				System.out.println("rs.getBoolean(IsHRHotList):::"+rs.getBoolean("IsHRHotList"));
			}

			procedureCall = "{call Sp_SelectCompany(?,?)}";
			List<Integer> urlId = new ArrayList<Integer>();
			List<String> url = new ArrayList<String>();
			List<String> urlDesc = new ArrayList<String>();
			CallableStatement callableSt1 = connection.prepareCall(procedureCall);
			callableSt1.setString(1, "URL");
			callableSt1.setInt(2, appId);
			ResultSet rs1 = callableSt1.executeQuery();
			while (rs1.next()) {
				urlId.add(rs1.getInt("Pk_Urlid"));
				url.add(rs1.getString("UrlText"));
				urlDesc.add(rs1.getString("Description"));
			}

			//System.out.println("urlId:::" + urlId.toString());

			firmMangForm.setUrl(url);
			firmMangForm.setUrlDescription(urlDesc);
			firmMangForm.setUrlId(urlId);

			//System.out.println("url:::::::::::" + firmMangForm.getUrl().size());

			procedureCall = "{call Sp_SelectCompany(?,?)}";
			List<String> additionFileName = new ArrayList<String>();
			List<Integer> additionFileId = new ArrayList<Integer>();
			CallableStatement callableSt2 = connection.prepareCall(procedureCall);
			callableSt2.setString(1, "Additional");
			callableSt2.setInt(2, appId);
			ResultSet rs2 = callableSt2.executeQuery();
			while (rs2.next()) {
				if (rs2.getString("FilePath") != null) {
					StringBuffer jobDescPath = new StringBuffer(rs2.getString("FilePath")).reverse();
					String[] indexOfAdditionalFile = jobDescPath.toString().split("\\\\");
					StringBuffer bufferAdditionalFile = new StringBuffer(indexOfAdditionalFile[0]).reverse();
					//System.out.println("buffer:::" + bufferAdditionalFile);
					additionFileName.add(bufferAdditionalFile.toString());
				} else {

				}
				additionFileId.add(rs2.getInt("Pk_JobDescriptionId"));
			}

			firmMangForm.setAdditionalFilePathList(additionFileName);
			firmMangForm.setAdditionalFileId(additionFileId);
			//System.out.println("additional file list size::" + firmMangForm.getAdditionalFilePathList().size());

			procedureCall = "{call Sp_SelectCompany(?,?)}";
			List<Integer> RoleId = new ArrayList<Integer>();
			List<Integer> designationId = new ArrayList<Integer>();
			List<String> RoleName = new ArrayList<String>();
			List<Boolean> coverLetter = new ArrayList<Boolean>();
			List<Integer> minimumWorkExp = new ArrayList<Integer>();
			List<Integer> maxiWorkExp = new ArrayList<Integer>();
			List<String> compensation = new ArrayList<String>();
			List<Integer> expectedNoOfHires = new ArrayList<Integer>();
			List<Integer> maxHireOffers = new ArrayList<Integer>();
			List<Integer> avgHireOffers = new ArrayList<Integer>();
			List<Integer> rolemaplist = new ArrayList<Integer>();
			
			CallableStatement callableSt3 = connection.prepareCall(procedureCall);
			callableSt3.setString(1, "Role");
			callableSt3.setInt(2, appId);
			ResultSet rs3 = callableSt3.executeQuery();
			while (rs3.next()) {
				designationId.add(rs3.getInt("Pk_DesignationId"));
				RoleId.add(rs3.getInt("Pk_CompanyRoleId"));
				RoleName.add(rs3.getString("DesignationName"));
				coverLetter.add(rs3.getBoolean("CoverLatter"));
				minimumWorkExp.add(rs3.getInt("WorkExpReq"));
				maxiWorkExp.add(rs3.getInt("WorkExpReqMax")==20000?0:rs3.getInt("WorkExpReqMax"));
				compensation.add(rs3.getString("Compension"));
				expectedNoOfHires.add(rs3.getInt("Hires"));
				maxHireOffers.add(rs3.getInt("MaxHire"));
				avgHireOffers.add(rs3.getInt("AvgHire"));
				rolemaplist.add(rs3.getInt("RoleMap"));
			}
			firmMangForm.setDesiationId(designationId);
			firmMangForm.setRoleId(RoleId);
			firmMangForm.setRoleName(RoleName);
			firmMangForm.setCoverLetter(coverLetter);
			firmMangForm.setWorkExp(minimumWorkExp);
			firmMangForm.setWorkExpMax(maxiWorkExp);
			firmMangForm.setCompensation(compensation);
			firmMangForm.setNoHire(expectedNoOfHires);
			firmMangForm.setMaxNoHire(maxHireOffers);
			firmMangForm.setAvgNoHire(avgHireOffers);
			firmMangForm.setMapRoleId(rolemaplist);

			//System.out.println("roleIdLength::" + firmMangForm.getRoleId().size());

		} catch (Exception e) {
			e.printStackTrace();
		}

		return firmMangForm;
	}

	public JSONArray getCohortValues(String clusterId) {
		System.out.println("inside getCohortValues method:::" + clusterId);
		String procedureCall = "{call proc_Master(?,?,?,?,?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {

			connection = getJdbcTemplate().getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "selectcohortById1");
			callableSt.setString(2, null);
			callableSt.setString(3, null);
			callableSt.setInt(4, 0);
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, Integer.parseInt(clusterId));
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				JSONObject object = new JSONObject();
				object.put("key", rs.getInt("Pk_CphortId"));
				object.put("value", rs.getString("CohortName"));
				array.put(object);
			}
			//System.out.println("Array value:::" + array.toString());
		} catch (Exception e) {
			System.out.println("Exception in getCohortValues method" + e.getMessage());
			e.printStackTrace();
		}
		return array;
	}

	public String deleteRole(String roleId, String appId) throws Exception {
		String returnValue = "";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call Sp_UpdateCompany(?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deleteRole");
			callableSt.setInt(2, Integer.parseInt(appId));
			callableSt.setInt(3, 0); // firmManagementForm.getCompanyId()
			callableSt.setInt(4, 0);// procsId
			callableSt.setInt(5, Integer.parseInt(roleId));
			callableSt.execute();
			returnValue = "success";
		} catch (Exception e) {
			returnValue = "failure";
			System.out.println("Exception in deleteRole method::" + e.getMessage());
			e.printStackTrace();
		}
		return returnValue;
	}

	public String updateFirmManagement(FirmManagementForm firmManagementForm) {
		//System.out.println("companyName:::" + firmManagementForm.getCompanyName() + "appId::"
			//	+ firmManagementForm.getApplicationId());
		String returnValue = "";
		String sql = " SELECT Pk_CompanyId ,CompanyName FROM tbl_mst_company where CompanyName='"
				+ firmManagementForm.getCompanyName() + "' and IsActive=1";

		SqlRowSet rows = jdbcTemplate.queryForRowSet(sql);
		int compId = 0;
		while (rows.next()) {
			compId = rows.getInt("Pk_CompanyId");
		}
	//	System.out.println("companyId:::" + compId);
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call Sp_UpdateCompany(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,"
					+ "?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "Company");
			callableSt.setInt(2, firmManagementForm.getApplicationId());
			callableSt.setInt(3, compId); // firmManagementForm.getCompanyId()
			callableSt.setInt(4, firmManagementForm.getProcessId());// procsId
			callableSt.setInt(5, 0);
			callableSt.setInt(6, 0);
			callableSt.setInt(7, firmManagementForm.getYear());
			callableSt.setString(8, null);
			//System.out.println("perfenece servey::"+firmManagementForm.getPreferenceServe());
			callableSt.setBoolean(9, firmManagementForm.getPreferenceServe());
			callableSt.setString(10, null);
			callableSt.setString(11, null);
			callableSt.setInt(12, Integer.parseInt(firmManagementForm.getClusterName()));
			callableSt.setInt(13, Integer.parseInt(firmManagementForm.getCohortName()));
			callableSt.setBoolean(14, true);
			callableSt.setInt(15, 0);
			callableSt.setString(16, null);
			callableSt.setString(17, null);
			DateFormat formatter = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
			Date openingDate = formatter.parse(firmManagementForm.getOpeningDate());
			java.sql.Timestamp openingSqlTimeStamp = new java.sql.Timestamp(openingDate.getTime());
			// System.out.println("date::opening-" + openingDate+"open sql
			// date-"+openingSqlTimeStamp);
			Date closingDate = formatter.parse(firmManagementForm.getCloseingDate());
			java.sql.Timestamp closingSqlTimeStamp = new java.sql.Timestamp(closingDate.getTime());
			callableSt.setTimestamp(18, openingSqlTimeStamp);
			callableSt.setTimestamp(19, closingSqlTimeStamp);
			callableSt.setInt(20, 0);
			callableSt.setInt(21, 0);
			callableSt.setString(22, null);
			callableSt.setInt(23, 0);
			callableSt.setString(24, firmManagementForm.getInternshipExperience());
			callableSt.setString(25, firmManagementForm.getInternshipExperience());
			callableSt.setString(26, firmManagementForm.getAdditionalTextArea());
			callableSt.setInt(27, 0);
			callableSt.setBoolean(28, firmManagementForm.isMultCVRelates());
		
			callableSt.setString(29, firmManagementForm.getJobDescriptionFilePathList());
			//System.out.println("path List value::" + additionalFilePathList.get(i));
			callableSt.setString(30,null);
			callableSt.setTimestamp(31, null);
			callableSt.setBoolean(32, true);
			callableSt.setInt(33, 0);
			callableSt.setString(34, null);
			callableSt.setInt(35, firmManagementForm.getLimitCVNo());
			callableSt.setInt(36, firmManagementForm.getNoOfRound());
			callableSt.setString(37, null);
			callableSt.setInt(38, 0);			
			callableSt.setInt(39, 0);			
			callableSt.setInt(40, 0);
			callableSt.setInt(41, 0);
			callableSt.setInt(42, 0);
			callableSt.setBoolean(43, firmManagementForm.isHrHotlist());
			callableSt.execute();

			procedureCall = "{call Sp_UpdateCompany(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStUrl = connection.prepareCall(procedureCall);
		//	System.out.println("url size::" + firmManagementForm.getUrlId().size());
			for (int i = 0; i < firmManagementForm.getUrlId().size(); i++) {
			//	System.out.println("url id::" + firmManagementForm.getUrlId().get(i));
				callableStUrl.setString(1, "URL");
				callableStUrl.setInt(2, firmManagementForm.getApplicationId());
				callableStUrl.setInt(3, 0); // firmManagementForm.getCompanyId()
				callableStUrl.setInt(4, 0);// procsId
				callableStUrl.setInt(5, 0);
				callableStUrl.setInt(6, 0);
				callableStUrl.setInt(7, 0);
				callableStUrl.setString(8, null);
				callableStUrl.setBoolean(9, true);
				callableStUrl.setString(10, null);
				callableStUrl.setString(11, null);
				callableStUrl.setInt(12, 0);
				callableStUrl.setInt(13, 0);
				callableStUrl.setBoolean(14, true);
				callableStUrl.setInt(15, firmManagementForm.getUrlId().get(i));
				if(firmManagementForm.getUrl().size()!=0){
					callableStUrl.setString(16, firmManagementForm.getUrl().get(i));
				}
				else {
					callableStUrl.setString(16, null);
				}
				if(firmManagementForm.getUrlDescription().size()!=0){
					callableStUrl.setString(17, firmManagementForm.getUrlDescription().get(i));
				}
				else {
					callableStUrl.setString(17, null);
				}
				//callableStUrl.setString(17, firmManagementForm.getUrlDescription().get(i));
			    callableStUrl.execute();
			}

			procedureCall = "{call Sp_UpdateCompany(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStAdditional = connection.prepareCall(procedureCall);
			System.out.println("url size::" + firmManagementForm.getUrlId().size());

			List<MultipartFile> additionalFile = firmManagementForm.getAdditionalFile();
			List<String> additionalFilePathList = firmManagementForm.getAdditionalFilePathList();

			System.out.println("additionalFile::::" + additionalFile.toString());
			System.out.println("additionalFilePathList::::" + additionalFilePathList.size());
			if (additionalFilePathList.size() > 0) {
				if (additionalFilePathList != null) {
					for (int i = 0; i < additionalFilePathList.size(); i++) {
						if (additionalFilePathList.get(i) != null) {
							callableStAdditional.setString(1, "Additional");
							callableStAdditional.setInt(2, firmManagementForm.getApplicationId());
							callableStAdditional.setInt(3, 0); // firmManagementForm.getCompanyId()
							callableStAdditional.setInt(4, 0);// procsId
							callableStAdditional.setInt(5, 0);
							callableStAdditional.setInt(6, 0);
							callableStAdditional.setInt(7, 0);
							callableStAdditional.setString(8, null);
							callableStAdditional.setBoolean(9, true);
							callableStAdditional.setString(10, null);
							callableStAdditional.setBytes(11, additionalFile.get(i).getBytes());
							callableStAdditional.setInt(12, 0);
							callableStAdditional.setInt(13, 0);
							callableStAdditional.setBoolean(14, true);
							callableStAdditional.setInt(15, 0);
							callableStAdditional.setString(16, null);
							callableStAdditional.setString(17, null);
							callableStAdditional.setTimestamp(18, null);
							callableStAdditional.setTimestamp(19, null);
							callableStAdditional.setInt(20, 0);
							callableStAdditional.setInt(21, 0);
							callableStAdditional.setString(22, null);
							callableStAdditional.setInt(23, 0);
							callableStAdditional.setString(24, null);
							callableStAdditional.setString(25, null);
							callableStAdditional.setString(26, null);
							callableStAdditional.setInt(27, 0);
							callableStAdditional.setString(28, null);
							callableStAdditional.setString(29, null);
							//System.out.println("path List value::" + additionalFilePathList.get(i));
							callableStAdditional.setString(30,
									additionalFilePathList.get(i) == null ? "" : additionalFilePathList.get(i));
							callableStAdditional.setTimestamp(31, null);
							callableStAdditional.setBoolean(32, true);
							callableStAdditional.setInt(33, 0);
							callableStAdditional.setString(34, null);
							callableStAdditional.setInt(35, 0);
							callableStAdditional.setInt(36, 0);
							callableStAdditional.setString(37, null);
							callableStAdditional.setInt(38, firmManagementForm.getAdditionalFileId().get(i));
							callableStAdditional.execute();
						}
					}
				}
			}

			procedureCall = "{call Sp_UpdateCompany(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)}";
			CallableStatement callableStRole = connection.prepareCall(procedureCall);
			
			int dbRoleId=0;
			if (firmManagementForm.getRoleId().size() > 1) {
				String roleCall = "{call sp_InsertCompany(?)}";
				CallableStatement callableRoleID = connection.prepareCall(roleCall);
				callableRoleID.setString(1, "selectMaxRoleId");
				ResultSet rs = callableRoleID.executeQuery();
				if (rs.next()) {
					dbRoleId = rs.getInt("roleCount");
				}
			}
			//System.out.println("no of role::"+firmManagementForm.getRoleId().size());
			int maxExp = 0;
			for (int i = 0; i < firmManagementForm.getRoleId().size(); i++) {
				/*System.out.println("role id size::" + firmManagementForm.getRoleId().size());
				System.out.println("role id::" + firmManagementForm.getRoleId().get(i));
				System.out.println("compnsation id::" + firmManagementForm.getCompensation().get(i));
				System.out.println("cover Flag::"+firmManagementForm.getCoverLetter());*/
				callableStRole.setString(1, "Role");
				callableStRole.setInt(2, firmManagementForm.getApplicationId());
				callableStRole.setInt(3, 0); // firmManagementForm.getCompanyId()
				callableStRole.setInt(4, 0);// procsId
				callableStRole.setInt(5, firmManagementForm.getRoleId().get(i));
				callableStRole.setInt(6, firmManagementForm.getRoleCompanyId().get(i));
				callableStRole.setInt(7, 0);
				callableStRole.setString(8, null);
				callableStRole.setBoolean(9, true);
				callableStRole.setString(10, null);
				callableStRole.setBytes(11, null);
				callableStRole.setInt(12, 0);
				callableStRole.setInt(13, 0);
				callableStRole.setBoolean(14, firmManagementForm.getCoverLetter().get(i));
				callableStRole.setInt(15, 0);
				callableStRole.setString(16, null);
				callableStRole.setString(17, null);
				callableStRole.setTimestamp(18, null);
				callableStRole.setTimestamp(19, null);
				callableStRole.setInt(20,
						firmManagementForm.getWorkExp().get(i) == null ? 0 : firmManagementForm.getWorkExp().get(i));
				maxExp = firmManagementForm.getWorkExpMax().get(i)== null?0: firmManagementForm.getWorkExpMax().get(i);
				callableStRole.setInt(21, maxExp==0?20000 : maxExp);
				if(firmManagementForm.getCompensation().size()!=0){
					callableStRole.setString(22, firmManagementForm.getCompensation().get(i));
				}
				else {
					callableStRole.setString(22, null);
				}
				
				callableStRole.setInt(23,
						firmManagementForm.getNoHire().get(i) == null ? 0 : firmManagementForm.getNoHire().get(i));
				callableStRole.setString(24, null);
				callableStRole.setString(25, null);
				callableStRole.setString(26, null);
				callableStRole.setInt(27, 0);
				callableStRole.setString(28, null);
				callableStRole.setString(29, null);
				callableStRole.setString(30, null);
				callableStRole.setTimestamp(31, null);
				callableStRole.setBoolean(32, true);
				callableStRole.setInt(33, 0);
				callableStRole.setString(34, null);
				callableStRole.setInt(35, 0);
				callableStRole.setInt(36, 0);
				callableStRole.setString(37, null);
				callableStRole.setInt(38, 0);
				callableStRole.setInt(39, firmManagementForm.getRoleId().get(i));
				callableStRole.setInt(40,
						firmManagementForm.getMaxNoHire().get(i) == null ? 0 : firmManagementForm.getMaxNoHire().get(i));			
				callableStRole.setInt(41,
						firmManagementForm.getAvgNoHire().get(i) == null ? 0 : firmManagementForm.getAvgNoHire().get(i));
				
				if (firmManagementForm.getMapRoleId().size() > 0) {
					int roleIdCount =0;
					for (Integer val : firmManagementForm.getRoleId()) {
						if(val!=0) roleIdCount++;
					}
					if(firmManagementForm.getMapRoleId().get(i)==0){
						callableStRole.setInt(42, firmManagementForm.getMapRoleId().get(i));
					}else{
						if(firmManagementForm.getRoleId().size()>=i){
							if(firmManagementForm.getRoleId().get(firmManagementForm.getMapRoleId().get(i)==0?0:firmManagementForm.getMapRoleId().get(i)-1)>0)
								callableStRole.setInt(42, firmManagementForm.getRoleId().get(firmManagementForm.getMapRoleId().get(i)==0?0:firmManagementForm.getMapRoleId().get(i)-1));
							else
								callableStRole.setInt(42, dbRoleId+(firmManagementForm.getMapRoleId().get(i)-roleIdCount));
								
						}else{
							callableStRole.setInt(42, dbRoleId+(firmManagementForm.getMapRoleId().get(i)-roleIdCount));
						}
					}
				} else {
					callableStRole.setInt(42, 0);
				}
				
				callableStRole.execute();
			}
			returnValue = "success";
		} catch (Exception e) {
			returnValue = "failure";
			System.out.println("Exception in updateFirmManagement method::" + e.getMessage());
			e.printStackTrace();
		}
		return returnValue;
	}

	public String updateDeleteUrl(String url) {
		System.out.println("inside updateDeleteUrl method::");
		String returnValue = "";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call Sp_SelectCompany(?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deleteUrl");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, Integer.parseInt(url)); // firmManagementForm.getCompanyId()
			callableSt.execute();
			returnValue = "success";
		} catch (Exception e) {
			returnValue = "failure";
			System.out.println("Exception in updateDeleteUrl method::" + e.getMessage());
			e.printStackTrace();
		}
		return returnValue;
	}

	public String deleteAdditionalFiles(String addFileId) {
		System.out.println("addFileId id::" + addFileId);
		String returnValue = "";
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call Sp_SelectCompany(?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "deleteAddFiles");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0); // firmManagementForm.getCompanyId()
			callableSt.setInt(4, Integer.parseInt(addFileId));
			callableSt.execute();
			returnValue = "success";
		} catch (Exception e) {
			returnValue = "failure";
			System.out.println("Exception in updateDeleteUrl method::" + e.getMessage());
			e.printStackTrace();
		}
		return returnValue;
	}

	@Override
	public Boolean checkCompanyClusterCohort(String compName, String cluster, int cohortId,int year) {
		System.out.println("FirmManagementDAOImpl.checkCompanyClusterCohort()"+compName+" cluster-"+cluster+"cohortid--"+cohortId);
		Boolean checkCompany=false;
		try {
			Connection connection = jdbcTemplate.getDataSource().getConnection();
			String procedureCall = "{call Sp_SelectCompany(?,?,?,?,?,?,?,?)}";
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "checkCompanyClusterCohort");
			callableSt.setInt(2, 0);
			callableSt.setInt(3, 0); 
			callableSt.setInt(4,0);
			callableSt.setString(5,compName.trim() );
			callableSt.setString(6,cluster.trim());
			callableSt.setInt(7,cohortId);
			callableSt.setInt(8, year); 
			ResultSet rs=callableSt.executeQuery();
			System.out.println("size--of-checkcompay--"+rs.getFetchSize());
			while(rs.next()){				
				checkCompany=true;
			}
			System.out.println("checkCompany===========in dao---"+checkCompany);
		} catch (Exception e) {			
			System.out.println("Exception in checkCompanyClusterCohort method::" + e.getMessage());
			e.printStackTrace();
		}
		return checkCompany;
	}
	
	
}
