package com.balintimes.erp.crm.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.crm.dao.MediaDao;
import com.balintimes.erp.crm.model.Media;
import com.balintimes.erp.crm.service.MediaService;

@Service
public class MediaServiceImpl implements MediaService {

	@Resource
	private MediaDao mediaDao;

	public List<Media> getMediaList(String name) {
		// TODO Auto-generated method stub
		return this.mediaDao.getMediaList(name);
	}

	public Media getMedia(String uid) {
		// TODO Auto-generated method stub
		return this.mediaDao.getMedia(uid);
	}

	public void createMedia(Media media) {
		// TODO Auto-generated method stub
		this.mediaDao.createMedia(media);
	}

	public void updateMedia(Media media) {
		// TODO Auto-generated method stub
		this.mediaDao.updateMedia(media);
	}

	public void deleteMedia(String uid) {
		// TODO Auto-generated method stub
		this.mediaDao.deleteMedia(uid);
	}

}
