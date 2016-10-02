package by.redlaw.converter;

import by.redlaw.dto.PostDTO;
import by.redlaw.entity.Post;

import org.springframework.mock.web.MockMultipartFile;
import org.springframework.web.multipart.MultipartFile;

/**
 * C reated by ZheynovVV on 17.09.2016.
 */

public class PostToPostDTOConverter {

    public static PostDTO convertoEntityToDTO(Post post) {

        PostDTO postDTO = new PostDTO();

        postDTO.setPostID(post.getPostID());
        postDTO.setUserID(post.getUserID());
        postDTO.setPostDate(post.getPostDate());

        if (post.getText() != null)
            postDTO.setText(post.getText());

        if (post.getPhoto() != null) {
            MultipartFile multipartFile = new MockMultipartFile("imageFile", "filename", "image/png", post.getPhoto());
            postDTO.setPhoto(multipartFile);
        }

        return postDTO;
    }
}
