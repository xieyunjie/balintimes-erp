package com.balintimes.erp.workflow.activiti.impl;

import com.balintimes.erp.workflow.activiti.ActivitiEng;
import com.balintimes.erp.workflow.exception.DeployException;
import com.balintimes.erp.workflow.exception.ExecutException;
import com.balintimes.erp.workflow.model.TaskUserLink;
import com.balintimes.erp.workflow.model.TupleTask;
import com.balintimes.erp.workflow.model.UserTask;
import com.balintimes.erp.workflow.util.WFUtil;
import org.activiti.engine.*;
import org.activiti.engine.repository.Deployment;
import org.activiti.engine.repository.ProcessDefinition;
import org.activiti.engine.runtime.ProcessInstance;
import org.activiti.engine.task.IdentityLink;
import org.activiti.engine.task.Task;
import org.activiti.engine.task.TaskInfoQuery;
import org.apache.commons.lang3.StringUtils;

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

    private String erpType = "";

    private RepositoryService repositoryService;
    private RuntimeService runtimeService;
    private TaskService taskService;
    private HistoryService historyService;
    private FormService formService;

    public ActivitiEngImpl(String erpType) {
        this.erpType = erpType;
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

    public String deployBPMN(String zipfilepath, String deployName) throws DeployException {
        try {
            ZipInputStream zipInputStream = new ZipInputStream(new FileInputStream(new File(zipfilepath)));

            // 加载流程表
            Deployment deployment = this.repositoryService.createDeployment().name(deployName).addZipInputStream(zipInputStream).deploy();
            return deployment.getId();

        } catch (FileNotFoundException e) {
            throw new DeployException("file can not found!");
        }
    }

    public void deleteBPMN(String deploymentId) {
        this.repositoryService.deleteDeployment(deploymentId);
    }

    public boolean disableBPMN(String processDefId) {
        this.repositoryService.suspendProcessDefinitionById(processDefId);
        ProcessDefinition pd = this.repositoryService.getProcessDefinition(processDefId);

        return pd.isSuspended();
    }

    public boolean enableBPMN(String processDefId) {
        this.repositoryService.activateProcessDefinitionById(processDefId);
        ProcessDefinition pd = this.repositoryService.getProcessDefinition(processDefId);

        return !pd.isSuspended();
    }

    public void startAndcompleteProcess(String processDefId, String bizKey, String creator, Map<String, Object> variablesMap) throws ExecutException {
        try {
            if (variablesMap == null) {
                variablesMap = new HashMap<String, Object>();
            }
            variablesMap.put("creator", creator);

            ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefId, WFUtil.makeComplexKey(erpType, bizKey), variablesMap);
            Task task = this.taskService.createTaskQuery().processInstanceBusinessKey(WFUtil.makeComplexKey(erpType, bizKey)).singleResult();
            this.taskService.complete(task.getId());
        } catch (Exception ex) {

            throw new ExecutException("start Process inst error", ex);
        }
    }

    public void startProcess(String processDefId, String bizKey, String creator, Map<String, Object> variablesMap) throws ExecutException {
        try {
            if (variablesMap == null) {
                variablesMap = new HashMap<String, Object>();
            }
            variablesMap.put("creator", creator);

            ProcessInstance pi = runtimeService.startProcessInstanceByKey(processDefId, WFUtil.makeComplexKey(erpType, bizKey), variablesMap);
        } catch (Exception ex) {

            throw new ExecutException("start Process inst error", ex);
        }
    }

    public boolean suspendProcessInst(String bizKey) {

        ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().active().processInstanceBusinessKey(WFUtil.makeComplexKey(erpType, bizKey)).singleResult();
        this.runtimeService.suspendProcessInstanceById(pi.getId());

        ProcessInstance piResult = this.getProcessInstance(erpType, bizKey);
        return piResult.isSuspended();
    }

    public boolean activateProcessInst(String bizKey) {
        ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().suspended().processInstanceBusinessKey(WFUtil.makeComplexKey(erpType, bizKey)).singleResult();
        this.runtimeService.activateProcessInstanceById(pi.getId());

        ProcessInstance piResult = this.getProcessInstance(erpType, bizKey);
        return !piResult.isSuspended();
    }

    public boolean deleteProcessInst(String bizKey, String comment) {
        ProcessInstance pi = this.getProcessInstance(erpType, bizKey);
        this.runtimeService.deleteProcessInstance(pi.getId(), comment);

        return true;
    }

    public UserTask getOneUserTask(String taskId) {
        Task task = this.taskService.createTaskQuery().taskId(taskId).singleResult();
        return makeUserTask(task);
    }

    public List<UserTask> getBizTask(String bizKey) {
        List<Task> taskList = this.taskService.createTaskQuery().processInstanceBusinessKey(WFUtil.makeComplexKey(erpType, bizKey)).orderByTaskCreateTime().asc().list();
        List<UserTask> userTasks = new ArrayList<UserTask>();

        for (Task task : taskList) {
            userTasks.add(this.makeUserTask(task));
        }
        return userTasks;
    }

    public List<UserTask> getUserTask(String userUID) {
        return this.getUserTask(userUID, erpType, 1, Integer.MAX_VALUE).taskList;
    }

    public List<UserTask> getUserAllTask(String userUID) {
        return this.getUserTask(userUID, null, 1, Integer.MAX_VALUE).taskList;
    }

    public TupleTask<List<UserTask>, Long> getUserTask(String userUID, Integer page, Integer pagesize) {
        return this.getUserTask(userUID, erpType, page, pagesize);
    }

    public TupleTask<List<UserTask>, Long> getUserAllTask(String userUID, Integer page, Integer pagesize) {
        return this.getUserTask(userUID, null, page, pagesize);
    }

    public void claimUserTask(String userUID, String taskId) {
        this.taskService.claim(taskId, userUID);
    }

    public boolean completeUserTask(String taskId, String userUID, Map<String, Object> variablesMap) throws ExecutException {
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

    public void fireSignalEvent(String executionId, String signal, Map<String, Object> variablesMap) {

        if (!StringUtils.isEmpty(executionId) && variablesMap != null) {
            this.runtimeService.signalEventReceived(signal, executionId, variablesMap);
        } else if (!StringUtils.isEmpty(executionId) && variablesMap == null) {
            this.runtimeService.signalEventReceived(signal, executionId);
        } else if (StringUtils.isEmpty(executionId) && variablesMap != null) {
            this.runtimeService.signalEventReceived(signal, variablesMap);
        } else {
            this.runtimeService.signalEventReceived(signal);
        }
    }

    public void fireMessageEvent(String executionId, String message, Map<String, Object> variablesMap) {
        if (variablesMap == null) {
            this.runtimeService.messageEventReceived(message, executionId);
        } else {
            this.runtimeService.messageEventReceived(message, executionId, variablesMap);
        }
    }

    public void fireBoundarySignalEvent(String executionId, String signal, Map<String, Object> variablesMap) {
        this.fireSignalEvent(executionId, signal, variablesMap);
    }

    private ProcessInstance getProcessInstance(String type, String bizKey) {
        return this.runtimeService.createProcessInstanceQuery().processInstanceBusinessKey(WFUtil.makeComplexKey(type, bizKey)).singleResult();
    }

    private UserTask makeUserTask(Task task) {

        UserTask userTask = new UserTask();
        List<IdentityLink> identityLinks;

        userTask.setTaskId(task.getId());
        userTask.setTaskDefKey(task.getTaskDefinitionKey());
        userTask.setParentTaskId(task.getParentTaskId());
        userTask.setExecutionInstId(task.getExecutionId());
        ProcessInstance pi = this.runtimeService.createProcessInstanceQuery().processInstanceId(task.getProcessInstanceId()).singleResult();
        userTask.setBizKey(WFUtil.splitBizKey(pi.getBusinessKey()));
        userTask.setProcessDefId(task.getProcessDefinitionId());
        userTask.setProcessInstId(task.getProcessInstanceId());

        userTask.setOwner(task.getOwner());
        userTask.setAssignee(task.getAssignee());
        userTask.setCreateTime(task.getCreateTime());

        identityLinks = this.taskService.getIdentityLinksForTask(task.getId());

        if (identityLinks != null) {
            for (IdentityLink identityLink : identityLinks) {
                userTask.addUserLink(new TaskUserLink("", identityLink.getType(), identityLink.getUserId()));
            }
        }

        return userTask;
    }


    private TupleTask<List<UserTask>, Long> getUserTask(String userUID, String erpType, Integer page, Integer pagesize) {

        List<UserTask> userTasks = new ArrayList<UserTask>();

        TaskInfoQuery taskInfoQuery = this.taskService.createTaskQuery().taskCandidateOrAssigned(userUID);
        if (erpType != null) taskInfoQuery = taskInfoQuery.processInstanceBusinessKeyLike("%" + erpType.toString() + "%");
        Long totalcount = taskInfoQuery.count();

        List<Task> taskList = taskInfoQuery.orderByTaskCreateTime().asc().listPage((page - 1) * pagesize, pagesize);

        for (Task task : taskList) {
            userTasks.add(this.makeUserTask(task));
        }
        return new TupleTask<List<UserTask>, Long>(userTasks, totalcount);
    }
}
