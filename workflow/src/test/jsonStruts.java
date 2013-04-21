package test;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.List;
import java.util.zip.ZipInputStream;

import main.core.bpm.IWorkFlowManager;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;

public class jsonStruts {

	private String userName;
	
	private String password;
	
	private IWorkFlowManager workflowManager; //流程管理
	
	 public File getImage() {
		return image;
	}


	public void setImage(File image) {
		this.image = image;
	}


	public String getImageFileName() {
		return imageFileName;
	}


	public void setImageFileName(String imageFileName) {
		this.imageFileName = imageFileName;
	}


	public String getImageContentType() {
		return imageContentType;
	}


	public void setImageContentType(String imageContentType) {
		this.imageContentType = imageContentType;
	}

	private File image; //上传的文件
	 private String imageFileName; //文件名称
	 private String imageContentType; //文件类型
	 
	 
	 public String toUplad(){
		 return "toDemoUpload";
	 }
	
	public String upload(){
		try{
			if(image !=null){
				InputStream in = new FileInputStream(image);
				ZipInputStream zip = new ZipInputStream(in);
				workflowManager.deployProcess(zip);
				in.close();
				zip.close();
			}
		}catch(Exception e){
			e.printStackTrace();
		}
		return "success";
	}


	public String test1() throws Exception{
		System.out.println("test1");
		User u = new User();
		//u.setId(1);
		u.setPassword("password");
		u.setUserName("name");
		List list = new ArrayList();
		list.add(u);
		list.add("里2");
		list.add("里3");
		
		JSONArray resultObjs = JSONArray.fromObject(list);
		System.out.println(resultObjs);
		JSONObject jb = JSONObject.fromObject(u);
		System.out.println(jb);
		return "success";
	}

	public void setWorkflowManager(IWorkFlowManager workflowManager) {
		this.workflowManager = workflowManager;
	}

	public String getUserName() {
		return userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
}
