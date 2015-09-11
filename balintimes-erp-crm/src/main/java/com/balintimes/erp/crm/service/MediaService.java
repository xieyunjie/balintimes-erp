package com.balintimes.erp.crm.service;

import java.util.List;

import com.balintimes.erp.crm.model.Media;

public interface MediaService {
	List<Media> getMediaList(String name);

	Media getMedia(String uid);

	void createMedia(Media media);

	void updateMedia(Media media);

	void deleteMedia(String uid);
}
