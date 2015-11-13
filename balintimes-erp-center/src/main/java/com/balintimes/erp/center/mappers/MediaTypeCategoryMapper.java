package com.balintimes.erp.center.mappers;

import com.balintimes.erp.center.model.MediaTypeCategory;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/5.
 */
public interface MediaTypeCategoryMapper {
    List<MediaTypeCategory> GetMediaTypeCategoryListByCondition(Map<String,Object> params);

    MediaTypeCategory GetOneMediaTypeCategory(String uid);

    boolean  UpdateMediaTypeCategory(MediaTypeCategory mediaTypeCategory);

    boolean  DeleteMediaTypeCategory(String uid);

    boolean CreateMediaTypeCategory(MediaTypeCategory mediaTypeCategory);
}
