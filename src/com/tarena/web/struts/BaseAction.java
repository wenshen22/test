package com.tarena.web.struts;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;

@SuppressWarnings("serial")
public class BaseAction extends ActionSupport 
						implements 
							SessionAware,
							ServletRequestAware,
                            ServletResponseAware{
	
	protected HttpServletRequest request;
	protected HttpServletResponse response;
	protected HttpSession session;
	protected Map<String,Object> sessionMap;
	
	public void setSession(Map<String, Object> sessionMap) {
		this.sessionMap = sessionMap;
		
	}

	public void setServletResponse(HttpServletResponse arg0) {
		this.response = arg0;
		
	}

	public void setServletRequest(HttpServletRequest arg0) {
		this.request = arg0;
		
	}

}
