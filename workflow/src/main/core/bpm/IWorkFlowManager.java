package main.core.bpm;

import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import main.core.util.PageModel;

import org.jbpm.api.ProcessDefinition;






public interface IWorkFlowManager {

	/**
	 * 获取已部署的流程（流程定义）
	 * @param keyword 查询关键字
	 * @param pageNo 页码
	 * @param pageSize 每页记录数
	 * @return
	 */
	public PageModel<ProcessDefinition> getProcessDefinitions(String keyword,int pageNo,int pageSize);
	/**
	 * 部署流程
	 * @param zip
	 */
	public void deployProcess(ZipInputStream zip);
	/**
	 * 一处流程,包含流程的下属流程实例
	 * @param deployId
	 */
	public void deleteProcess(String deployId);
	
	public List<ProcessDefinition> getProcessDefinitions(int pageNo,int pageSize);
	
	public int getTotalSize();
	
	public void deployProcess();
	
	public void createInstance(String key,Map<String,Object> var);
	
}
