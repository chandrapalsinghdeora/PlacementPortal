package com.precise.dao;

import java.sql.SQLException;
import java.util.List;
import com.precise.model.ProcessManagement;

public interface ProcessDAO {
	public String submitProces(ProcessManagement process) throws SQLException;
	public List<ProcessManagement> getAllProcess();
	public String editProcessForm(ProcessManagement process) throws SQLException ;
	public String deleteProcessData(ProcessManagement process);

}
