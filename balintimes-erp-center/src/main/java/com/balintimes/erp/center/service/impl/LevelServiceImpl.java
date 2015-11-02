package com.balintimes.erp.center.service.impl;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.LevelDao;
import com.balintimes.erp.center.model.Level;
import com.balintimes.erp.center.service.LevelService;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/27.
 */
@Service("levelService")
public class LevelServiceImpl implements LevelService{
    @Resource
    private LevelDao levelDao;

    public List<Level> GetLevelListByCondition(Map<String, Object> params) {
        return levelDao.GetLevelListByCondition(params);
    }

    public Level GetOneLevel(String uid) {
        return levelDao.GetOneLevel(uid);
    }

    @CustomerTransactional
    public void UpdateLevel(Level level) {
        levelDao.UpdateLevel(level);
    }

    @CustomerTransactional
    public boolean DeleteLevel(String uid) {

        return levelDao.DeleteLevel(uid);
    }

    @CustomerTransactional
    public boolean CreateLevel(Level level) {
        return levelDao.CreateLevel(level);
    }
}
