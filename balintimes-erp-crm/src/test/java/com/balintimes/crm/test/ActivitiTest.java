package com.balintimes.crm.test;

import com.balintimes.erp.crm.service.AreaService;
import com.balintimes.erp.workflow.activiti.ActivitiEng;
import com.balintimes.erp.workflow.exception.DeployException;
import com.balintimes.erp.workflow.exception.ExecutException;
import com.balintimes.erp.workflow.model.UserTask;
import com.balintimes.erp.workflow.util.WFUtil;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import javax.annotation.Resource;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by AlexXie on 2015/10/9.
 */

//@RunWith(SpringJUnit4ClassRunner.class)
//@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class ActivitiTest {

    @Resource
    private ActivitiEng activitiEng;

//    @Test
    public void deployBPMN() {

        String path = "流程文件路径";
        String filename = "crm审核流程";
        System.out.println(path);

        try {
            this.activitiEng.deployBPMN(path, filename);
        } catch (DeployException e) {
            e.printStackTrace();
        }

    }

    /**
     * admin -- 59e2be4d-23b7-11e5-970b-c86000a05d5f
     * <p/>
     * yunjie.xie -- ef9f7cab-185b-4ef2-8763-b332a806dfe1
     * jacky -- 14cc1718-28f6-4e02-a45e-a4b1694cc224
     * alex -- ced2d960-23b7-11e5-970b-c86000a05d5f
     * <p/>
     * bizkey -- 81d17651-6e65-11e5-b1fb-c86000a05d5f
     */

//    @Test
    public void startBill() {

        List<String> audits = new ArrayList<String>(3);// 设置审核人员组
        audits.add("ef9f7cab-185b-4ef2-8763-b332a806dfe1");
        audits.add("14cc1718-28f6-4e02-a45e-a4b1694cc224");
        audits.add("ced2d960-23b7-11e5-970b-c86000a05d5f");
        Map<String, Object> varsMap = new HashMap<String, Object>();

        varsMap.put("audits", audits);

        String processKey = "CRMAuditProcess", // 流程id
                bizKey = "81d17651-6e65-11e5-b1fb-c86000a05d5f",// 业务id
                creator = "59e2be4d-23b7-11e5-970b-c86000a05d5f";// 创建人

        try {
//            this.activitiEng.startProcess(processKey, bizKey, creator, varsMap);
            this.activitiEng.startAndcompleteProcess(processKey, bizKey, creator, varsMap);
        } catch (ExecutException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void queryBill() {

        String creator = "59e2be4d-23b7-11e5-970b-c86000a05d5f";

        List<UserTask> userTasks = this.activitiEng.getUserTask(creator);

        for (UserTask userTask : userTasks) {
            System.out.println(userTask.toString());
        }
    }

    /**
     * 提交单据/完成任务
     */
//    @Test
    public void submitBill() {

        String creator = "59e2be4d-23b7-11e5-970b-c86000a05d5f";

        boolean isfinish = false;
        try {
            isfinish = this.activitiEng.completeUserTask("20003", creator, null);
            if (isfinish) {
                System.out.println("Process finish!!!");
            }
        } catch (ExecutException e) {
            e.printStackTrace();
        }

        try {
            Thread.sleep(70 * 1000);
            System.out.println("OK OK OK OK OK ");
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    // 个人任务或者任务 管理员审核
    /**
     * yunjie.xie -- ef9f7cab-185b-4ef2-8763-b332a806dfe1
     * jacky -- 14cc1718-28f6-4e02-a45e-a4b1694cc224
     * alex -- ced2d960-23b7-11e5-970b-c86000a05d5f
     * */
//    @Test
    public void queryTask() {

        String audit = "ced2d960-23b7-11e5-970b-c86000a05d5f";

        List<UserTask> userTasks = this.activitiEng.getUserTask(audit);
        System.err.println("####");
        for (UserTask userTask : userTasks) {
            System.out.println(userTask.toString());
        }
        System.err.println("####");

    }

//    @Test
    public void auditTask() {

        String audit = "14cc1718-28f6-4e02-a45e-a4b1694cc224";

        List<UserTask> userTasks = this.activitiEng.getUserTask(audit);

        Map<String, Object> variablesMap = new HashMap<String, Object>(2);
        // 是否通过审核
        variablesMap.put("pass", "1");
        // 是否驳回修改？
        variablesMap.put("fallback","0");
        String taskId = "47514";
        try {
            boolean isfinish = false;
            isfinish = this.activitiEng.completeUserTask(taskId, audit, variablesMap);
            if (isfinish) {
                System.out.println("Process finish!!!");
            }
        } catch (ExecutException e) {
            e.printStackTrace();
        }
    }

//    @Test
    public void  deleteProcessInst()
    {
        String bizKey = "81d17651-6e65-11e5-b1fb-c86000a05d5f";
        activitiEng.deleteProcessInst(bizKey,"取消了");
    }

    @Resource
    private AreaService areaService;
//    @Test
    public void completeTask(){
       areaService.updateAndaudit();
    }
}
