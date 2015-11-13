package com.balintimes.erp.center.service;

import com.balintimes.erp.center.model.MediaStatus;
import com.balintimes.erp.center.tuples.TupleResult;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface MediaStatusService {
    List<MediaStatus> GetMediaStatusListByCondition(Map<String,Object> params);

    MediaStatus GetOneMediaStatus(String uid);

    TupleResult<Boolean,Object> UpdateMediaStatus(MediaStatus mediaStatus);

    TupleResult<Boolean,Object>  DeleteMediaStatus(String uid);

    TupleResult<Boolean,Object> CreateMediaStatus(MediaStatus mediaStatus);
}
