package com.balintimes.erp.workflow.activiti.impl;

import com.balintimes.erp.workflow.activiti.ActivitiEng;
import com.balintimes.erp.workflow.exception.DeployException;
import com.balintimes.erp.workflow.exception.ExecutException;
import com.balintimes.erp.workflow.model.ERPType;
import com.balintimes.erp.workflow.model.UserTask;
import com.balintimes.erp.workflow.util.WFUtil;
import org.activiti.engine.*;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.Task;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.zip.ZipInputStream;

/**
 * Created by AlexXie on 2015/10/9.
 */
public class ActivitiEngImpl implements ActivitiEng {


    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private TaskService taskService;
    private HistoryService historyService;
    private FormService formService;


    public void deployBPMN(String filepath, String deployName) throws DeployException {

        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new File(filepath)));

            // 加载流程表
            this.repositoryService.createDeployment().name(deployName).addZipInputStream(zipInputStream).deploy();

        } catch (FileNotFoundException e) {
            throw new DeployException("file can not found!");
        }
    }

    public void startProcessAndSubmit(String processDefKey, ERPType type, String bizKey, String creator, Map<String, Object> variablesMap) throws ExecutException {

        try {
            if (variablesMap == null) {
                variablesMap = new HashMap<String, Object>();
            }
            variablesMap.put("creator", creator);

            ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefKey, WFUtil.makeBizKey(type, bizKey), variablesMap);
            Task task = this.taskService.createTaskQuery().processInstanceBusinessKey(WFUtil.makeBizKey(type, bizKey)).singleResult();
            this.taskService.complete(task.getId());
        } catch (Exception ex) {

            throw new ExecutException("start Process inst error", ex);
        }
    }

    public void startProcess(String processDefKey, ERPType type, String bizKey, String creator, Map<String, Object> variablesMap) throws ExecutException {
        try {
            if (variablesMap == null) {
                variablesMap = new HashMap<String, Object>();
            }
            variablesMap.put("creator", creator);

            ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefKey, WFUtil.makeBizKey(type, bizKey), variablesMap);
        } catch (Exception ex) {

            throw new ExecutException("start Process inst error", ex);
        }
    }

    public List<UserTask> getUserTask(String userUID, ERPType erpType) {
        List<UserTask> userTasks = new ArrayList<UserTask>();

        List<Task> taskList = new ArrayList<Task>();
        taskList.addAll(this.taskService.createTaskQuery().processInstanceBusinessKeyLike(erpType.toString()).taskAssignee(userUID).orderByTaskCreateTime().asc().list());
        taskList.addAll(this.taskService.createTaskQuery().processInstanceBusinessKeyLike(erpType.toString()).taskCandidateUser(userUID).orderByTaskCreateTime().asc().list());

        for (Task task : taskList) {
            UserTask userTask = new UserTask();

            userTask.setTaskId(task.getId());
            userTask.setTaskDefKey(task.getTaskDefinitionKey());
            userTask.setParentTaskId(task.getParentTaskId());
            ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            userTask.setBizKey(pi.getBusinessKey());
            userTask.setProcessDefId(task.getProcessDefinitionId());
            userTask.setProcessInstId(task.getProcessInstanceId());
            userTask.setOwner(task.getOwner());
            userTask.setAssignee(task.getAssignee());
            userTask.setCreateTime(task.getCreateTime());

            userTasks.add(userTask);
        }
        return userTasks;
    }

    public List<UserTask> getUserTask(String userUID) {
        List<UserTask> userTasks = new ArrayList<UserTask>();

        List<Task> taskList = new ArrayList<Task>();
        taskList.addAll(this.taskService.createTaskQuery().taskAssignee(userUID).orderByTaskCreateTime().asc().list());
        taskList.addAll(this.taskService.createTaskQuery().taskCandidateUser(userUID).orderByTaskCreateTime().asc().list());

        for (Task task : taskList) {
            UserTask userTask = new UserTask();

            userTask.setTaskId(task.getId());
            userTask.setTaskDefKey(task.getTaskDefinitionKey());
            userTask.setParentTaskId(task.getParentTaskId());
            ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            userTask.setBizKey(pi.getBusinessKey());
            userTask.setProcessDefId(task.getProcessDefinitionId());
            userTask.setProcessInstId(task.getProcessInstanceId());
            userTask.setOwner(task.getOwner());
            userTask.setAssignee(task.getAssignee());
            userTask.setCreateTime(task.getCreateTime());

            userTasks.add(userTask);
        }
        return userTasks;
    }

    public boolean completeUserTask(String taskId, String userUID, Map<String, Object> variablesMap) throws ExecutException{

        boolean finish_flag = false;
        try {

            Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
            this.taskService.claim(taskId, userUID);
            this.taskService.complete(taskId, variablesMap);

            ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
            if (pi == null) {
                finish_flag = true;
            }

        } catch (Exception ex) {

            throw new ExecutException("complete user task error", ex);
        }
        return finish_flag;
    }


    public void setRepositoryService(RepositoryService repositoryService) {
        this.repositoryService = repositoryService;
    }

    public void setRuntimeService(RuntimeService runtimeService) {
        this.runtimeService = runtimeService;
    }

    public void setTaskService(TaskService taskService) {
        this.taskService = taskService;
    }

    public void setHistoryService(HistoryService historyService) {
        this.historyService = historyService;
    }

    public void setFormService(FormService formService) {
        this.formService = formService;
    }
}
