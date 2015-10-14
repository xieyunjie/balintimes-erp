package com.balintimes.erp.crm.service.impl;

import com.balintimes.erp.crm.dao.AreaDao;
import com.balintimes.erp.crm.model.Area;
import com.balintimes.erp.crm.service.AreaService;
import com.balintimes.erp.workflow.activiti.ActivitiEng;
import com.balintimes.erp.workflow.exception.ExecutException;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

@Service
public class AreaServiceImpl implements AreaService {

    @Resource
    private AreaDao areaInfoDao;

    @Resource
    private ActivitiEng activitiEng;

    public List<Area> getAreaInfoList(String name, String code) {
        // TODO Auto-generated method stub
        return this.areaInfoDao.getAreaInfoList(name, code);
    }

    public List<Area> getAreaInfoListByParnetUid(String parentUid) {
        // TODO Auto-generated method stub
        return this.areaInfoDao.getAreaInfoListByParnetUid(parentUid);
    }

    public Area getAreaInfo(String uid) {
        // TODO Auto-generated method stub
        return this.areaInfoDao.getAreaInfo(uid);
    }

    public void createAreaInfo(Area areaInfo) {
        // TODO Auto-generated method stub
        areaInfo.setUid(UUID.randomUUID().toString());
        this.areaInfoDao.createAreaInfo(areaInfo);
    }

    public void updateAreaInfo(Area areaInfo) {
        // TODO Auto-generated method stub
        this.areaInfoDao.updateAreaInfo(areaInfo);
    }

    public void deleteAreaInfo(String uid) {
        // TODO Auto-generated method stub
        this.areaInfoDao.deleteAreaInfo(uid);
    }

    public void updateAndaudit() {

        String uid = "0CF93A8C-41DB-4AE9-A0A3-C511BC09C590";
        Area area = this.getAreaInfo(uid);
        area.setCode("org.springframework.orm.hibernate3.HibernateTransactionManager");
        this.updateAreaInfo(area);


        String taskId = "65014";
        String audit = "14cc1718-28f6-4e02-a45e-a4b1694cc224";

        Map<String, Object> variablesMap = new HashMap<String, Object>(2);
        // 是否通过审核
        variablesMap.put("pass", "1");
        // 是否驳回修改？
        variablesMap.put("fallback", "0");
        try {
            activitiEng.completeUserTask(taskId, audit, variablesMap);
        } catch (ExecutException e) {
            e.printStackTrace();
        }

    }

}
