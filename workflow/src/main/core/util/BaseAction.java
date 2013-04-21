package main.core.util;

import java.util.Map;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.struts2.dispatcher.SessionMap;
import org.apache.struts2.interceptor.ServletRequestAware;
import org.apache.struts2.interceptor.ServletResponseAware;
import org.apache.struts2.interceptor.SessionAware;

import com.opensymphony.xwork2.ActionSupport;


public class BaseAction extends ActionSupport implements SessionAware, ServletRequestAware, ServletResponseAware {

	private static final long serialVersionUID = -2999616889628004954L;
	protected HttpServletRequest  request;
	protected HttpServletResponse response;
	protected SessionMap session;

	@Override
	public void setServletResponse(HttpServletResponse response) {
		this.response = response;
	}

	@Override
	public void setServletRequest(HttpServletRequest request) {
		this.request = request;
	}

	@Override
	public void setSession(Map<String, Object> map) {
		this.session = (SessionMap)map;
	}

	
}
