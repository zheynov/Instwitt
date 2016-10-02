package by.redlaw.dto;

import by.redlaw.entity.UserEntity;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * Created by ZheynovVV on 17.09.2016.
 */
public class PostDTO {

    public PostDTO() {
    }

    private Long postID;
    private Date postDate;
    private UserEntity userID;
    private String text;
    private MultipartFile photo;

    public Long getPostID() {
        return postID;
    }

    public void setPostID(Long postID) {
        this.postID = postID;
    }

    public Date getPostDate() {
        return postDate;
    }

    public void setPostDate(Date postDate) {
        this.postDate = postDate;
    }

    public UserEntity getUserID() {
        return userID;
    }

    public void setUserID(UserEntity userID) {
        this.userID = userID;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    public MultipartFile getPhoto() {
        return photo;
    }

    public void setPhoto(MultipartFile photo) {
        this.photo = photo;
    }
}
