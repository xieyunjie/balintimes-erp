package com.balintimes.erp.center.dao;

import com.balintimes.erp.center.model.Level;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/10/27.
 */
public interface LevelDao {

    List<Level> GetLevelListByCondition(Map<String,Object> params);

    Level GetOneLevel(String uid);

    void  UpdateLevel(Level level);

    boolean  DeleteLevel(String uid);

    boolean CreateLevel(Level level);
}
