package com.balintimes.erp.center.dao;

import com.balintimes.erp.center.model.Media;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface MediaDao {
    Media GetOneMedia(String uid);

    List<Media> GetMediaListByCondition(Map<String, Object> params);

    boolean UpdateMedia(Media Media);

    boolean DeleteMedia(String uid);

    boolean CreateMedia(Media Media);


}
