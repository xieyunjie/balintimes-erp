package com.balintimes.erp.workflow.model;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by AlexXie on 2015/10/9.
 */
public class UserTask implements Serializable {

    private static final long serialVersionUID = 7120008794475945895L;

    private String taskId;
    private String taskDefKey;
    private String parentTaskId;

    private String bizKey;

    private String processDefId;
    private String processInstId;
    private String executionInstId;

    private String owner;
    private String assignee;
    private Date createTime;

    private List<TaskUserLink> taskUserLinkList;

    public UserTask() {
        this.taskUserLinkList = new ArrayList<TaskUserLink>();
    }

    public String getTaskId() {
        return taskId;
    }

    public void setTaskId(String taskId) {
        this.taskId = taskId;
    }

    public String getTaskDefKey() {
        return taskDefKey;
    }

    public void setTaskDefKey(String taskDefKey) {
        this.taskDefKey = taskDefKey;
    }

    public String getParentTaskId() {
        return parentTaskId;
    }

    public void setParentTaskId(String parentTaskId) {
        this.parentTaskId = parentTaskId;
    }

    public String getBizKey() {
        return bizKey;
    }

    public void setBizKey(String bizKey) {
        this.bizKey = bizKey;
    }

    public String getProcessDefId() {
        return processDefId;
    }

    public void setProcessDefId(String processDefId) {
        this.processDefId = processDefId;
    }

    public String getProcessInstId() {
        return processInstId;
    }

    public void setProcessInstId(String processInstId) {
        this.processInstId = processInstId;
    }

    public String getExecutionInstId() {
        return executionInstId;
    }

    public void setExecutionInstId(String executionInstId) {
        this.executionInstId = executionInstId;
    }

    @Deprecated
    public String getOwner() {
        return owner;
    }

    public void setOwner(String owner) {
        this.owner = owner;
    }

    @Deprecated
    public String getAssignee() {
        return assignee;
    }

    public void setAssignee(String assignee) {
        this.assignee = assignee;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public List<TaskUserLink> getTaskUserLinkList() {
        return taskUserLinkList;
    }

    public void setTaskUserLinkList(List<TaskUserLink> taskUserLinkList) {
        this.taskUserLinkList = taskUserLinkList;
    }

    public void addUserLink(TaskUserLink link) {

        if (this.taskUserLinkList == null) this.taskUserLinkList = new ArrayList<TaskUserLink>();

        this.taskUserLinkList.add(link);
    }

    @Override
    public String toString() {
        return "UserTask{" +
                "taskId='" + taskId + '\'' +
                ", taskDefKey='" + taskDefKey + '\'' +
                ", parentTaskId='" + parentTaskId + '\'' +
                ", bizKey='" + bizKey + '\'' +
                ", processDefId='" + processDefId + '\'' +
                ", processInstId='" + processInstId + '\'' +
                ", executionInstId='" + executionInstId + '\'' +
                ", owner='" + owner + '\'' +
                ", assignee='" + assignee + '\'' +
                ", createTime=" + createTime +
                '}';
    }
}
