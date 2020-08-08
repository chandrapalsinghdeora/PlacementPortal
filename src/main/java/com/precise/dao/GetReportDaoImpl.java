package com.precise.dao;
import java.awt.Desktop;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;
import org.apache.poi.xssf.usermodel.XSSFSheet;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import com.precise.model.CloseStatus;
import com.precise.model.Cluster;

@Repository
public class GetReportDaoImpl implements GetReportDao {
	@Autowired
	private JdbcTemplate jdbcTemplate; 
	
	
	@Override
	public void getDownloadCL(List<CloseStatus> list, int clusterid,String downloadCLFile){

		final String procedureCall = "{call proc_getReport(?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("Consideration List");
			Row rowhead = sheet.createRow((int) 0);
			Row row1;
			for (int i = 0; i < list.size(); i++) {
				rowhead.createCell(i, Cell.CELL_TYPE_STRING)
						.setCellValue(list.get(i).getFirmName() + "-" + list.get(i).getRoleName());
			}
			int count = 1, count1 = 1;
			for (int y = 0; y < list.size(); y++) {
				int i = 1;
				count = 1;

				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "GenerateConsiderationList");
				callableSt.setInt(2, clusterid);
				callableSt.setInt(3, list.get(y).getCmpId());
				callableSt.setInt(4, 0);

				callableSt.setInt(5, list.get(y).getCmpReportRoleId());
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				try {
					System.out.println("roleid:::" + list.get(y).getCmpReportRoleId() + " cmpid::" + list.get(y).getCmpId()+" :: "+list.size()+" clusterid:: "+clusterid);
				//	System.out.println("i :: " + i + "  y ::: " + y);
					if (rs.next()) {
						int j=1;
					//	System.out.println("count1: " + count1 + " count:: " + count);
						do {
							if (count < count1 && j==1) {
								row1 = sheet.getRow(count);
								count++;
							} else {
								row1 = sheet.createRow(i);
								j=0;
								count1++;
							}
							
							//System.out.println("email :: "+rs.getString("EmailID"));
							row1.createCell(y).setCellValue(rs.getString("EmailID")==null?"":rs.getString("EmailID"));

							i++;
						} while (rs.next());
					} else {
						//System.out.println("count1: " + count1 + " count:: " + count);
						if (count < count1) {
							row1 = sheet.getRow(count);
							count++;
						} else {
							row1 = sheet.createRow(i);
							count1++;
						}

						row1.createCell(y, Cell.CELL_TYPE_STRING).setCellValue("NA");

						i++;
					}

				} catch (SQLException e1) {
					System.out.println("Error creating download consider list of student");
					e1.printStackTrace();
				}
			}
			try {
				FileOutputStream fileOut = new FileOutputStream(downloadCLFile);
				workbook.write(fileOut);
				fileOut.close();
				Desktop.getDesktop().open(new File(downloadCLFile));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	
/*public void getCL(List<CloseStatus> list, int clusterid) {
		
		final String procedureCall = "{call proc_getReport(?,?,?,?,?)}";
		Connection connection = null;
		StringBuffer s=new StringBuffer();
		try {
			 connection = jdbcTemplate.getDataSource().getConnection();
             CallableStatement callableSt = connection.prepareCall(procedureCall);
             HSSFWorkbook workbook = new HSSFWorkbook();
             HSSFSheet sheet = workbook.createSheet("project_wise");
             HSSFRow rowhead = sheet.createRow(0); 
             rowhead.createCell(0).setCellValue("Firm Name");
             rowhead.createCell(1).setCellValue("Role Name");
             rowhead.createCell(2).setCellValue("Student Email");
             for(int i=0;i<list.size();i++)
             {
                if(s.length()>0)
                	s.delete(0, s.length());
 				callableSt.setString(1, "GenerateConsiderationList");
 				callableSt.setInt(2,clusterid);
 				callableSt.setInt(3,list.get(i).getCmpId());
 				callableSt.setInt(4, 0);

 				callableSt.setInt(5, list.get(i).getCmpRoleId());
                ResultSet rs = null;
             rs = callableSt.executeQuery();
          
             HSSFRow row = sheet.createRow(i+1);
             row.createCell(0).setCellValue(list.get(i).getFirmName());
             row.createCell(1).setCellValue(list.get(i).getRoleName());
             
              
                if(rs.next())
                {
                 do{
                    s+=rs.getString("EmailID")+",";
                	 s.append(rs.getString("EmailID")+"      ");
                 }while(rs.next());
                 row.createCell(2).setCellValue(s.toString());
                }
                else
                {
                 row.createCell(2).setCellValue("NA");
                }
             }
                
                 try {
                 String yemi = "D:/test.xls";
                 FileOutputStream fileOut = new FileOutputStream(yemi);
                 workbook.write(fileOut);
                 fileOut.close();
                 
                 Desktop.getDesktop().open(new File("D:\\test.xls"));
                 
                 }
                 catch (FileNotFoundException e1) {
                     e1.printStackTrace();
                 } catch (IOException e1) {
                     e1.printStackTrace();
                 }
             
     } catch (SQLException e) {
             e.printStackTrace();
     } finally {
             if (connection != null)
                     try {
                             connection.close();
                     } catch (SQLException e) {
                             e.printStackTrace();
                     }
     }

	}*/
	
	@Override
	public void getDownloadSL1(List<CloseStatus> list, int clusterid,String downloadSL1File){
		final String procedureCall = "{call proc_getReport(?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("ShortList1");
			Row rowhead = sheet.createRow((int) 0);
			Row row1;
			for (int i = 0; i < list.size(); i++) {
				rowhead.createCell(i, Cell.CELL_TYPE_STRING)
						.setCellValue(list.get(i).getFirmName() + "-" + list.get(i).getRoleName());
			}
			int count = 1, count1 = 1;
			for (int y = 0; y < list.size(); y++) {
				int i = 1;
				count = 1;

				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "SelectShortListOne");
				callableSt.setInt(2, clusterid);
				callableSt.setInt(3, list.get(y).getCmpId());
				callableSt.setInt(4, 0);

				callableSt.setInt(5, list.get(y).getCmpReportRoleId());
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				try {
				//	System.out.println("roleid:::" + list.get(y).getCmpRoleId() + " cmpid::" + list.get(y).getCmpId());
				//	System.out.println("i :: " + i + "  y ::: " + y);
					if (rs.next()) {
					//	System.out.println("count1: " + count1 + " count:: " + count);
						int j=1;
						//	System.out.println("count1: " + count1 + " count:: " + count);
						do {
							if (count < count1 && j==1) {
								row1 = sheet.getRow(count);
								count++;
							} else {
								row1 = sheet.createRow(i);
								j=0;
								count1++;
							}
							row1.createCell(y).setCellValue(rs.getString("EmailID"));

							i++;
						} while (rs.next());
					} else {
						//System.out.println("count1: " + count1 + " count:: " + count);
						if (count < count1) {
							row1 = sheet.getRow(count);
							count++;
						} else {
							row1 = sheet.createRow(i);
							count1++;
						}

						row1.createCell(y, Cell.CELL_TYPE_STRING).setCellValue("NA");

						i++;
					}

				} catch (SQLException e1) {
					System.out.println("Error creating download consider list of student");
					e1.printStackTrace();
				}
			}
			try {
				FileOutputStream fileOut = new FileOutputStream(downloadSL1File);
				workbook.write(fileOut);
				fileOut.close();
				Desktop.getDesktop().open(new File(downloadSL1File));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	@Override
	 public void getDownloadSL2(List<CloseStatus> list, int clusterid,String downloadSL2File){
		final String procedureCall = "{call proc_getReport(?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("ShortList2");
			Row rowhead = sheet.createRow((int) 0);
			Row row1;
			for (int i = 0; i < list.size(); i++) {
				rowhead.createCell(i, Cell.CELL_TYPE_STRING)
						.setCellValue(list.get(i).getFirmName() + "-" + list.get(i).getRoleName());
			}
			int count = 1, count1 = 1;
			for (int y = 0; y < list.size(); y++) {
				int i = 1;
				count = 1;

				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "SelectShortListTwo");
				callableSt.setInt(2, clusterid);
				callableSt.setInt(3, list.get(y).getCmpId());
				callableSt.setInt(4, 0);

				callableSt.setInt(5, list.get(y).getCmpReportRoleId());
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				try {
				//	System.out.println("roleid:::" + list.get(y).getCmpRoleId() + " cmpid::" + list.get(y).getCmpId());
				//	System.out.println("i :: " + i + "  y ::: " + y);
					if (rs.next()) {
					//	System.out.println("count1: " + count1 + " count:: " + count);
						int j=1;
						//	System.out.println("count1: " + count1 + " count:: " + count);
						do {
							if (count < count1 && j==1) {
								row1 = sheet.getRow(count);
								count++;
							} else {
								row1 = sheet.createRow(i);
								j=0;
								count1++;
							}
							row1.createCell(y).setCellValue(rs.getString("EmailID"));

							i++;
						} while (rs.next());
					} else {
						//System.out.println("count1: " + count1 + " count:: " + count);
						if (count < count1) {
							row1 = sheet.getRow(count);
							count++;
						} else {
							row1 = sheet.createRow(i);
							count1++;
						}

						row1.createCell(y, Cell.CELL_TYPE_STRING).setCellValue("NA");

						i++;
					}

				} catch (SQLException e1) {
					System.out.println("Error creating download consider list of student");
					e1.printStackTrace();
				}
			}
			try {
				FileOutputStream fileOut = new FileOutputStream(downloadSL2File);
				workbook.write(fileOut);
				fileOut.close();
				Desktop.getDesktop().open(new File(downloadSL2File));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	@Override
	 public void getDownloadHL(List<CloseStatus> list, int clusterid,String downloadHLFile){
		final String procedureCall = "{call proc_getReport(?,?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			Workbook workbook = new XSSFWorkbook();
			Sheet sheet = workbook.createSheet("HotList");
			Row rowhead = sheet.createRow((int) 0);
			Row row1;
			for (int i = 0; i < list.size(); i++) {
				rowhead.createCell(i, Cell.CELL_TYPE_STRING)
						.setCellValue(list.get(i).getFirmName() + "-" + list.get(i).getRoleName());
			}
			int count = 1, count1 = 1;
			for (int y = 0; y < list.size(); y++) {
				int i = 1;
				count = 1;

				CallableStatement callableSt = connection.prepareCall(procedureCall);
				callableSt.setString(1, "SelecthotList");
				callableSt.setInt(2, clusterid);
				callableSt.setInt(3, list.get(y).getCmpId());
				callableSt.setInt(4, 0);

				callableSt.setInt(5, list.get(y).getCmpReportRoleId());
				ResultSet rs = null;
				rs = callableSt.executeQuery();
				try {
				//	System.out.println("roleid:::" + list.get(y).getCmpRoleId() + " cmpid::" + list.get(y).getCmpId());
				//	System.out.println("i :: " + i + "  y ::: " + y);
					if (rs.next()) {
					//	System.out.println("count1: " + count1 + " count:: " + count);
						int j=1;
						//	System.out.println("count1: " + count1 + " count:: " + count);
						do {
							if (count < count1 && j==1) {
								row1 = sheet.getRow(count);
								count++;
							} else {
								row1 = sheet.createRow(i);
								j=0;
								count1++;
							}
							row1.createCell(y).setCellValue(rs.getString("EmailID"));

							i++;
						} while (rs.next());
					} else {
						//System.out.println("count1: " + count1 + " count:: " + count);
						if (count < count1) {
							row1 = sheet.getRow(count);
							count++;
						} else {
							row1 = sheet.createRow(i);
							count1++;
						}

						row1.createCell(y, Cell.CELL_TYPE_STRING).setCellValue("NA");

						i++;
					}

				} catch (SQLException e1) {
					System.out.println("Error creating download consider list of student");
					e1.printStackTrace();
				}
			}
			try {
				FileOutputStream fileOut = new FileOutputStream(downloadHLFile);
				workbook.write(fileOut);
				fileOut.close();
				Desktop.getDesktop().open(new File(downloadHLFile));
			} catch (FileNotFoundException e1) {
				e1.printStackTrace();
			} catch (IOException e1) {
				e1.printStackTrace();
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
	}
	@Override
	 public void getDownloadPF(List<CloseStatus> list, int clusterid,String downloadPFFile){
		
		
	}
	@Override
	public JSONArray getroles(int firmid,int clusterid) {
		final String procedureCall = "{call proc_getReport(?,?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getRole");
			callableSt.setInt(2, clusterid);
			callableSt.setInt(3, firmid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object1=null;
			while (rs.next()) {
				object1=new JSONObject();
	        	object1.put("companyid", rs.getInt("Pk_DesignationId"));
	        	object1.put("companyname",  rs.getString("DesignationName"));
	        	array.put(object1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return array;
	}
	
	@Override
	public List<Cluster> getAllCluster() {
		List<Cluster> list = new ArrayList<Cluster>();
		final String procedureCall = "{call proc_getReport(?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getCluster");
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				Cluster designation = new Cluster();
				designation.setClusterName(rs.getString("CluserName"));
				designation.setClusterId(rs.getInt("pk_ClusterId"));
				list.add(designation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}

	@Override
	public JSONArray getfirms(int clusterid) {
		final String procedureCall = "{call proc_getReport(?,?)}";
		Connection connection = null;
		JSONArray array = new JSONArray();
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "getcompany");
			callableSt.setInt(2, clusterid);
			ResultSet rs = null;
			rs = callableSt.executeQuery();
			JSONObject object1=null;
			while (rs.next()) {
				object1=new JSONObject();
	        	object1.put("companyid", rs.getInt("Pk_CompanyId"));
	        	object1.put("companyname",  rs.getString("CompanyName"));
	        	array.put(object1);
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		
		return array;
	}
	@Override
	public List<CloseStatus> getAllRoleId(int cmpid, int clusterid, int roleid) {
		List<CloseStatus> list = new ArrayList<CloseStatus>();
		final String procedureCall = "{call proc_getReport(?,?,?,?)}";
		Connection connection = null;
		try {
			connection = jdbcTemplate.getDataSource().getConnection();
			CallableStatement callableSt = connection.prepareCall(procedureCall);
			callableSt.setString(1, "ALLRoleId");
			callableSt.setInt(2, clusterid);
			callableSt.setInt(3, cmpid);
			callableSt.setInt(4, roleid);
			ResultSet rs = callableSt.executeQuery();
			while (rs.next()) {
				CloseStatus designation = new CloseStatus();
				designation.setCmpReportRoleId(rs.getInt("Pk_CompanyRoleId"));
				designation.setCmpId(rs.getInt("Pk_CompanyId"));
				designation.setFirmName(rs.getString("CompanyName"));
				designation.setRoleName(rs.getString("DesignationName"));
				list.add(designation);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (connection != null)
				try {
					connection.close();
				} catch (SQLException e) {
					e.printStackTrace();
				}
		}
		return list;
	}
	}


