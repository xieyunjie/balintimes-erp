package com.balintimes.erp.center.mappers;

import com.balintimes.erp.center.model.MediaType;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/6.
 */
public interface MediaTypeMapper {
    List<MediaType> GetMediaTypeListByCondition(Map<String,Object> params);

    List<MediaType> GetMediaTypeListByProcedure(Map<String,Object> params);

    MediaType GetOneMediaType(String uid);

    boolean  UpdateMediaType(MediaType mediaType);

    boolean  DeleteMediaType(String uid);

    boolean CreateMediaType(MediaType mediaType);
}
