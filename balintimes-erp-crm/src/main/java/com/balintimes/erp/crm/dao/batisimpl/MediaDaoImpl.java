package com.balintimes.erp.crm.dao.batisimpl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Repository;

import com.balintimes.erp.crm.dao.MediaDao;
import com.balintimes.erp.crm.mappers.MediaMapper;
import com.balintimes.erp.crm.model.Media;

@Repository
public class MediaDaoImpl implements MediaDao {

	@Resource
	private MediaMapper mediaMapper;

	public List<Media> getMediaList(String name) {
		// TODO Auto-generated method stub
		return this.mediaMapper.getMediaList(name);
	}

	public Media getMedia(String uid) {
		// TODO Auto-generated method stub
		return this.mediaMapper.getMedia(uid);
	}

	public void createMedia(Media media) {
		// TODO Auto-generated method stub
		this.mediaMapper.createMedia(media);
	}

	public void updateMedia(Media media) {
		// TODO Auto-generated method stub
		this.mediaMapper.updateMedia(media);
	}

	public void deleteMedia(String uid) {
		// TODO Auto-generated method stub
		this.mediaMapper.deleteMedia(uid);
	}

}
