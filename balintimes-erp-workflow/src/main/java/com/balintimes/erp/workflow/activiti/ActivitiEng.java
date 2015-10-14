package com.balintimes.erp.workflow.activiti;

import com.balintimes.erp.workflow.exception.DeployException;
import com.balintimes.erp.workflow.exception.ExecutException;
import com.balintimes.erp.workflow.model.TupleTask;
import com.balintimes.erp.workflow.model.UserTask;

import java.util.List;
import java.util.Map;

/**
 * Created by AlexXie on 2015/10/9.
 */
public interface ActivitiEng {


    /**
     * 加载流程图定义
     * filepath 文件路径+文件 建议是zip文件包含bpnm 以及 png文件
     * deployName 加载的名称
     * return deployid
     */
    String deployBPMN(String zipfilepath, String deployName) throws DeployException;

    /**
     * 删除流程图定义
     */
    void deleteBPMN(String deploymentId);

    /**
     * 中止流程定义 -- 只是不能启动流程实例而已
     * 运行当中的流程实例不受影响
     */
    boolean disableBPMN(String processDefId);

    /**
     * 开启流程定义
     */
    boolean enableBPMN(String processDefId);

    /**
     * 启动流程，并执行执行提交
     * processDefKey 流程图定义id
     * bizKey 业务表单key
     * creator 创建人 -- 提交人
     * 流程变量
     */
    void startAndcompleteProcess(String processDefId,  String bizKey, String creator, Map<String, Object> variablesMap) throws ExecutException;

    /**
     * 启动流程
     * processDefKey 流程图定义id
     * bizKey 业务表单key
     * creator 创建人 -- 提交人
     * 流程变量
     */
    void startProcess(String processDefId,  String bizKey, String creator, Map<String, Object> variablesMap) throws ExecutException;

    /**
     * 暂停流程实例
     */
    boolean suspendProcessInst( String bizKey);

    /**
     * 启动流程实例
     */
    boolean activateProcessInst(String bizKey);

    /**
     * 删除任务
     */
    boolean deleteProcessInst(String bizKey, String comment);

    /**
     * 单个任务属性
     */
    UserTask getOneUserTask(String taskId);

    /**
     * 获取业务任务属性
     */
    List<UserTask> getBizTask(String bizKey);

    /**
     * 获取用户任务
     */
    List<UserTask> getUserTask(String userUID);

    /**
     * 获取用户所有任务
     */
    List<UserTask> getUserAllTask(String userUID);

    /**
     * 获取用户任务，返回tupletask
     */
    TupleTask<List<UserTask>, Long> getUserTask(String userUID, Integer page, Integer pagesize);

    /**
     * 获取用户任务，返回tupletask
     */
    TupleTask<List<UserTask>, Long> getUserAllTask(String userUID, Integer page, Integer pagesize);

    /**
     * 领取任务
     */
    void claimUserTask(String userUID, String taskId);

    /**
     * 返回是否已经完成流程 true 流程完成
     */
    boolean completeUserTask(String taskId, String userUID, Map<String, Object> variablesMap) throws ExecutException;


    // =============== 事件 =============================

    /**
     * @executionId 执行ID，不是实例ID哦
     */
    void fireSignalEvent(String executionId, String signal, Map<String, Object> variablesMap);

    void fireMessageEvent(String executionId, String message, Map<String, Object> variablesMap);

    void fireBoundarySignalEvent(String executionId, String signal, Map<String, Object> variablesMap);


}
