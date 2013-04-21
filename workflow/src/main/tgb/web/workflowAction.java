package main.tgb.web;

import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import net.sf.json.JSONObject;

import org.jbpm.api.ProcessDefinition;

import test.ProcessD;


import main.core.bpm.IWorkFlowManager;
import main.core.util.BaseAction;
import main.core.util.BeanTools;

/**
 * 流程基础信息管理Action
 * @author Administrator
 *
 */
public class workflowAction extends BaseAction  {

	private static final long serialVersionUID = 6862468434373120128L;
	private IWorkFlowManager workflowManager; //流程管理
	private JSONObject resultObj = null;
	private File workflowFile;
	private String workflowFileFileName; //文件名称
	private String workflowFileContentType; //文件类型
	private String result = "error";
	private int page; //easyUI分页当前页
	private int rows; //easyUI每页记录数
	private String deployId; //流程部署ID
	
	public String getDeployId() {
		return deployId;
	}
	public void setDeployId(String deployId) {
		this.deployId = deployId;
	}
	public int getPage() {
		return page;
	}
	public void setPage(int page) {
		this.page = page;
	}
	public int getRows() {
		return rows;
	}
	public void setRows(int rows) {
		this.rows = rows;
	}
	public String getResult() {
		return result;
	}
	public void setResult(String result) {
		this.result = result;
	}
	public String getWorkflowFileFileName() {
		return workflowFileFileName;
	}
	public void setWorkflowFileFileName(String workflowFileFileName) {
		this.workflowFileFileName = workflowFileFileName;
	}
	public String getWorkflowFileContentType() {
		return workflowFileContentType;
	}
	public void setWorkflowFileContentType(String workflowFileContentType) {
		this.workflowFileContentType = workflowFileContentType;
	}
	public File getWorkflowFile() {
		return workflowFile;
	}
	public void setWorkflowFile(File workflowFile) {
		this.workflowFile = workflowFile;
	}
	public JSONObject getResultObj() {
		return resultObj;
	}
	public void setResultObj(JSONObject resultObj) {
		this.resultObj = resultObj;
	}
	public void setWorkflowManager(IWorkFlowManager workflowManager) {
		this.workflowManager = workflowManager;
	}
	/**
	 * 模拟登录,进入主页面
	 * @return
	 */
	public String login() throws Exception{
		return "main";
	}
	/**
	 * 查询全部流程定义
	 * @return
	 */
	public String getProcessDefinitionList() {
		List<ProcessDefinition> pd = workflowManager.getProcessDefinitions(page,rows);
		int total = workflowManager.getTotalSize();
		List<ProcessD> pd1 = new ArrayList<ProcessD>();
		ProcessD p = null;
		try{
		for(int i=0;i<pd.size();i++){
			p = new ProcessD();
			BeanTools.copyProperties(p, (ProcessDefinition)pd.get(i));
			pd1.add(p);
		}
		}catch(Exception e){
			e.printStackTrace();
		}
		Map<String,Object> map = new HashMap<String,Object>();
		
		map.put("total", total);
		map.put("rows", pd1);
		resultObj= JSONObject.fromObject(map);
		return "datagrid";
	}
	
	public String deployProcess(){
		workflowManager.deployProcess();
		return "main";
	}
	/**
	 * 访问上传流程定义页面
	 * @return
	 */
	public String toDeploy(){
		return "toDeploy";
	}
	/**
	 * 部署流程
	 * @return
	 */
	public String deployProcessReal(){
		try{
			if(workflowFile != null){
				InputStream in = new FileInputStream(workflowFile);
				ZipInputStream zip = new ZipInputStream(in);
				workflowManager.deployProcess(zip);
				in.close();
				zip.close();
				result = "successful";
			}
		} catch (Exception e) {
			e.printStackTrace();
		} 
		return "workflowMgr";
	}
	/**
	 * 删除流程
	 * @return
	 */
	public String deleteProcess(){
		try{
			workflowManager.deleteProcess(deployId);
			result = "successful";
		}catch(Exception e){
			e.printStackTrace();
		}
		return "result";
	}
}
