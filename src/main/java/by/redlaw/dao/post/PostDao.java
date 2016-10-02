package by.redlaw.dao.post;

import by.redlaw.entity.Post;
import java.util.List;

/**
 * Created by Redlaw on 10.09.2016.
 */

public interface PostDao {

    Post createPost(Post post);

    Post getPostById(Long postId);

    void updatePost(Post post);

    void deletePost(Post post);

    List<Post> getAllThePosts();

}
