package com.balintimes.erp.center.mappers;

import com.balintimes.erp.center.model.MediaStatus;

import java.util.List;
import java.util.Map;

/**
 * Created by Administrator on 2015/11/4.
 */
public interface MediaStatusMapper {
    List<MediaStatus> GetMediaStatusListByCondition(Map<String,Object> params);

    MediaStatus GetOneMediaStatus(String uid);

    boolean  UpdateMediaStatus(MediaStatus mediaStatus);

    boolean  DeleteMediaStatus(String uid);

    boolean CreateMediaStatus(MediaStatus mediaStatus);
}
