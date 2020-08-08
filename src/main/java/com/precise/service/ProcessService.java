package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import com.precise.model.ProcessManagement;

public interface ProcessService {
	public String submitProces(ProcessManagement process) throws SQLException;
	public List<ProcessManagement> getAllProcess();
	public String editProcessForm(ProcessManagement process) throws SQLException ;
	public String deleteProcessData(ProcessManagement process);

}
