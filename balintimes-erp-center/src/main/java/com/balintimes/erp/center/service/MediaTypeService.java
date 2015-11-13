package com.balintimes.erp.center.service;

import com.balintimes.erp.center.model.MediaType;
import com.balintimes.erp.center.tuples.TupleResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface MediaTypeService {
    List<MediaType> GetMediaTypeListByCondition(Map<String,Object> params);

    MediaType GetOneMediaType(String uid);

    TupleResult<Boolean,Object> UpdateMediaType(MediaType mediaType);

    TupleResult<Boolean,Object>  DeleteMediaType(String uid);

    TupleResult<Boolean,Object> CreateMediaType(MediaType mediaType);
}
