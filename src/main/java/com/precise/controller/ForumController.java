package com.precise.controller;

import java.util.List;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.precise.model.Forum;
import com.precise.model.Reply;
import com.precise.model.SessionBean;
import com.precise.model.ThreadBlog;
import com.precise.model.UserBean;
import com.precise.service.ForumService;

@Controller
public class ForumController {
	@Autowired
	ForumService forumService;

	public ForumController() {
		System.out.println("ForumController.ForumController() d.cons.");
	}

	@RequestMapping(value = "/getCreateForumPage", method = RequestMethod.POST)
	public ModelAndView getCreateForumPage(ModelAndView model, HttpServletRequest req) {
		List<ThreadBlog> permission = forumService.getPostPermission();
		req.setAttribute("permission", permission);
		model.setViewName("createForumPage");
		return model;
	}

	@RequestMapping(value = "/saveForum", method = RequestMethod.POST)
	public ModelAndView saveForum(ModelAndView model, Forum forum,HttpServletRequest request) {
		SessionBean sessionBean = (SessionBean) request.getSession().getAttribute("sessionBean");
		int roleId = sessionBean.getRoleID();
		forumService.saveForum(forum);
		System.out.println(forum.getReplyPermission());
		List<Forum> allForumData = forumService.getAllFourm(roleId);
		return new ModelAndView("forumDashboard", "forum", allForumData);
	}

	@RequestMapping(value = "/editForum", method = RequestMethod.POST)
	public ModelAndView editForum(Forum forum, HttpServletRequest req) {
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int roleId = sessionBean.getRoleID();
		Map<String, String> mapValue = forumService.getForumData(req.getParameter("forumId"));
		List<ThreadBlog> permission = forumService.getPostPermission();
		// forumService.updateForum(forum);
		List<Forum> allForumData = forumService.getAllFourm(roleId);
		req.setAttribute("forum", allForumData);
		ModelAndView modelMapValue = new ModelAndView("createForumPage");
		modelMapValue.addObject("permission", permission);
		modelMapValue.addObject("forumName", mapValue.get("ForumName"));
		modelMapValue.addObject("editPermission", mapValue.get("editPermission"));
		modelMapValue.addObject("deletePermission", mapValue.get("deletePermission"));
		modelMapValue.addObject("postPermission", mapValue.get("postPermission"));
		modelMapValue.addObject("replyPermission", mapValue.get("replyPermission"));
		modelMapValue.addObject("ForumId", req.getParameter("forumId"));
		return modelMapValue;
	}

	@RequestMapping(value = "/editForumValues", method = RequestMethod.POST)
	public String updateForumValues(Forum forum,HttpServletRequest request) {
		request.getParameter("forumId::"+request.getParameter("forumId"));
		//System.out.println("ForumId value in updateForumValues method::" + forum.getForumId());
		//System.out.println("ForumId value in updateForumValues method::" + forum.getEditPermission());
		forumService.updateForum(forum);
		return "redirect:forumHomePage";
	}

	@RequestMapping(value = "/deleteForum", method = RequestMethod.POST)
	public ModelAndView deleteForum(Forum forum, HttpServletRequest req,Model model) {
		SessionBean sessionBean = (SessionBean) req.getSession().getAttribute("sessionBean");
		int roleId = sessionBean.getRoleID();
		forumService.deleteForum(forum.getForumId());
		List<Forum> allForumData = forumService.getAllFourm(roleId);
		// req.setAttribute("forum", allForumData);
		model.addAttribute("forumDeleted","Forum deleted!!");
		return new ModelAndView("forumDashboard", "forum", allForumData);
	}

	@RequestMapping(value = "/getForumReplyPage", method = RequestMethod.GET)
	public ModelAndView getForumReplyPage(HttpServletRequest req, UserBean userBean) {
		return new ModelAndView("forum");
	}

	@RequestMapping(value = "/forumHomePage")
	public ModelAndView forumHomePage(HttpServletRequest request) {
		int roleId;
		try	{
			SessionBean sbean=(SessionBean)request.getSession().getAttribute("sessionBean");
			roleId=sbean.getRoleID();
			if(roleId!=6)
			{
				return new ModelAndView("LoginPage");
			}
		}catch(Exception e)
		{
			return new ModelAndView("LoginPage");
		}
		System.out.println("role id :: "+roleId);
		
		List<Forum> allForumData = forumService.getAllFourm(roleId);
		return new ModelAndView("forumDashboard", "forum", allForumData);
	}

	@RequestMapping(value = "/questionReplyDisplay")
	public ModelAndView getAllReplyPageByQuestion(HttpServletRequest req) {
		int questionId = Integer.parseInt(req.getParameter("question"));
		int threadreplyId = Integer.parseInt(req.getParameter("threadId"));
		List<Reply> allReply = forumService.getAllReplyByQuestionId(questionId);
		
		List<ThreadBlog> replyId= forumService.getReplyId(threadreplyId);
		
		req.setAttribute("replyId", replyId.get(0).getReplyPermissionId());
		req.setAttribute("replyShareRMId", replyId.get(0).getReplyPermissionShareRMId() );
		
		System.out.println("threadId ::" +threadreplyId);
		System.out.println("replyId" + replyId.get(0).getReplyPermissionId()+"replyShareRMId"+ replyId.get(0).getReplyPermissionShareRMId() );
		
		if(allReply.size()>0){
		req.setAttribute("repList", allReply);
		req.setAttribute("userName",allReply.get(0).getUserName());
		req.setAttribute("roleName", allReply.get(0).getRoleName());
		}
		req.setAttribute("threadId", req.getParameter("threadId"));
		req.setAttribute("forumId", req.getParameter("forumId"));
		return new ModelAndView("replyPage", "questionId", questionId);
	}

	@RequestMapping(value = "/saveReply", method = RequestMethod.POST)
	public ModelAndView saveReply(ModelAndView model, Reply reply, HttpServletRequest req) {
		System.out.println("inside save reply method forumId::"+req.getParameter("forumId"));
		forumService.saveReply(reply);
		List<Reply> allReply = forumService.getAllReplyByQuestionId(reply.getQuestionId());
		req.setAttribute("userName",allReply.get(0).getUserName());
		req.setAttribute("roleName", allReply.get(0).getRoleName());
		req.setAttribute("repList", allReply);
		req.setAttribute("questionId", reply.getQuestionId());
		req.setAttribute("threadId", req.getParameter("threadId"));
		req.setAttribute("forumId", req.getParameter("forumId"));
		List<ThreadBlog> replyId= forumService.getReplyId(Integer.parseInt(req.getParameter("threadId").toString()==null?"0":req.getParameter("threadId").toString()));
		
		req.setAttribute("replyId", replyId.get(0).getReplyPermissionId());
		req.setAttribute("replyShareRMId", replyId.get(0).getReplyPermissionShareRMId() );
		
		return new ModelAndView("replyPage");
	}

	@RequestMapping(value = "/openGoogleApi")
	public String googleApi() {
		return "GoogleApi";
	}

}
