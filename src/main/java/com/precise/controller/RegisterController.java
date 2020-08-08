package com.precise.controller;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Forum;
import com.precise.model.Question;
import com.precise.model.Register;
import com.precise.model.SessionBean;
import com.precise.model.ThreadBlog;
import com.precise.service.RegisterService;
import com.precise.service.ThreadService;
import com.precise.util.PasswordEncryption;
import com.precise.util.PasswordEncryptionDecryption;
import com.precise.util.PasswordEncryptionException;

@Controller
public class RegisterController {
	@Autowired
	RegisterService registerService;
	@Autowired
	ThreadService threadService;

	public RegisterController() {
		// System.out.println("RegisterController.RegisterController()
		// d.cons.");
	}

	@RequestMapping(value = "/getRegistrationPage", method = RequestMethod.GET)
	public ModelAndView getRegistrationPage(ModelAndView model, HttpServletRequest req) {
		// System.out.println("RegisterController.getRegistrationPage()");

		model.setViewName("registrationForm");
		return model;
	}

	@RequestMapping(value = "/saveRegistration", method = RequestMethod.POST)
	public String saveRegistration(Register reg) throws ParseException {// @ModelAttribute("Register")
		// System.out.println("RegisterController.saveRegistration()");
		try {
			String encryptedPassword = PasswordEncryption.getInstance().encrypt(reg.getLoginPassword());
			// System.out.println("encrypted password== "+encryptedPassword);

			PasswordEncryptionDecryption dec = new PasswordEncryptionDecryption();

			// String decryptedPassword=dec.DecryptText(encryptedPassword);
			// System.out.println("decrypted password=="+decryptedPassword);

			String encpw = dec.EncryptText(reg.getLoginPassword());
			String decrypt = dec.DecryptText(encpw);

			reg.setLoginPassword(encpw);

			// System.out.println("enc pwd= "+encpw);
			// System.out.println("dec pwd= "+decrypt);

		} catch (PasswordEncryptionException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		;

		java.util.Date date = new java.util.Date();
		date = reg.getDateOfBirth();
		// java.sql.Timestamp sqlTimeStamp = new
		// java.sql.Timestamp(date.getTime());
		java.sql.Date dob = new java.sql.Date(date.getTime());
		// System.out.println("sql date "+dob);

		/*
		 * String dateForMySql = ""; SimpleDateFormat sdf = new
		 * SimpleDateFormat("yyyy-MM-dd"); dateForMySql = sdf.format(date);
		 * System.out.println("simple date format"+dateForMySql);
		 */

		reg.setDateOfBirth(dob);

		registerService.saveRegistration(reg);
		// model.setViewName("registrationForm");
		return "registrationForm";
	}

	@RequestMapping(value = "/getAdminDashboardPage", method = RequestMethod.GET)
	public ModelAndView getAdminPage(ModelAndView model, HttpServletRequest req) {
		// System.out.println("RegisterController.getAdminPage()");
		List<ThreadBlog> wcpost = threadService.getReply();
		req.setAttribute("post", wcpost);
		model.setViewName("addQuestion");
		return model;
	}

	@RequestMapping(value = "/addQuestion", method = RequestMethod.POST)
	public ModelAndView saveQuestions(Question ques, HttpServletRequest request, ModelMap modelmap) {
		System.out.println("inside save method:: "+ request.getParameter("threadId"));
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		registerService.saveQuestions(ques, sessionBean.getUserID(),sessionBean.getRoleID());
		System.out.println("userName::" + sessionBean.getName());
		System.out.println("threadId::"+request.getParameter("threadId"));
		List<Question> quesiton = registerService.getAllQuestion(request.getParameter("threadId"));
	//	List<Question> quesiton = registerService.getComQuestions(request.getParameter("threadId"));
		
		ModelAndView returnResult = new ModelAndView("questionView");
		returnResult.addObject("quesiton", quesiton);
		returnResult.addObject("threadId", request.getParameter("threadId"));
		returnResult.addObject("userName", sessionBean.getUserID());
		/* returnResult.addObject("formType","ADD"); */
		modelmap.addAttribute("formType", "ADD");
		return returnResult;
	}

	@RequestMapping(value = "/viewQuestions", method = {RequestMethod.POST,RequestMethod.GET})
	public ModelAndView viewQuestions(HttpServletRequest request) {
		List<Question> quesiton = registerService.getAllQuestion(request.getParameter("threadId"));
		
		// model.setViewName("addQuestion");
		ModelAndView returnModel = new ModelAndView("questionView");
		returnModel.addObject("threadId", request.getParameter("threadId"));
		returnModel.addObject("forumId", request.getParameter("forumId"));
		returnModel.addObject("quesiton", quesiton);
		return returnModel;
	}
@RequestMapping(value = "/viewComQuestions", method = RequestMethod.GET)
public ModelAndView getComQuestions(HttpServletRequest request) throws SQLException {
	//System.out.println(request.getParameter("cmpId"));
	String threadId=registerService.getThreadId(request.getParameter("cmpId"));
	
	List<Question> quesiton = registerService.getComQuestions(request.getParameter("cmpId"));
	System.out.println("companyID::::"+request.getParameter("cmpId"));
	ModelAndView returnModel = new ModelAndView("questionView","quesiton",quesiton);
	returnModel.addObject("threadId",threadId);
	returnModel.addObject("formType", "ADD");
	// model.setViewName("addQuestion");
	
	return returnModel;
}
	@RequestMapping(value = "/editQuestions", method = RequestMethod.POST)
	public String editQuestion(HttpServletRequest request, ModelMap modelMap) {
		System.out.println("questionId ::555555555555" + request.getParameter("questionId"));
		System.out.println("threadId::" + request.getParameter("threadId"));
		Map<String, String> map = registerService.getQuestionData(request.getParameter("questionId"));
		modelMap.addAttribute("questionId", map.get("questionId"));
		modelMap.addAttribute("question", map.get("question"));
		modelMap.addAttribute("threadId", request.getParameter("threadId"));
		modelMap.addAttribute("formType", "EDIT");
		return "addQuestion";
	}

	@RequestMapping(value = "/editQuestionvalues", method = RequestMethod.POST)
	public ModelAndView editQuestionValue(HttpServletRequest request) {
		String returnJsp = "";
		ModelAndView returnModel;
		String returnResult = registerService.updateQuestionData(request.getParameter("question"),
				request.getParameter("questionId"));
		if (returnResult.equalsIgnoreCase("success")) {
			List<Question> quesiton = registerService.getAllQuestion(request.getParameter("threadId"));
			returnModel = new ModelAndView("questionView");
			returnModel.addObject("threadId", request.getParameter("threadId"));
			returnModel.addObject("quesiton", quesiton);
		} else {
			returnModel = new ModelAndView("Error");
		}
		return returnModel;
	}

	@RequestMapping(value = "/deleteQuestion", method = RequestMethod.POST)
	public ModelAndView deleteQuestion(HttpServletRequest request) {
		System.out.println("ques vcalue   " + request.getParameter("questionId"));
		ModelAndView returnModel;
		String returnResult=registerService.deleteQuestion(request.getParameter("questionId"));
		if (returnResult.equalsIgnoreCase("success")) {
		List<Question> quesiton = registerService.getAllQuestion(request.getParameter("threadId"));
		returnModel = new ModelAndView("questionView");
		returnModel.addObject("threadId", request.getParameter("threadId"));
		returnModel.addObject("quesiton", quesiton);
		}
		else {
			returnModel = new ModelAndView("Error");
		}
		return returnModel;
	}

}
