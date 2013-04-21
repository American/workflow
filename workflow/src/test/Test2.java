package test;

import net.sf.json.JSONObject;

import org.jbpm.api.Configuration;
import org.jbpm.api.ProcessEngine;

import junit.framework.TestCase;

public class Test2 extends TestCase {

	public void add(){
		Test1<String> t1 = new Test1<String>();
		t1.add();
	}
	
	public void deploy(){
		ProcessEngine processEngine = Configuration.getProcessEngine();
	}
	
	public void testJson(){
		User u = new User();
		//u.setId(1);
		u.setPassword("1");
		u.setUserName("2");
		JSONObject resultObjs = JSONObject.fromObject(u);
		System.out.println(resultObjs);
	}
}
