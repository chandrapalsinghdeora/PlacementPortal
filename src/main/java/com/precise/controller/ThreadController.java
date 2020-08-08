package com.precise.controller;

import java.text.ParseException;
import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import com.precise.model.Forum;
import com.precise.model.ThreadBlog;
import com.precise.model.UserBean;
import com.precise.service.ThreadService;

@Controller
public class ThreadController {
	@Autowired

	ThreadService threadService;

	public ThreadController() {

	}

	@RequestMapping(value = "/getThreadPage")
	public ModelAndView getThradPage(ModelAndView model, HttpServletRequest req) {
		List<ThreadBlog> fname = threadService.getPost(req.getParameter("forumId"));
		List<ThreadBlog> wcpost = threadService.getReply();
		List<ThreadBlog> wcedit = threadService.getEdit();
		List<ThreadBlog> wcdelete = threadService.getDelete();
		req.setAttribute("fn", fname);
		req.setAttribute("post", wcpost);
		req.setAttribute("edit", wcedit);
		req.setAttribute("delete", wcdelete);
		ModelAndView returnModel = new ModelAndView("addThread"); 
		returnModel.addObject("forumId", req.getParameter("forumId"));
		returnModel.addObject("formType", "ADD");
		return returnModel;// "addThread";
	}

	@RequestMapping(value = "/saveThrad", method = { RequestMethod.POST})
	public ModelAndView saveThrad(@ModelAttribute("ThreadBlog") ThreadBlog threadBlog, HttpServletRequest request) throws ParseException {
		String forumId = request.getParameter("forumId");
		System.out.println("forumId:::::::::::"+forumId);
		System.out.println("inside save thread method forum id::"+forumId);
		threadService.saveThrad(threadBlog);
		List<ThreadBlog> allForumData = threadService.getAllThread(forumId);
		ModelAndView returnModel = new ModelAndView("ThreadFormView"); 
		returnModel.addObject("forum",allForumData );
		returnModel.addObject("forumId", forumId);
		return returnModel;

	}

	@RequestMapping(value = "/dispayThreadData", method = { RequestMethod.POST, RequestMethod.GET })
	public ModelAndView displayThreadData(HttpServletRequest request) throws ParseException {
		System.out.println("ForumId:::&&&&&&&&&&&"+request.getParameter("forumId"));
		String forumId = request.getParameter("forumId");
		List<ThreadBlog> allForumData = threadService.getAllThread(forumId);
		ModelAndView result = new ModelAndView("ThreadFormView"); 
		result.addObject("forum", allForumData);
		result.addObject("forumId", forumId);		
		request.setAttribute("thredDeleted", request.getParameter("thredDeleted"));
		//return new ModelAndView("ThreadFormView", "forum", allForumData);
		return result;
	}
	
	@RequestMapping(value="/editThread" ,method={RequestMethod.POST})
	public ModelAndView editThreadValue(ModelAndView model, HttpServletRequest req){
		System.out.println("threadId:::"+req.getParameter("threadId"));
		Map<String,Object> values = threadService.getForumName(req.getParameter("threadId").toString());
		List<ThreadBlog> fname=(List<ThreadBlog>) values.get("ForumNameList");
		List<ThreadBlog> wcpost = threadService.getReply();
		List<ThreadBlog> wcedit = threadService.getEdit();
		List<ThreadBlog> wcdelete = threadService.getDelete();
		req.setAttribute("fn", fname);
		req.setAttribute("post", wcpost);
		req.setAttribute("edit", wcedit);
		req.setAttribute("delete", wcdelete);
		Map<String,String> mapValue=(Map<String, String>) values.get("permissionData");
		System.out.println("editPermission::"+mapValue.get("editPermission"));
		ModelAndView returnModel = new ModelAndView("addThread"); 
		returnModel.addObject("forumId", req.getParameter("forumId"));
		returnModel.addObject("threadName",mapValue.get("threadName").toString());
		returnModel.addObject("editPermission",mapValue.get("editPermission"));
		returnModel.addObject("deletePermission",mapValue.get("deletePermission"));
		returnModel.addObject("postPermission",mapValue.get("postPermisssion"));
		returnModel.addObject("formType","EDIT");
		returnModel.addObject("threadId", req.getParameter("threadId"));
		return returnModel;// "addThread";
	}
	
	@RequestMapping(value="/editThreadData" , method = { RequestMethod.POST})
	public String editThreadData(@ModelAttribute("ThreadBlog") ThreadBlog threadBlog,HttpServletRequest request){
		String returnJsp="";
		String returnValue=threadService.updateThreadData(threadBlog,request.getParameter("threadId"));
		System.out.println("returnJspValue::;"+returnValue);
		if(returnValue.equalsIgnoreCase("success")){
			returnJsp="redirect:dispayThreadData?forumId="+request.getParameter("forumId");
		}else{
			returnJsp="Error";
		}
		return returnJsp;
		
	}
	
	@RequestMapping(value="/deleteThread",method={ RequestMethod.POST})
	public String deleteThreadData(HttpServletRequest request,Model model){
		System.out.println("threadId::"+request.getParameter("threadId"));
		String returnJsp;
		String returnValue=threadService.deleteThread(request.getParameter("threadId"));
		if(returnValue.equalsIgnoreCase("success")){
			returnJsp="redirect:dispayThreadData?forumId="+request.getParameter("forumId");
		}else{
			returnJsp="Error";
		}
		model.addAttribute("thredDeleted","Thread deleted!!");
		return returnJsp;
		
	}

}
