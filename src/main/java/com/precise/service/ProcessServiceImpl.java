package com.precise.service;

import java.sql.SQLException;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.precise.dao.ProcessDAO;
import com.precise.model.ProcessManagement;

@Service("processService")
@Transactional
public class ProcessServiceImpl implements ProcessService {
	@Autowired
	ProcessDAO processDAO;
	
	public String submitProces(ProcessManagement process) throws SQLException{
		return processDAO.submitProces(process) ;
	}
	
	@Override
	public List<ProcessManagement> getAllProcess() {
		return processDAO.getAllProcess();
	}
	@Override
	public String editProcessForm(ProcessManagement process) throws SQLException {
		return processDAO.editProcessForm(process) ;
	}
	@Override
	public String deleteProcessData(ProcessManagement process){
		return processDAO.deleteProcessData(process);
	}
	
	
}
