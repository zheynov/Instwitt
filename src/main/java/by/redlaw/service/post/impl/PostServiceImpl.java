package by.redlaw.service.post.impl;

import by.redlaw.dao.post.PostDao;
import by.redlaw.dto.PostDTO;
import by.redlaw.entity.Post;
import by.redlaw.service.post.PostService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;

/**
 * Created by Walder on 10.09.2016.
 */

@Service("postService")
public class PostServiceImpl implements PostService {

    @Autowired
    PostDao postDao;

    @Override
    @Transactional
    public Post createPost(Post post) {
        return postDao.createPost(post);
    }

    @Override
    @Transactional
    public Post getPostById(Long postId) {
        return postDao.getPostById(postId);
    }

    @Override
    @Transactional
    public void updatePost(Post post) {
        postDao.updatePost(post);
    }

    @Override
    @Transactional
    public void deletePost(Post post) {
        postDao.deletePost(post);
    }

    @Override
    @Transactional
    public List<Post> getAllThePosts() {
        return postDao.getAllThePosts();
    }


    public void postEdit(PostDTO postDTO) {

        Post oldpost = this.getPostById(postDTO.getPostID());

        Post newPost = new Post();
        //getting user_id from the current post_object inside the DB
        newPost.setUserID(oldpost.getUserID()); //временный велосипед
        //getting postDate from the current post_object inside the DB
        newPost.setPostDate(oldpost.getPostDate());
        newPost.setPostID(postDTO.getPostID());
        newPost.setText(postDTO.getText());

        try {
            if (postDTO.getPhoto().getBytes().length > 0)
                newPost.setPhoto(postDTO.getPhoto().getBytes());
            else
                newPost.setPhoto(oldpost.getPhoto());
        } catch (IOException e) {
            e.printStackTrace();
        }

        this.updatePost(newPost);
    }
}
