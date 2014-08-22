package com.adapapa.simplecms.common.security;

import java.lang.reflect.Method;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.web.servlet.HandlerInterceptor;
import org.springframework.web.servlet.ModelAndView;

import com.adapapa.simplecms.domain.model.RoleInfo;
import com.adapapa.simplecms.domain.model.UserInfo;
import com.dreammore.framework.common.utils.Constants;
import com.dreammore.framework.common.utils.Tools;

public class CheckPermissionInterceptor implements HandlerInterceptor{

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws Exception {
		
		if (request.getRequestURI().endsWith(".do") ) {
			String methodName = request.getRequestURI().substring(request.getRequestURI().lastIndexOf("/") + 1, 
					request.getRequestURI().indexOf(".do"));
			Method[] methods = handler.getClass().getDeclaredMethods();
			for (Method method : methods) {
				//System.out.println(method.getName());
				if (method.getName().equals(methodName)) {
					CheckPermission checkPermission = method.getAnnotation(CheckPermission.class);
					boolean result = false;
					if (!Tools.empty(checkPermission)) {
						PermissionType[] permissionTypes = checkPermission.value();
						UserInfo userInfo = (UserInfo) request.getSession().getAttribute(Constants.USER_PRINCIPAL);
						if (Tools.empty(userInfo)) {
							throw new RuntimeException("Not logged!");
						}
						for (PermissionType permissionType : permissionTypes) {
							result = result || checkPermission(userInfo.getRoleInfo(), permissionType); 
						}
						if (!result) {
							throw new RuntimeException("No Permission");
						} 
					}
				}
			}
			
		}
		
		return true;
	}

	@Override
	public void postHandle(HttpServletRequest request, HttpServletResponse response, Object handler,
			ModelAndView modelAndView) throws Exception {
	}

	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
	}
	
	private boolean checkPermission(RoleInfo roleInfo, PermissionType permissionType){
		if (permissionType == PermissionType.SERVICER) {
			return roleInfo == RoleInfo.SERVICER; 
		}else if (permissionType == PermissionType.ADMINISTRATOR) {
			return roleInfo == RoleInfo.ADMINISTRATOR ;
		}else if (permissionType == PermissionType.EDITOR) {
			return roleInfo == RoleInfo.EDITOR;
		}
		return false;
		
	}

}
