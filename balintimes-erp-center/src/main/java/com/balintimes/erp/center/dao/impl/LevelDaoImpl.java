package com.balintimes.erp.center.dao.impl;

import com.balintimes.erp.center.dao.LevelDao;
import com.balintimes.erp.center.mappers.LevelMapper;
import com.balintimes.erp.center.mappers.StationMapper;
import com.balintimes.erp.center.model.Level;
import com.balintimes.erp.center.model.Station;
import org.springframework.stereotype.Repository;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/27.
 */
@Repository
public class LevelDaoImpl implements LevelDao {
    @Resource
    private LevelMapper levelMapper;
    @Resource
    private StationMapper stationMapper;

    public List<Level> GetLevelListByCondition(Map<String, Object> params) {
        return levelMapper.GetLevelListByCondition(params);
    }


    public Level GetOneLevel(String uid) {
        return levelMapper.GetOneLevel(uid);
    }


    public void UpdateLevel(Level level) {
        levelMapper.UpdateLevel(level);
    }


    public boolean DeleteLevel(String uid) {
        List<Station> stations=stationMapper.CheckLevel(uid);
        if(stations.size()==0){
            try {
                levelMapper.DeleteLevel(uid);
                return true;
            }
            catch (Exception err){
                return  false;
            }
        }
        else {
            return false;
        }

    }

    public boolean CreateLevel(Level level) {
        try {
            levelMapper.CreateLevel(level);
            return true;
        }
        catch (Exception err){
            return false;
        }
    }
}
