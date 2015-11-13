package com.balintimes.erp.center.service;

import com.balintimes.erp.center.model.MediaTypeCategory;
import com.balintimes.erp.center.tuples.TupleResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/5.
 */
public interface MediaTypeCategoryService {
    List<MediaTypeCategory> GetMediaTypeCategoryListByCondition(Map<String,Object> params);

    MediaTypeCategory GetOneMediaTypeCategory(String uid);

    TupleResult<Boolean,Object> UpdateMediaTypeCategory(MediaTypeCategory mediaTypeCategory);

    TupleResult<Boolean,Object>  DeleteMediaTypeCategory(String uid);

    TupleResult<Boolean,Object> CreateMediaTypeCategory(MediaTypeCategory mediaTypeCategory);
}
