package com.precise.controller;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.Writer;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.SessionBean;
import com.precise.model.Interview;
import com.precise.service.InterviewService;


@Controller
public class InterviewController {

	@Autowired
	InterviewService interviewservice;
	
	@RequestMapping(value = "/getpreparation", method = RequestMethod.GET)
	public String getPreparation(ModelAndView model, HttpServletRequest request) {
		List<Interview> cmpList = interviewservice.getAllInterview();
		request.setAttribute("cmpList", cmpList);
		return "interview";
		
	}
	
	@RequestMapping(value={"/saveinterview"},method={RequestMethod.POST})
	public String saveInterview(Interview inter,HttpServletRequest request){
		System.out.println("interviewController.saveInterview()");
		SessionBean sessionBean = (SessionBean) request.getSession()
				.getAttribute("sessionBean");
		int userId=sessionBean.getUserID();
		
		try {
			List<String> questionList=inter.getQuestion();
		 	List<String> answerList=inter.getAnswer();
       //     System.out.println("qlist :: "+questionList.size());
       //     System.out.println("alist :: "+answerList.size());
            String defaultLocation = request.getServletContext().getInitParameter("defaultLocation");
            Interview cmpInterview = interviewservice.getCompanyName(inter.companyId);
            Writer writer = null;

            try {
            	File f = new File(defaultLocation+"Past Experiences\\Interview Experiences\\"+cmpInterview.companyName+"\\"+inter.year);
            	if(!f.exists()){
            		f.mkdirs();
            	}
            //	String fileName = "questionAnswers"+userId+".txt";
            	String date = new SimpleDateFormat("dd-MM-yyyy").format(new Date());
            	String fileName = cmpInterview.companyName+"_"+date.replaceAll("-", "") +".txt";
            	
                writer = new BufferedWriter(new OutputStreamWriter(
                      new FileOutputStream(defaultLocation+"Past Experiences\\Interview Experiences\\"+cmpInterview.companyName+"\\"+inter.year+"\\"+fileName), "utf-8"));
                writer.write("Question With Answer for "+ cmpInterview.companyName +" on year :"+inter.year + "\r\n" );
                writer.write("\r\n");
                for(int i=0;i < questionList.size();i++){
	                writer.write("Questions"+(i+1)+" : "+ questionList.get(i)+ "\r\n" );
	                writer.write("Answer:"+(i+1)+" :"+ answerList.get(i) + "\r\n");
	                writer.write("\r\n");
                }
            } catch (IOException ex) {
              	ex.printStackTrace();
            } finally {
               try {writer.close();} catch (Exception ex) {ex.printStackTrace();}
            }
            
			inter.setCreatedBy(userId);
			interviewservice.saveFirmManagementForm(inter);
			
		} catch (Exception e) {
			
			e.printStackTrace();
		}

		return "redirect:getpreparation";
	}
}
