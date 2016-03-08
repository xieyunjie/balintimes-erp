package com.balintimes.erp.center.service;

import com.balintimes.erp.center.model.Media;
import com.balintimes.erp.center.tuples.TupleResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/11.
 */
public interface MediaService {

    List<Media> GetMediaListByCondition(Map<String, Object> params);

    Media GetOneMedia(String uid);

    TupleResult<Boolean,Object> UpdateMedia(Media Media);

    TupleResult<Boolean,Object> DeleteMedia(String uid);

    TupleResult<Boolean,Object> CreateMedia(Media Media);
}
