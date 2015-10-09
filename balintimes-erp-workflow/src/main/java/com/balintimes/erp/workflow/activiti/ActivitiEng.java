package com.balintimes.erp.workflow.activiti;

import com.balintimes.erp.workflow.exception.DeployException;
import com.balintimes.erp.workflow.exception.ExecutException;
import com.balintimes.erp.workflow.model.ERPType;
import com.balintimes.erp.workflow.model.UserTask;

import java.util.List;
import java.util.Map;

/**
 * Created by AlexXie on 2015/10/9.
 */
public interface ActivitiEng {
    /**
     * 加载流程图
     * filepath 文件路径+文件 建议是zip文件包含bpnm 以及 png文件
     * deployName 加载的名称
     */
    void deployBPMN(String filepath, String deployName) throws DeployException;

    /**
     * 启动流程，并执行执行提交
     * processDefKey 流程图定义id
     * ERPType 业务类型
     * bizKey 业务表单key
     * creator 创建人 -- 提交人
     * 流程变量
     */
    void startProcessAndSubmit(String processDefKey, ERPType type, String bizKey, String creator, Map<String, Object> variablesMap) throws ExecutException;

    /**
     * 启动流程
     * processDefKey 流程图定义id
     * ERPType 业务类型
     * bizKey 业务表单key
     * creator 创建人 -- 提交人
     * 流程变量
     */
    void startProcess(String processDefKey, ERPType type, String bizKey, String creator, Map<String, Object> variablesMap) throws ExecutException;

    List<UserTask> getUserTask(String userUID, ERPType erpType);

    List<UserTask> getUserTask(String userUID);

    /**
     * 返回是否已经完成流程 true 流程完成
     */
    boolean completeUserTask(String taskId, String userUID, Map<String, Object> variablesMap) throws ExecutException;



}
