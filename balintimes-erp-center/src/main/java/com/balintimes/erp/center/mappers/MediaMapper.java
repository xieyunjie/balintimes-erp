package com.balintimes.erp.center.mappers;

import com.balintimes.erp.center.model.Media;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface MediaMapper {

    boolean UpdateMedia(Media Media);

    boolean DeleteMedia(String uid);

    boolean CreateMedia(Media Media);

    Media GetOneMedia(String uid);

    List<Media> GetMediaListByCondition(Map<String, Object> params);

}
