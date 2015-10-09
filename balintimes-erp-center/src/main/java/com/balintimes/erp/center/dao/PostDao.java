package com.balintimes.erp.center.dao;

import java.util.List;

import com.balintimes.erp.center.model.Post;
import com.balintimes.erp.center.tuples.TuplePage;

public interface PostDao {
	List<Post> GetPostList();

	TuplePage<List<Post>, Integer> GetPostListByPage(Post post, int page,
													 int pageSize);

	void UpdatePost(Post post);

	void DetetePost(String uid);

	boolean CreatePost(Post post);

	List<Post> GetPostSet(String PostName);

	Post GetOnePost(String uid);
	
	List<Post> GetPostByEmployee(String useruid);
	
	List<Post> GetPostParent(String postuid, String organizationuid);
	
	List<Post> GetOneUserPosts(String useruid);
}
