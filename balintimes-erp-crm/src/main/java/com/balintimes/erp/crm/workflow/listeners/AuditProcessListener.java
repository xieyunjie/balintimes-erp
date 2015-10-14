package com.balintimes.erp.crm.workflow.listeners;
 
import org.activiti.engine.delegate.DelegateExecution;

/**
 * Created by AlexXie on 2015/10/13.
 */
public class AuditProcessListener {


    public void remindToAudit(DelegateExecution execution) {
        System.out.println("#### 提醒，有一个客户信息需要审核:" + execution.getProcessBusinessKey());
    }

    public void notifyAuditResult(String pass) {
        System.out.println("#### 审核完成了，状态是：" + pass);
    }

}
