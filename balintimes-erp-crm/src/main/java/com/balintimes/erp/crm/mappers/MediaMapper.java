package com.balintimes.erp.crm.mappers;

import java.util.List;

import com.balintimes.erp.crm.model.Media;

public interface MediaMapper {
	List<Media> getMediaList(String name);

	Media getMedia(String uid);

	void createMedia(Media media);

	void updateMedia(Media media);

	void deleteMedia(String uid);
}
