package by.redlaw.service.post;

import by.redlaw.dto.PostDTO;
import by.redlaw.entity.Post;
import java.util.List;

/**
 * Created by Walder on 10.09.2016.
 */

public interface PostService {

    Post createPost(Post post);

    Post getPostById(Long postId);

    void updatePost(Post post);

    void deletePost(Post post);

    List<Post> getAllThePosts();

    void postEdit(PostDTO postDTO);

}
