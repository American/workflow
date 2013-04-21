package main.core.bpm.impl;


import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

import main.core.bpm.IWorkFlowManager;
import main.core.util.Page;
import main.core.util.PageModel;

import org.jbpm.api.ExecutionService;
import org.jbpm.api.HistoryService;
import org.jbpm.api.IdentityService;
import org.jbpm.api.ProcessDefinition;
import org.jbpm.api.ProcessEngine;
import org.jbpm.api.RepositoryService;
import org.jbpm.api.TaskService;
import org.jbpm.pvm.internal.model.ProcessDefinitionImpl;


/**
 * 流程管理类
 * @author Administrator
 *
 */
public class WorkFlowManager implements IWorkFlowManager{

	private ProcessEngine processEngine;
	private RepositoryService repositoryService;
	private ExecutionService executionService;
	private HistoryService historyService;
	private TaskService taskService;
	private IdentityService identityService;
	
	public void setProcessEngine(ProcessEngine processEngine) {
		this.processEngine = processEngine;
	}
	public void setRepositoryService(RepositoryService repositoryService) {
		this.repositoryService = repositoryService;
	}
	public void setExecutionService(ExecutionService executionService) {
		this.executionService = executionService;
	}
	public void setHistoryService(HistoryService historyService) {
		this.historyService = historyService;
	}
	public void setTaskService(TaskService taskService) {
		this.taskService = taskService;
	}
	public void setIdentityService(IdentityService identityService) {
		this.identityService = identityService;
	}
	/*
	 * (non-Javadoc)
	 * @see main.core.bpm.IWorkFlowManager#getProcessDefinitions(java.lang.String, int, int)
	 */
	@Override
	public PageModel<ProcessDefinition> getProcessDefinitions(String keyword,int pageNo, int pageSize) {
		int startNo = Page.getStartNumber(pageNo, pageSize);

		if (keyword == null) {
			keyword = "";
		}

		List<ProcessDefinition> datas = repositoryService
				.createProcessDefinitionQuery()
				.processDefinitionNameLike("%" + keyword + "%")
				.page(startNo, pageSize).list();

		int total = (int) repositoryService.createProcessDefinitionQuery()
				.processDefinitionNameLike("%" + keyword + "%").count();
		PageModel<ProcessDefinition> pm = new PageModel<ProcessDefinition>();
		pm.setTotal(total);
		pm.setDatas(datas);
		return pm;
	}
	/*
	 * (non-Javadoc)
	 * @see main.core.bpm.IWorkFlowManager#getProcessDefinitions()
	 */
	@Override
	public List<ProcessDefinition> getProcessDefinitions(int pageNo,int pageSize) {
		List<ProcessDefinition> datas = repositoryService.createProcessDefinitionQuery()
				.page((pageNo-1)*pageSize, pageSize)
				.list();
		return datas;
	}
	
	public int getTotalSize(){
		return (int) repositoryService.createProcessDefinitionQuery().count();
	}
	@Override
	public void deployProcess() {
		repositoryService.createDeployment().addResourceFromClasspath("test/file/submit.jpdl.xml").deploy();
	}
	@Override
	public void createInstance(String key, Map<String, Object> var) {
		// TODO Auto-generated method stub
		
	}
	@Override
	public void deployProcess(ZipInputStream zip) {
		repositoryService.createDeployment().addResourcesFromZipInputStream(zip).deploy();
	}
	@Override
	public void deleteProcess(String deployId) {
		repositoryService.deleteDeployment(deployId);
	}
	
}
