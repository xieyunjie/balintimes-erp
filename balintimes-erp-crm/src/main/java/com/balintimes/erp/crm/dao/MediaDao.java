package com.balintimes.erp.crm.dao;

import java.util.List;

import com.balintimes.erp.crm.model.Media;

public interface MediaDao {
	List<Media> getMediaList(String name);

	Media getMedia(String uid);

	void createMedia(Media media);

	void updateMedia(Media media);

	void deleteMedia(String uid);
}
