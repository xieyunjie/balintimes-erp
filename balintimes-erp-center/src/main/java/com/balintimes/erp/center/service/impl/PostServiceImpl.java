package com.balintimes.erp.center.service.impl;

import java.util.List;

import javax.annotation.Resource;

import org.springframework.stereotype.Service;

import com.balintimes.erp.center.annotation.CustomerTransactional;
import com.balintimes.erp.center.dao.PostDao;
import com.balintimes.erp.center.model.Post;
import com.balintimes.erp.center.service.PostService;
import com.balintimes.erp.center.tuples.TuplePage;

@Service("postService")
public class PostServiceImpl implements PostService {
	
	@Resource
	private PostDao postDao;
	
	public List<Post> GetPostList() {
		return this.postDao.GetPostList();
	}

	
	public TuplePage<List<Post>, Integer> GetPostListByPage(Post post,
			int page, int pageSize) {
		// TODO Auto-generated method stub
		return this.postDao.GetPostListByPage(post, page, pageSize);
	}

	 @CustomerTransactional
	public void UpdatePost(Post post) {
		// TODO Auto-generated method stub
		 this.postDao.UpdatePost(post);
	}

	 @CustomerTransactional
	public void DetetePost(String uid) {
		// TODO Auto-generated method stub
		 this.postDao.DetetePost(uid);
	}
	
	public boolean CreatePost(Post post) {
		// TODO Auto-generated method stub
		return this.postDao.CreatePost(post);
	}


	public List<Post> GetPostSet(String PostName) {
		// TODO Auto-generated method stub
		return this.postDao.GetPostSet(PostName);
	}


	public Post GetOnePost(String uid) {
		// TODO Auto-generated method stub
		return this.postDao.GetOnePost(uid);
	}


	
	public List<Post> GetPostParent(String postuid, String organizationuid) {
		// TODO Auto-generated method stub
		return this.postDao.GetPostParent(postuid, organizationuid);
	}


	
	public List<Post> GetOneUserPosts(String useruid) {
		// TODO Auto-generated method stub
		return this.postDao.GetOneUserPosts(useruid);
	}
	
	

}
