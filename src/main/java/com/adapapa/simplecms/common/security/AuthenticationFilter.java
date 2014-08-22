package com.adapapa.simplecms.common.security;

import java.io.IOException;
import java.util.Enumeration;

import javax.servlet.Filter;
import javax.servlet.FilterChain;
import javax.servlet.FilterConfig;
import javax.servlet.ServletException;
import javax.servlet.ServletRequest;
import javax.servlet.ServletResponse;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.log4j.Logger;

import com.adapapa.simplecms.common.JSONSimpler;
import com.dreammore.framework.common.utils.Constants;

public class AuthenticationFilter implements Filter {
	
	protected Logger logger = Logger.getLogger(this.getClass());

	@Override
	public void init(FilterConfig filterConfig) throws ServletException {
		
	}

	@Override
	public void doFilter(ServletRequest request, ServletResponse response,
			FilterChain chain) throws IOException, ServletException {
		
		try {
			if (checkSession((HttpServletRequest) request, (HttpServletResponse) response) == false) {
				logger.warn("reject reqeust because user doesn't login");
				return;
			}

		} catch (Exception exp) {
			logger.warn("meet error:" + exp.getMessage());
		}

		chain.doFilter(request, response);
		
	}
	
	@Override
	public void destroy() {
		
	}

	private boolean checkSession(HttpServletRequest request, HttpServletResponse response) throws Exception {
		if (request.getRequestURI().indexOf("/toResource.do") >= 0 
				|| request.getRequestURI().indexOf("/toResourceList.do") >= 0 
				
			) {
			 return true;
		}

		if (request.getRequestURI().indexOf("/logout.do") >= 0) {
			clearSession(request.getSession());
			response.sendRedirect(((HttpServletRequest) request).getContextPath() + "/userinfo/login.do");
			return false;
		}

		// if session expired redirect request to login page except login
		// request
		if (request.getRequestURI().indexOf("/userinfo/") < 0 && request.getSession().getAttribute(Constants.USER_PRINCIPAL) == null) {

			// ajax超时处理
			if (request.getHeader("x-requested-with") != null && request.getHeader("x-requested-with").equalsIgnoreCase("XMLHttpRequest")) {
				response.addHeader("sessionstatus", "timeout");
				response.setStatus(500);
				SessionData timeOutData = new SessionData();
				timeOutData.setRedirectUrl(request.getContextPath() + "/userinfo/login.do");
				response.getWriter().write(JSONSimpler.toJson(timeOutData));
				return false;
			}
			StringBuilder js = new StringBuilder();
			js.append("<script type=\"text/javascript\">");
			js.append(" top.document.location.href = \"");
			js.append(((HttpServletRequest) request).getContextPath()).append("/userinfo/login.do\"");
			js.append("</script>");
			((HttpServletResponse) response).setStatus(510);
			response.getOutputStream().write(js.toString().getBytes());
			return false;
		}

		return true;
	}

	private void clearSession(HttpSession session) {
		Enumeration<?> attrs = session.getAttributeNames();
		while (attrs.hasMoreElements()) {
			Object attr = attrs.nextElement();
			if (attr instanceof String){
				session.removeAttribute((String) attr);
			}
		}
	}

	
	

}
