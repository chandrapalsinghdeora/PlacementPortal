package com.precise.intercepter;

import java.net.URLDecoder;
import java.net.URLEncoder;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import com.precise.model.SessionBean;


public class SessionManagement extends HandlerInterceptorAdapter {

	private static Logger logger = Logger.getLogger(SessionManagement.class);

	@Override
	public boolean preHandle(HttpServletRequest request,
			HttpServletResponse response, Object handler) throws Exception {
		response.setContentType("text/html; charset=UTF-8");// For Special
															// characters
		HttpSession session = request.getSession(false);
		String uri = request.getRequestURI();
		
		logger.info("request uri " + uri);
		System.out.println("uri value::" + uri);
		if (!uri.endsWith("login") && !uri.endsWith("getshortlistReleaseCompanyHRPage")
				&& !uri.endsWith("getshortlistReceiveCompanyHRPage") && !uri.endsWith("getCompanyHRPage") 
				&& !uri.endsWith("getInfoValuesWithoutSession")  && !uri.endsWith("getGreetingsWithoutSession")
				&& !uri.endsWith("saveHRShortlistedWithoutSession") && !uri.endsWith("saveHRHotListedWithoutSession")  
				&& !uri.endsWith("HRprocessDoneHotlisted") && !uri.endsWith("saveSendMail")
				&& !uri.endsWith("pdfmergeWOSession")&& !uri.endsWith("downloadHRCv")
				&& !uri.endsWith("getFileManagerWithoutSession")) {
			if (session != null) {
				logger.info("session is not null:::::::::::::::::::::::");
				SessionBean sessionBean = (SessionBean) request.getSession()
						.getAttribute("sessionBean");
				//logger.info("session vale in intercepter ::"+sessionBean.getUserName());
				return true;
			} else {
				if (request.getSession().getAttribute("sessionBean") == null) {
					logger.info("session expired");
					logger.info("resquest header "
							+ request.getHeader("X-Requested-With"));
					if (request.getHeader("X-Requested-With") != null
							&& request.getHeader("X-Requested-With")
									.equalsIgnoreCase("ajax")) {
						// Ajax requests
						System.out.println("inside if condition::");
						logger.info("inside ajax request session expired");
						response.setStatus(HttpServletResponse.SC_FORBIDDEN);
						response.sendRedirect("login");
						return false;
					} else {
						// Non Ajax requests
						System.out.println("inside else condition::");
						logger.info("inside non ajax request session expired");
						response.sendRedirect("login");
						return false;
					}
				}
			}
		} else {
			System.out.println("session not checked:::::::::::::::::::::");
		}
		return true;
	}

}
